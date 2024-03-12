package org.ac.cst8277.sun.guiquan.twitterclone.repositories;

import org.ac.cst8277.sun.guiquan.twitterclone.constants.QueryConstants;
import org.ac.cst8277.sun.guiquan.twitterclone.entities.MessageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "messageRepository")
public interface MessageRepository extends CrudRepository<MessageEntity, String> {
    @Query(value = QueryConstants.GET_MESSAGE_BY_PRODUCERID)
    List<MessageEntity> getMessagesByProducerId(@Param("producer_id") String producer_id);

    @Query(value = QueryConstants.GET_MESSAGE_BY_SUBSCRIBERID)
    List<MessageEntity> getMessagesBySubscriberId(@Param("subscriber_id") String subscriber_id);

}
