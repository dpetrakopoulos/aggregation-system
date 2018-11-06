package com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.service;

import com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.model.Message;
import com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;

    final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    public void save(Message message) {
        logger.info("Persisting message {} to Cassandra", message);
        try {
            messageRepository.save(message);
        } catch (RuntimeException ex) {
            logger.error(String.format("An error occurred while persisting message: {}", message.toString()), ex.getMessage());
        }

    }
}