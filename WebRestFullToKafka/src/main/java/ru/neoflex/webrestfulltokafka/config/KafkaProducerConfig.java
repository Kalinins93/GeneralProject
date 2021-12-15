package ru.neoflex.webrestfulltokafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.neoflex.webrestfulltokafka.kafka.KafkaMessagePublisher;
import ru.neoflex.webrestfulltokafkaapi.model.RestReceiveRequest;

import java.util.HashMap;
import java.util.Map;
@EnableAsync
@Configuration
public class KafkaProducerConfig {
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    @Value(value = "${kafka.retries}")
    private String retries;
    @Bean
    public ProducerFactory<String, RestReceiveRequest> producerFactoryRequestsApi() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        configProps.put(JsonSerializer.TYPE_MAPPINGS, "restreceiverequest:ru.neoflex.webrestfulltokafkaapi.model.RestReceiveRequest");
        configProps.put(ProducerConfig.RETRIES_CONFIG,retries);
        return new DefaultKafkaProducerFactory<>(configProps);
    }


    @Bean(name = "kafkaAsyncPublisherRequestsApi")
    public KafkaTemplate<String, RestReceiveRequest> kafkaAsyncPublisherRequestsApi() {
        return new KafkaTemplate<>(producerFactoryRequestsApi());
    }
    @Bean
    public KafkaMessagePublisher kafkaMessagePublisher(){
        return new KafkaMessagePublisher();
    }

}