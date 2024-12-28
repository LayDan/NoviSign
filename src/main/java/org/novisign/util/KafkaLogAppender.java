package org.novisign.util;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.encoder.Encoder;
import lombok.Setter;
import org.novisign.configuration.KafkaLoggingService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class KafkaLogAppender extends AppenderBase<ILoggingEvent> implements ApplicationContextAware {

    @Setter
    private Encoder<ILoggingEvent> encoder;
    private static KafkaLoggingService kafkaLoggingService;

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        if (kafkaLoggingService != null) {
            kafkaLoggingService.sendLogs(new String(encoder.encode(iLoggingEvent)));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        kafkaLoggingService = applicationContext.getAutowireCapableBeanFactory().getBean(KafkaLoggingService.class);
    }
}
