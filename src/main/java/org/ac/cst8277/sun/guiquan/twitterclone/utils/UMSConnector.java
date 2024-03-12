package org.ac.cst8277.sun.guiquan.twitterclone.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.nio.charset.Charset;

@Configuration("umsConnector")
public class UMSConnector {

    // get value from the configuration properties file
    @Value("${ums.host}")
    private String uriUmsHost;
    // get value from the configuration properties file
    @Value("${ums.port}")
    private String uriUmsPort;
    @PostConstruct
    public void print() {
        System.err.println("uriUmsHost=" + uriUmsHost + ",umsPort=" + uriUmsPort);
    }
    public Mono<Object> retrieveUmsData(String uri, String token) {
        // prepare the HTTP Client and define some HTTP headers for it
        WebClient client = WebClient.builder().baseUrl(uriUmsHost + ":" + uriUmsPort)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("token", token).build();
        // define the request to send and its deserialization (in .bodyToMono(...))
        Mono<Object> response = client.method(HttpMethod.GET).uri(uri).accept(MediaType.APPLICATION_JSON)
                .acceptCharset(Charset.forName("UTF-8")).retrieve().bodyToMono(Object.class);
        //return the Mono instance, which is reactive, i.e. you will get actual values when you block it
        return response;
    }
}
