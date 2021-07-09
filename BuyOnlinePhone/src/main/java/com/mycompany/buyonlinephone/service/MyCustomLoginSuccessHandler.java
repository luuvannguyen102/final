/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.service;

import com.mycompany.buyonlinephone.entities.AccountEntity;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 *
 * @author Admin
 */
public class MyCustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public MyCustomLoginSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Autowired
    private AccountService accountService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session != null) {
            String redirectUrl = (String) session.getAttribute("url_prior_login");
            Object principal = authentication.getPrincipal();
            String username = principal.toString();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }

            AccountEntity accountEntity = accountService.getAccountInfo(username);
            session.setAttribute("userName", accountEntity);
            if (redirectUrl != null) {
                // we do not forget to clean this attribute from session
                session.removeAttribute("url_prior_login");
                // then we redirect
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);

                session.setAttribute("userName", accountEntity.getFullName());
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
