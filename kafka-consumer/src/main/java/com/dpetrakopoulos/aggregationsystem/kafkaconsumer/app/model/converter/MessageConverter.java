package com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.model.converter;

import com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.model.Message;
import com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.model.MessagePrimaryKey;
import com.dpetrakopoulos.thrift.ThriftMessage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Converts from a thrift message to a cassandra one
 */
@Component
public class MessageConverter implements Converter<ThriftMessage, Message> {

    @Override
    public Message convert(ThriftMessage thriftMessage) {
        return new Message(new MessagePrimaryKey(UUID.randomUUID(),new Date(thriftMessage.getTime())),
                thriftMessage.getV(),thriftMessage.getM());
    }
}