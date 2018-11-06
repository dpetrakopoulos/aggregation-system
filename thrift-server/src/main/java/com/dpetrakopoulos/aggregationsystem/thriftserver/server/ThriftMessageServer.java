package com.dpetrakopoulos.aggregationsystem.thriftserver.server;

import com.dpetrakopoulos.thrift.MessageService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ThriftMessageServer {
    @Value("${thrift.server.port}")
    private int thriftServerPort;

    @Autowired
    MessageService.Iface messageService;

    final Logger logger = LoggerFactory.getLogger(ThriftMessageServer.class);

    private TServer server;

    public void start() throws TTransportException {
        logger.info("Listening for messages on port {}", thriftServerPort);
        final TServerTransport serverTransport = new TServerSocket(thriftServerPort);
        final TServer.Args tsArgs = new TServer.Args(serverTransport)
                .processor(new MessageService.Processor<>(messageService));
        server = new TSimpleServer(tsArgs);
        server.serve();
    }
}
