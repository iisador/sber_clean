package ru.isador.sber.clean.web;

import java.util.Objects;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import ru.isador.sber.clean.analyzers.MessageValidator;
import ru.isador.sber.clean.analyzers.ValidationException;

/**
 * Тестовый валидатор для проверки web-api.
 * <p>
 * Работает по следующим правилам:
 * <ol>
 *     <li>Если переданное сообщение == "invalid" - будет выброшено {@link ValidationException};</li>
 *     <li>Если переданное сообщение == "npe" - будет выброшено {@link NullPointerException};</li>
 *     <li>Если переданное сообщение == "illegal" - будет выброшено {@link IllegalArgumentException};</li>
 *     <li>В остальных случаях валидация пройдет успешно;</li>
 * </ol>
 *
 * @since 1.0.0
 */
@ApplicationScoped
@Alternative
@Priority(1)
public class TestMessageValidator implements MessageValidator<String> {

    @Override
    public void validate(String message) throws ValidationException {
        Objects.requireNonNull(message);
        if ("invalid".equals(message)) {
            throw new ValidationException("");
        }
        if ("illegal".equals(message)) {
            throw new IllegalArgumentException();
        }
    }
}
