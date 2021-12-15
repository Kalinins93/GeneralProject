package ru.neoflex.kafkatopostgresql.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.neoflex.kafkatopostgresql.model.ProcessedRequests;
import ru.neoflex.kafkatopostgresql.model.RestReceiveRequest;
import ru.neoflex.kafkatopostgresql.services.ServiceProcessedRequestsImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Optional;

@Component
class KafkaListeners {
    @Value(value = "${credit.limit}")
    private int CreditLimit;

    @Autowired
    ServiceProcessedRequestsImpl serviceProcessedRequests;
    Logger LOG = LoggerFactory.getLogger(KafkaListeners.class);

    @KafkaListener(
            topics = "requests",
            containerFactory = "restReceiveRequestConcurrentKafkaListenerContainerFactory")
    void listener(RestReceiveRequest restReceiveRequest) {
        try {
            LOG.info("InputMessage-", restReceiveRequest.toString());
            ProcessedRequests processedRequests = new ProcessedRequests();
            processedRequests.setId(restReceiveRequest.getId());
            processedRequests.setFio(restReceiveRequest.getFio());
            processedRequests.setBirthday(restReceiveRequest.getBirthday());
            processedRequests.setSumma(restReceiveRequest.getSumma());
            processedRequests.setCountMonth(restReceiveRequest.getCountMonth());
            boolean age = calculateAge(restReceiveRequest.getBirthday());
            if (processedRequests.getSumma() <= CreditLimit && age) {
                processedRequests.setStatus("ОДОБРЕН");
            } else {
                processedRequests.setStatus("НЕ ОДОБРЕН");
            }
            serviceProcessedRequests.addProcessedRequests(processedRequests);
        }
        catch (Exception e)
        {
            LOG.info(e.getMessage());
        }
    }

    public boolean calculateAge(
            String birthDate) {
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(LocalDate.parse(birthDate), currentDate).getYears();
        if (age > 18 && age < 80) {
            return true;
        } else {
            return false;
        }
    }
}