package com.dpetrakopoulos.aggregationsystem.thriftserver.server.service;

import com.dpetrakopoulos.aggregationsystem.thriftserver.server.kafka.KafkaProducer;
import com.dpetrakopoulos.thrift.InvalidOperationException;
import com.dpetrakopoulos.thrift.MessageService;
import com.dpetrakopoulos.thrift.ThriftMessage;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Sends received thrift messages to kafka through producer.
 */
@Service
public class ThriftMessageServiceImpl implements MessageService.Iface {

    @Autowired
    KafkaProducer kafkaProducer;

    final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Override
    public void save(ThriftMessage thriftMessage) throws InvalidOperationException, TException {

        ListenableFuture<SendResult<String, ThriftMessage>> messageFuture = kafkaProducer.send(thriftMessage);
        messageFuture.addCallback(new ListenableFutureCallback<SendResult<String, ThriftMessage>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.error("Failed sending message {} to kafka", thriftMessage);
            }

            @Override
            public void onSuccess(SendResult<String, ThriftMessage> result) {
                logger.info("Successfully sent message {} to kafka", thriftMessage);
            }
        });

    }
}