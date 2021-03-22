package com.befree.config;

import com.befree.security.jwt.JwtConfigurer;
import com.befree.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final long serialVersionUID = 1L;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        //A encriptacao da senha vai usar bcryptPassword encoder, ou seja, esta encriptografando senha
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        return bCrypt;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()//autorizando requisicoes
                .antMatchers("/auth/login", "/api-docs/**", "swagger-ui.html**",
                        "/auth/register","/graduations").permitAll()
                // os endpoints que permitirao acesso o/** permitira acesso a tudo de api docs, ou seja,
                //(continuacao de cima) todas as urls compativeis com essas definidas vai permitir acesso sem autenticacao
                .antMatchers("/api/**").authenticated()//todos que chegarem em /api precisam estar autenticados, todos os /apis precisam de senha
                .and()
                .apply(new JwtConfigurer(tokenProvider))
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/auth/login").permitAll();
//        http
//                .csrf().disable()
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .httpBasic();


    }
}
