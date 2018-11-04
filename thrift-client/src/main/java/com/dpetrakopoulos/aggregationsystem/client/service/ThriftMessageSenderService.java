package com.dpetrakopoulos.aggregationsystem.client.service;

public interface ThriftMessageSenderService {

    /**
     *  The function that sends an arbitrary generated message through the thrift message client class.
     *  This function is scheduled to send the message every specific amount of milliseconds (this is configurable).
     *
     */
    void sendRandomMessage();
}
