package org.ac.cst8277.sun.guiquan.twitterclone.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
@Table(name = "messages", schema = "messages", catalog = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "created")
    private Long created;

    @Column(name = "producer_id")
    private String producer_id;


}
