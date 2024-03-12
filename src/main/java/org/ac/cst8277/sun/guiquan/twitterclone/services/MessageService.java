package org.ac.cst8277.sun.guiquan.twitterclone.services;

import jakarta.annotation.Resource;
import org.ac.cst8277.sun.guiquan.twitterclone.UserTokenVo;
import org.ac.cst8277.sun.guiquan.twitterclone.entities.MessageEntity;
import org.ac.cst8277.sun.guiquan.twitterclone.repositories.MessageRepository;
import org.ac.cst8277.sun.guiquan.twitterclone.utils.HttpResponseExtractor;
import org.ac.cst8277.sun.guiquan.twitterclone.utils.UMSConnector;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service("messageService")
public class MessageService {

    @Resource(name = "messageRepository")
    private MessageRepository messageRepository;

    private String uri = "/getUserTokenByTokenId";

    @Resource(name = "umsConnector")
    private UMSConnector umsConnector;

    public boolean verifyToken(String token) {
        Mono<Object> objectMono = umsConnector.retrieveUmsData(uri, token);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(() -> objectMono.block());
        Map<String, Object> tokenInfoMap;
        UserTokenVo userTokenVo;
        try {
            tokenInfoMap = (Map<String, Object>) future.get();
            userTokenVo = HttpResponseExtractor.extractDataFromHttpClientResponse(tokenInfoMap, UserTokenVo.class);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
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
