package com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.service;

import com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.model.Message;

public interface MessageService {
    void save(Message message);
}
