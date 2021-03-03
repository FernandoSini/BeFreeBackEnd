package com.befree.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    /*
     * Aqui  como estammos extendendo o Genéric filter Bean,
     * então precisamos sobrescrever o doFilter
     * No doFilter nos pegamos o token que vem do cabecalho da Requisição
     *  e verificamos se ele é diferente de null e válido.
     * Caso seja válido vamos tentar obter a autenticacao,
     *  e se estiver tudo certo com o token então a autenticação é setada no contexto
     *
     * */

    @Autowired
    private JwtTokenProvider tokenProvider;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //filtrar o trafego http e pegando o token no cabecalho da requisicao
        String token = tokenProvider.resolveToken((HttpServletRequest) request);
        //verificacao se o token for diferente de nulo e valido entao vamos pegar a authenticacao
        if (token != null && tokenProvider.validateToken(token)) {
            //tentando obter a autenticacao a partir do token passado
            Authentication authentication = tokenProvider.getAuthentication(token);
            //se estiver tudo ok com o token e autenticacao for  valida ele vai pegar a autenticacao
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        //o filtro tem que ficar por fora das verificações se não vai retornar 200 toda hora
        //aplicando o filtro
        chain.doFilter(request, response);
    }

    public JwtTokenFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

}
