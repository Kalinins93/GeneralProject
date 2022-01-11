package ru.neoflex.kafkastream.kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;
import ru.neoflex.kafkastream.model.RestReceiveRequest;
import ru.neoflex.kafkastream.service.CreditRequestService;

@Configuration
public class TopologyConfiguration {

    @Value("${spring.kafka.consumer.input-topic}")
    private String inputTopic;
    @Value("${spring.kafka.consumer.output-topic}")
    private String outputTopic;

    @Bean
    public KStream<String, RestReceiveRequest> kStream(StreamsBuilder streamsBuilders) {
        KStream<String, RestReceiveRequest> stream = streamsBuilders.
                stream(inputTopic,
                        Consumed.with(Serdes.String(),
                                new JsonSerde<>(RestReceiveRequest.class)));
        stream
                .filter((k, v) -> CreditRequestService.isYounger(v))
                .to(outputTopic);
        return stream;
    }
}