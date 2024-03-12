package org.ac.cst8277.sun.guiquan.twitterclone;

import jakarta.annotation.Resource;
import org.ac.cst8277.sun.guiquan.twitterclone.utils.HttpResponseExtractor;
import org.ac.cst8277.sun.guiquan.twitterclone.utils.UMSConnector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.Optional;

@SpringBootTest
public class UMSConnectorTest {

    private String uriUmsHost="http://127.0.0.1";
    // get value from the configuration properties file
    private String uriUmsPort="9000";
    @Resource(name = "umsConnector")
    private UMSConnector umsConnector;

    /**
     * Problem:
     * When testing the code using postman tool, it will cause
     * java.lang.IllegalStateException: blockOptional() is blocking, which is not supported in thread reactor-http-nio-3
     * Solution:
     *ExecutorService executorService = Executors.newSingleThreadExecutor();
     *Future future = executorService.submit(() -> objectMono.block());
     *
     */
    @Test
    public void test() {
        /*String uri = "/getUserTokenByTokenId";
        WebClient client = WebClient.builder().baseUrl(uriUmsHost + ":" + uriUmsPort)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("token","39f3b313-9f55-4acb-80d6-80b3d84838fb")
                .build();
        // define the request to send and its deserialization (in .bodyToMono(...))
        Mono<Object> response = client.method(HttpMethod.GET).uri(uri).accept(MediaType.APPLICATION_JSON)
                .acceptCharset(Charset.forName("UTF-8")).retrieve().bodyToMono(Object.class);
        Optional<Object> objectOptional = response.blockOptional();
        UserTokenVo userTokenVo;
        if (objectOptional.isPresent()) {
            //System.err.println(objectOptional.get());
            userTokenVo = HttpResponseExtractor.extractDataFromHttpClientResponse(objectOptional.get(),
                    UserTokenVo.class);
            System.err.println(userTokenVo);
        }*/
    }
}
