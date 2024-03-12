package org.ac.cst8277.sun.guiquan.twitterclone.services;

import jakarta.annotation.Resource;
import org.ac.cst8277.sun.guiquan.twitterclone.UserTokenVo;
import org.ac.cst8277.sun.guiquan.twitterclone.entities.MessageEntity;
import org.ac.cst8277.sun.guiquan.twitterclone.repositories.MessageRepository;
import org.ac.cst8277.sun.guiquan.twitterclone.utils.HttpResponseExtractor;
import org.ac.cst8277.sun.guiquan.twitterclone.utils.UMSConnector;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;

@Service("messageService")
public class MessageService {

    @Resource(name = "messageRepository")
    private MessageRepository messageRepository;

    @Resource(name = "umsConnector")
    private UMSConnector umsConnector;

    public boolean verifyToken(String token) {
        String uri = "/getUserTokenByTokenId";
        Mono<Object> objectMono = umsConnector.retrieveUmsData(uri, token);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(() -> objectMono.block());
        Map<String, Object> tokenInfoMap;
        UserTokenVo userTokenVo;
        try {
            tokenInfoMap = (Map<String, Object>) future.get();
            userTokenVo = HttpResponseExtractor.extractDataFromHttpClientResponse(tokenInfoMap, UserTokenVo.class);
            System.err.println("userTokenVo=" + userTokenVo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.err.println("tokenInfo=" + tokenInfoMap);
        executorService.shutdown();
        return userTokenVo != null;
    }

    public List<MessageEntity> getMessagesByProducerId(String producer_id) {
        List<MessageEntity> messageEntities
                = messageRepository.getMessagesByProducerId(producer_id);
        return messageEntities;
    }

    public List<MessageEntity> getAllMessages() {
        return (List<MessageEntity>) messageRepository.findAll();
    }

    public List<MessageEntity> getMessagesBySubscriberId(String subscriber_id) {
        List<MessageEntity> messageEntities
                = messageRepository.getMessagesBySubscriberId(subscriber_id);
        return messageEntities;
    }
}
