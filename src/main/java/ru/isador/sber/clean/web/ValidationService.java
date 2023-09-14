package ru.isador.sber.clean.web;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.isador.sber.clean.analyzers.MessageValidator;
import ru.isador.sber.clean.analyzers.ValidationException;

@ApplicationScoped
public class ValidationService {

    private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);

    private final MessageValidator<String> messageValidator;

    @Inject
    public ValidationService(MessageValidator<String> messageValidator) {
        this.messageValidator = messageValidator;
    }

    public ValidationResult validate(Message message) {
        try {
            messageValidator.validate(message.getText());
            return new ValidationResult(true);
        } catch (ValidationException | NullPointerException e) {
            logger.error("", e);
        }

        return new ValidationResult(false);
    }
}
