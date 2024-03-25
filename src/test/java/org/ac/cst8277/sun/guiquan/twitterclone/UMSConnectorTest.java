package org.ac.cst8277.sun.guiquan.twitterclone;

import jakarta.annotation.Resource;
import org.ac.cst8277.sun.guiquan.twitterclone.reponseVo.UserTokenVo;
import org.ac.cst8277.sun.guiquan.twitterclone.utils.HttpResponseExtractor;
import org.ac.cst8277.sun.guiquan.twitterclone.utils.UMSConnector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@SpringBootTest
public class UMSConnectorTest {

    private String uriUmsHost = "http://127.0.0.1";
    // get value from the configuration properties file
    private String uriUmsPort = "8080";
    @Resource(name = "umsConnector")
    private UMSConnector umsConnector;

    /**
     * Problem:
     * When testing the code using postman tool, it will cause
     * java.lang.IllegalStateException: blockOptional() is blocking, which is not supported in thread reactor-http-nio-3
     * Solution:
     * ExecutorService executorService = Executors.newSingleThreadExecutor();
     * Future future = executorService.submit(() -> objectMono.block());
     */
    @Test
    public void test() {
        String uri = "/getUserTokenByTokenId";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJHdWlxdWFuIFN1biIsInJvbGVzIjpbIlJJQkVSIl0sImlhdCI6MTcxMTM5NTg4MywiZXhwIjoxNzExMzk2NzgzfQ.H3TsB29Z52gXAU4Fn84fUhAsY9XtRvYgLuWLadU-3HU";
        WebClient client = WebClient.builder().baseUrl(uriUmsHost + ":" + uriUmsPort)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("token", token)
//                .defaultHeader(HttpHeaders.AUTHORIZATION,
//                        "Bearer " + token)
                .build();
        // define the request to send and its deserialization (in .bodyToMono(...))
        Flux<Object> response = client.method(HttpMethod.GET).uri(uri).accept(MediaType.APPLICATION_JSON)
                .acceptCharset(Charset.forName("UTF-8")).retrieve().bodyToFlux(Object.class);

        try {
            List<Object> list = response.collectList().toFuture().get();
            list.forEach(o -> {
                System.err.println(o);
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
