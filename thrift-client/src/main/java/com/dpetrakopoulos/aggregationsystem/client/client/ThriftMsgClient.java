package com.dpetrakopoulos.aggregationsystem.client.client;

import com.dpetrakopoulos.thrift.MessageService;
import com.dpetrakopoulos.thrift.ThriftMessage;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ThriftMsgClient {

    @Value("${thrift.server.address}")
    private String serverAddress;

    @Value("${thrift.server.port}")
    private int serverPort;

    final Logger logger = LoggerFactory.getLogger(ThriftMsgClient.class);

    public void send(final ThriftMessage thriftMessage) throws TException {
        TTransport transport;

        transport = new TSocket(serverAddress, serverPort);
        transport.open();

        TProtocol protocol = new TBinaryProtocol(transport);
        MessageService.Client client = new MessageService.Client(protocol);

        logger.info("Going to save random message");

        client.save(thriftMessage);

        logger.debug("Random message saved");

        transport.close();

    }
}
