package ru.neoflex.kafkatopostgresql.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.neoflex.kafkatopostgresql.model.RestReceiveRequest;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    public ConsumerFactory<String, RestReceiveRequest> restReceiveRequestConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "test");
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        props.put(JsonSerializer.TYPE_MAPPINGS, "restreceiverequest:ru.neoflex.kafkatopostgresql.RestReceiveRequest");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "ru.neoflex.kafkatopostgresql.RestReceiveRequest");
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(RestReceiveRequest.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RestReceiveRequest>  restReceiveRequestConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, RestReceiveRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setMissingTopicsFatal(false);
        factory.setConsumerFactory(restReceiveRequestConsumerFactory());
        return factory;
    }

}


