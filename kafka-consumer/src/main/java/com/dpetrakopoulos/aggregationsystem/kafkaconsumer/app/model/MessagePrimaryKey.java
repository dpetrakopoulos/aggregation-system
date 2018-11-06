package com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * The Cassandra composite (compound) key
 */
@PrimaryKeyClass
@Getter
@Builder
@ToString
public class MessagePrimaryKey implements Serializable {

    @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID id;

    @PrimaryKeyColumn(name = "date", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Date eventDate;

    public MessagePrimaryKey(UUID id, Date eventDate) {
        this.id = id;
        this.eventDate = eventDate;
    }
}
