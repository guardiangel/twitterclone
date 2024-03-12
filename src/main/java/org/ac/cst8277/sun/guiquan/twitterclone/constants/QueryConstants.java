package org.ac.cst8277.sun.guiquan.twitterclone.constants;

public final class QueryConstants {



    private QueryConstants() {
    }
    public static final String FIND_ALL_USERS =
            "SELECT s FROM UserEntity s";
    public static final String GET_MESSAGE_BY_PRODUCERID
            = "SELECT m FROM MessageEntity m WHERE m.producer_id=:producer_id";

    public static final String GET_MESSAGE_BY_SUBSCRIBERID
            = "SELECT m FROM MessageEntity m WHERE m.producer_id IN " +
            "(SELECT se.producers_id FROM SubscriptionsEntity se WHERE se.subscribers_id=:subscriber_id)";

}
