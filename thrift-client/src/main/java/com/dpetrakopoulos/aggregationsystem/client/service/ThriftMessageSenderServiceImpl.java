package com.dpetrakopoulos.aggregationsystem.client.service;

import com.dpetrakopoulos.aggregationsystem.client.client.ThriftMsgClient;
import com.dpetrakopoulos.thrift.ThriftMessage;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.UUID;

@Service
public class ThriftMessageSenderServiceImpl implements ThriftMessageSenderService {

    private static final short thriftVersion = 1;

    @Autowired
    ThriftMsgClient thriftMessageClient;

    final Logger logger = LoggerFactory.getLogger(ThriftMessageSenderServiceImpl.class);

    @Scheduled(fixedRateString = "${millis.between.messages}")
    public void sendRandomMessage() {
        final ThriftMessage message = generateRandomMessage();
        try {
            thriftMessageClient.send(message);
        } catch (TException e) {
            logger.error(String.format("An error occurred while sending random message: {}", message.toString()), e.getMessage());
        }
    }

    private ThriftMessage generateRandomMessage() {
        final ThriftMessage result = new ThriftMessage();
        result.setV(thriftVersion);
        result.setTime(Calendar.getInstance().getTimeInMillis());
        result.setM(UUID.randomUUID().toString());
        return result;
    }

}
