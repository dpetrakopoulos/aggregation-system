package com.dpetrakopoulos.aggregationsystem.kafkaconsumer.app.kafka;

import com.dpetrakopoulos.thrift.ThriftMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.consumer.group.id}")
    private String consumerGroupId;

    @Bean
    public ConsumerFactory<String, ThriftMessage> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        DefaultKafkaConsumerFactory<String, ThriftMessage> consumerFactory = new DefaultKafkaConsumerFactory<>(props);
        consumerFactory.setValueDeserializer(new JsonDeserializer<>(ThriftMessage.class));
        return consumerFactory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ThriftMessage> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ThriftMessage> containerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(consumerFactory());
        return containerFactory;
    }
}