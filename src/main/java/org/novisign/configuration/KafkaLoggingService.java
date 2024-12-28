package org.novisign.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaLoggingService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    private final ObjectMapper objectMapper;

    public KafkaLoggingService(KafkaTemplate<String, String> kafkaTemplate,
                               @Value("${spring.kafka.template.logs}") String topic, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    public void sendLogs(String logMessage) {
        kafkaTemplate.send(topic, objectMapper.writeValueAsString(logMessage));
    }

}
