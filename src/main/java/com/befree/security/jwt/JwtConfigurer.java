package com.befree.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    /*Como estamos extendendo o security configurer adapter,
 então é necessário sobre escrever o configure.
 Nessa classe estamos adicionando um novo filtro de trafego na nossa api e precisa ser feito
 antes do acesso aos endpoints, por isso addFilterBefore
* */
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public JwtConfigurer(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
   /*Instanciando um novo filtro para todos os trafegos http*/
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);

        //adicionando esse filtro às politicas de segurança
        httpSecurity.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
