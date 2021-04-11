package com.befree.security.jwt;

import com.befree.exceptions.InvalidJwtAuthenticationException;
import com.befree.exceptions.UserNotFoundException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    // essas variaveis(secretKey e validityInMiliseconds) vao ser inicializada com esses valores,
    // caso nao seja passado os valores eles serão pegos dps dos :
    @Value("${security.jwt.token.secret-key:mySecretKey}")
    private String secretKey = "mySecretKey";

    @Value("${security.jwt.token.secret-key:3600000}")
    private long validityInMillyseconds = 3600000;//1hora


    @Autowired
    private UserDetailsService userDetailsService;


    @PostConstruct
    //após injetar a dependencia, iremos encodar a secrete key
    public void init() {
        //encriptografando a chave
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //criando o token com userName e suas funções
    public String createToken(String userName, List<String> roles) {
        //definindo a jwt claims que é a certificacao
        Claims claims = Jwts.claims().setSubject(userName);
        claims.put("roles", roles);

        //Pegando o tempo de duracao do token
        // que é a hora atual + o tempo em milisegundos que é 1 hora
        Date now = new Date();
        Date validade = new Date(now.getTime() + validityInMillyseconds);

        //retornando o token
        return Jwts.builder().setClaims(claims)
                .setIssuedAt(now)//quando o token foi criado
                .setExpiration(validade)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //criando um token para o event owner
    public String createTokenEventOwner(String eventOwnerUsername, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(eventOwnerUsername);
        claims.put("roles", roles);
        //param serve para diferenciar se é requisicao vinda de um param eventowner ou user
        Date now = new Date();
        Date validade = new Date(now.getTime() + validityInMillyseconds);

        return Jwts.builder().setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validade)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    //pegar a authenitcation
    public Authentication getAuthentication(String token) {
        //vamos procurar pelo usuario e associá-lo ao token
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User was deleted or not found in our database with this token");
        }
    }

    //parei aqui
    public String getUsername(String token) {
        //primeiro é o tipo de token,os dados sao presenciados no meio do token,
        // e o final são as chaves secretas, observar a estrutura do token no site do jwt
        //pegando o usuario presente no token
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {

        //vamos definir o token como bearer
        //ou seja, ele vai pegar o parametro pelo cabeçalho da request chamdo authorization
        String bearerToken = request.getHeader("Authorization");
        // e fazer uma verification, se for diferente de nulo e comecar com Bearer ele vai autorizar o acesso
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            //vai pegar o token depois do Bearer kjdjskahdkasjdhjak
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    //validando o token
    public Boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (ExpiredJwtException e) {

//            throw new ExpiredJwtException(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getHeader(),
//                    (Claims) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token), e.getMessage());
            throw new InvalidJwtAuthenticationException("Expired or invalid token! \n" + "error: " + e.getMessage());

        }
    }
}
