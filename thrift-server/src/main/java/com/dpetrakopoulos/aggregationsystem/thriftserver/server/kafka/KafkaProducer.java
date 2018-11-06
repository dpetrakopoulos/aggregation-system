package com.dpetrakopoulos.aggregationsystem.thriftserver.server.kafka;

import com.dpetrakopoulos.thrift.ThriftMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Sends thrift messages to a kafka topic.
 */

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, ThriftMessage> kafkaTemplate;

    final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    public ListenableFuture<SendResult<String, ThriftMessage>> send(ThriftMessage thriftMessage) {
        logger.info("Sending thrift message {} to kafka topic", thriftMessage);
        return kafkaTemplate.sendDefault(thriftMessage);
    }
}
