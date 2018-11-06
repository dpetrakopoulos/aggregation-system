package com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Represents the Cassandra table
 */
@Table
@Getter
@Builder
@ToString
public class Message {
    @PrimaryKey
    private MessagePrimaryKey messagePrimaryKey;

    @Column("v")
    private short version;

    @Column("m")
    private String message;

    public Message(MessagePrimaryKey messagePrimaryKey, short version, String message) {
        this.messagePrimaryKey = messagePrimaryKey;
        this.version = version;
        this.message = message;
    }
}