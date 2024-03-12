package org.ac.cst8277.sun.guiquan.twitterclone.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "subscriptions", schema = "messages", catalog = "messages")
@IdClass(SubscriptionsEntityPK.class)
public class SubscriptionsEntity {
    @Id
    @Column(name = "producers_id")
    private String producers_id;

    @Id
    @Column(name = "subscribers_id")
    private String subscribers_id;


}
