package com.befree.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;
import org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.web.socket.server.support.OriginHandshakeInterceptor;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
@EnableWebSocket
@Controller
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    //configuracao que vai controlar as mensagens
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // o /user é usado pelo metodo convert and send to user do simple messaging template
        //para prefixar todos os usuarios especificos de destinocom o /user
        registry.enableSimpleBroker("/user","/queue","/messages");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");

    }


    //aqui estamos registrando um endpoint Stomp /ws, para conectar com o stomp server
    //e também estamos permitindo as opções de fallback do Sockjs, então
    //outros servicos de mensagem podem ser usados caso o ws não esteja disponível
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws").setAllowedOrigins("*");
//        registry.addEndpoint("/socket");
        registry.setErrorHandler(new StompSubProtocolErrorHandler())
                .addEndpoint("/socket")
                .addInterceptors(new OriginHandshakeInterceptor())
                .addInterceptors(new HttpSessionHandshakeInterceptor())
//                .setAllowedOrigins("*")
                .setHandshakeHandler(new DefaultHandshakeHandler(new TomcatRequestUpgradeStrategy()));
//                .withSockJS().setWebSocketEnabled(true).;
        System.out.println("registry" + registry.setErrorHandler(new StompSubProtocolErrorHandler()).getHandlerMapping());

    }

//    @Override
//    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
//        WebSocketMessageBrokerConfigurer.super.configureWebSocketTransport(registry);
//
//    }

    //aqui estamos configurando um Json conversor de mensagens
    // que será usado pelo spring para  converter as mensagens de/para json
    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.getObjectMapper().registerModule(new JavaTimeModule());
        converter.setSerializedPayloadClass(String.class);
        converter.setContentTypeResolver(resolver);
        converter.setStrictContentTypeMatch(true);
        converter.setPrettyPrint(true);
        messageConverters.add(converter);
        return false;
    }

}
