package com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.kafka;

import com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.model.Message;

import com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.service.MessageService;
import com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.service.MessageServiceImpl;
import com.dpetrakopoulos.thrift.ThriftMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Listens to topic 'kafka.consume.topic' for thrift messages and saves them through MessageService
 */
@Component
public class KafkaConsumer {
    final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    private Converter<ThriftMessage, Message> msgConverter;

    // Convert thrift message to cassandra one and save
    @KafkaListener(topics = "${kafka.consume.topic}", group = "${kafka.consumer.group.id}")
    public void consume(ThriftMessage message) {
        logger.info("About to convert message {} to cassandra one and save", message);
        messageService.save(msgConverter.convert(message));
    }
}