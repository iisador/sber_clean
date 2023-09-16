package ru.isador.sber.clean.web;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.isador.sber.clean.analyzers.MessageValidator;
import ru.isador.sber.clean.analyzers.ValidationException;
import ru.isador.sber.clean.web.massages.Message;

/**
 * Сервис проверки входящего сообщения.
 *
 * @since 1.0.0
 */
@ApplicationScoped
public class ValidationService {

    private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);

    private final MessageValidator<String> messageValidator;

    @Inject
    public ValidationService(MessageValidator<String> messageValidator) {
        this.messageValidator = messageValidator;
    }

    /**
     * Проверка текстового сообщения на соответствие правилам текущего {@link MessageValidator}.
     * <p>
     * Метод выполняется атомарно. Результат выполнения не может быть null. Возникшие, в результате проверки, исключительные ситуации выводятся в лог.
     *
     * @param message входящее текстовое сообщение.
     *
     * @return результат проверки.
     *
     * @since 1.0.0
     */
    public ValidationResult validate(Message<String> message) {
        try {
            messageValidator.validate(message.getMessage());
            return new ValidationResult(true);
        } catch (ValidationException | NullPointerException | IllegalArgumentException e) {
            logger.error("", e);
        }

        return new ValidationResult(false);
    }
}
