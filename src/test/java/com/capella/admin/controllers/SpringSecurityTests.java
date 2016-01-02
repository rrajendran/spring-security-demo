package com.capella.admin.controllers;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.capella.admin.controllers.SecurityRequestPostProcessors.user;
import static com.capella.admin.controllers.SecurityRequestPostProcessors.userDeatilsService;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-security.xml"})
@WebAppConfiguration
public class SpringSecurityTests {

    private static String SEC_CONTEXT_ATTR = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {


        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilters(this.springSecurityFilterChain).build();
    }

    @Test
    public void requiresAuthentication() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(redirectedUrl("/secure/index"));
    }

    @Test
    public void accessGranted() throws Exception {
        this.mockMvc.perform(get("/login").with(userDeatilsService("admin")))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/login.jsp"));
    }

    @Test
    public void accessDeniedWithoutAuthentication() throws Exception {
        this.mockMvc.perform(get("/secure/index").with(user("admin").roles("ROLE_USER")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void userAuthenticates() throws Exception {
        final String username = "admin";
        final String password = "admin";
        mockMvc.perform(post("/login")
                .with(csrf())
                .param("username", username)
                .param("password", password))
                .andExpect(redirectedUrl("/secure/index"))
                .andExpect(new ResultMatcher() {
                    public void match(MvcResult mvcResult) throws Exception {
                        HttpSession session = mvcResult.getRequest().getSession();
                        SecurityContext securityContext = (SecurityContext) session.getAttribute(SEC_CONTEXT_ATTR);
                        Assert.assertEquals(securityContext.getAuthentication().getName(), username);
                        Optional<? extends GrantedAuthority> role_user =
                                securityContext.getAuthentication().getAuthorities().stream()
                                        .filter(r -> r.getAuthority().equals("ROLE_USER")
                                                || r.getAuthority().equals("ROLE_ADMIN")).findFirst();
                        Assert.assertEquals(role_user.isPresent(), true);
                    }
                });
    }

    @Test
    public void userAuthenticates_GuestUser() throws Exception {
        final String username = "guest";
        final String password = "guest";
        mockMvc.perform(post("/login")
                .with(csrf())
                .param("username", username)
                .param("password", password))
                .andExpect(redirectedUrl("/secure/index"))
                .andExpect(new ResultMatcher() {
                    public void match(MvcResult mvcResult) throws Exception {
                        HttpSession session = mvcResult.getRequest().getSession();
                        SecurityContext securityContext = (SecurityContext) session.getAttribute(SEC_CONTEXT_ATTR);
                        Assert.assertEquals(securityContext.getAuthentication().getName(), username);
                        Optional<? extends GrantedAuthority> role_user =
                                securityContext.getAuthentication().getAuthorities().stream()
                                        .filter(r -> r.getAuthority().equals("ROLE_USER")).findFirst();
                        Assert.assertEquals(role_user.isPresent(), true);
                    }
                });
    }

    @Test
    public void userAuthenticatesInvalidCSRFToken() throws Exception {
        final String username = "admin";
        String password = "admin";
        mockMvc.perform(post("/login")
                .with(csrf().useInvalidToken())
                .param("username", username)
                .param("password", password))
                .andExpect(redirectedUrl(null));
    }

    @Test
    public void userAuthenticateFails() throws Exception {
        final String username = "user";
        mockMvc.perform(post("/login").param("username", username).param("password", "invalid")
                .with(csrf())
        ).andExpect(redirectedUrl("/login?error"))
                .andExpect(new ResultMatcher() {
                    public void match(MvcResult mvcResult) throws Exception {
                        HttpSession session = mvcResult.getRequest().getSession();
                        SecurityContext securityContext = (SecurityContext) session.getAttribute(SEC_CONTEXT_ATTR);
                        Assert.assertNull(securityContext);
                    }
                });
    }

}