package ru.neoflex.kafkastream.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import ru.neoflex.webrestfulltokafka.Exceptions.ExternalSystemException;
import ru.neoflex.webrestfulltokafkaapi.model.RestReceiveRequest;

public class KafkaMessagePublisher {
    @Autowired
    private KafkaTemplate<String,RestReceiveRequest> kafkaTemplate;
    @Value("${kafka.topic}")
    private String topic;
    public ListenableFuture sendMessage(RestReceiveRequest requestsApi) {
        ListenableFuture listenableFuture = kafkaTemplate.send(
                topic,
                "RestReceiveRequest",
                requestsApi);

        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<?, ?>>() {

            @Override
            public void onSuccess(final SendResult<?, ?> message) {
                System.out.println("Sent");
            }

            @Override
            public void onFailure(final Throwable throwable) {
                throw new ExternalSystemException("Message sending failed");
            }

        });
        return listenableFuture;
    }


}