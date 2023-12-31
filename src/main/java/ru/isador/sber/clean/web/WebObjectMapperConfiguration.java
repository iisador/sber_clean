package ru.isador.sber.clean.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.inject.Singleton;

import io.quarkus.jackson.ObjectMapperCustomizer;
import ru.isador.sber.clean.web.messages.TextMessage;
import ru.isador.sber.clean.web.messages.TextMessageBuilder;

/**
 * Дополнительная настройка для корректной десериализации входящих сообщений.
 *
 * @since 1.0.0
 */
@Singleton
public class WebObjectMapperConfiguration implements ObjectMapperCustomizer {

    @Override
    public void customize(ObjectMapper objectMapper) {
        SimpleModule m = new SimpleModule("messageModule");
        m.addDeserializer(TextMessage.class, new TextMessageBuilder());
        objectMapper.registerModule(m);
    }
}
