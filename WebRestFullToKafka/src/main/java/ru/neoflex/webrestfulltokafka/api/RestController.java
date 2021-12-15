package ru.neoflex.webrestfulltokafka.api;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.neoflex.webrestfulltokafka.Exceptions.ExternalSystemException;
import ru.neoflex.webrestfulltokafka.kafka.KafkaMessagePublisher;
import ru.neoflex.webrestfulltokafkaapi.api.RequestsApi;
import ru.neoflex.webrestfulltokafkaapi.model.RestReceiveRequest;
import ru.neoflex.webrestfulltokafkaapi.model.RestResponse;
import ru.neoflex.webrestfulltokafkaapi.model.StatusMessage;

@AllArgsConstructor
@org.springframework.web.bind.annotation.RestController
public class RestController implements RequestsApi {
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(
            RestController.class);
    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;
    @Override
    public Mono<ResponseEntity<RestResponse>> restReceiveRequest(Mono<RestReceiveRequest> restReceiveRequest, ServerWebExchange exchange) {
        try {
            return restReceiveRequest.flatMap(request -> {
                logger.info("Request JSON-" + request.toString());
               // sendMessage(request.toString());
                kafkaMessagePublisher.sendMessage(request);
                StatusMessage statusMessage = new StatusMessage();
                statusMessage.setCode("0");
                RestResponse resp = new RestResponse();
                resp.setId(request.getId());
                resp.setStatus(statusMessage);
                Mono<ResponseEntity<RestResponse>> just = Mono.just(ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resp));
                return just;
            });
        } catch (Exception throwable) {
            Mono monoError = null;
            if (throwable instanceof ExternalSystemException) {
                RestResponse responseError = new RestResponse();
                StatusMessage statusMessage = new StatusMessage();
                statusMessage.setCode("ERRORS");
                restReceiveRequest.flatMap(request ->
                        {
                            logger.info("Request JSON-" + request.toString());
                            responseError.setId(request.getId());
                            statusMessage.setText("Собщение не отправлена проблема с брокером сообщений");
                            responseError.setStatus(statusMessage);
                            return null;
                        }
                );
               monoError =  Mono.just(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(responseError));

            }
            else {
                RestResponse responseError = new RestResponse();
                StatusMessage statusMessage = new StatusMessage();
                statusMessage.setCode("ERRORS");
                restReceiveRequest.flatMap(request ->
                {
                    logger.info("Request JSON-" + request.toString());
                    responseError.setId(request.getId());
                    statusMessage.setText("Собщение не отправлена проблема с брокером сообщений");
                    responseError.setStatus(statusMessage);
                    return null;
                });
                        monoError =  Mono.just(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(responseError));
            }
            return monoError;
        }
    }


}
