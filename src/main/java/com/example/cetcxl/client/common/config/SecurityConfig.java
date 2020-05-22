package com.example.cetcxl.client.common.config;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.io.PrintWriter;

@Configuration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(
                        (req, res, auth) -> {
                            Object principal = auth.getPrincipal();
                            res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                            PrintWriter out = res.getWriter();
                            out.write(JSON.toJSONString(principal));
                            out.flush();
                            out.close();
                        }
                )
                .and()
                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .and();
    }

}
