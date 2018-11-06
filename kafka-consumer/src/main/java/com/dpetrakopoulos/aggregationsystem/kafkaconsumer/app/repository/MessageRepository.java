package com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.repository;

import com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.model.Message;
import com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.model.MessagePrimaryKey;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, MessagePrimaryKey> {
}
