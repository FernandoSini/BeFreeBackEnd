package com.befree.config;

import com.befree.serialization.converter.YamlJackson2HttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    //permitindo no header adicionar .json, .xml etc

    private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");


    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMessageConverter());
    }


    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //definindo as configuraçãoes
        //não vai ignorar o que vier no cabecalho e conteudo default vai ser json e aceitar os media types localhost///.json
        //content negotiation via Extension
//        configurer.favorParameter(false)
//                .ignoreAcceptHeader(false)
//                .parameterName("format")
//                .defaultContentType(MediaType.APPLICATION_JSON)
//                .mediaType("json", MediaType.APPLICATION_JSON)
//                .mediaType("xml", MediaType.APPLICATION_XML);

        //enviando via query parameter  example:localhost:8080/user/teste?format=xml (é só depois do interrogacao)
//        configurer.favorParameter(true)
//                .ignoreAcceptHeader(false)
//                .parameterName("format")
//                .useRegisteredExtensionsOnly(false)
//                .useRegisteredExtensionsOnly(false)
//                .defaultContentType(MediaType.APPLICATION_JSON)
//                .mediaType("json", MediaType.APPLICATION_JSON)
//                .mediaType("xml", MediaType.APPLICATION_XML)
//                .mediaType("yml", MEDIA_TYPE_YML);

        //enviando via header parameter(nos headers temos que passar o application/xml (exemplo))
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("yml", MEDIA_TYPE_YML);

    }

    //PERMITINDO O CORS
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "PATCH","DELETE", "OPTIONS")
        .allowedHeaders("*");
    }
}
