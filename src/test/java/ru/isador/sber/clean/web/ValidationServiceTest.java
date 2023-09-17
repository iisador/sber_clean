package ru.isador.sber.clean.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.isador.sber.clean.analyzers.MessageValidator;
import ru.isador.sber.clean.analyzers.ValidationException;
import ru.isador.sber.clean.web.messages.TextMessage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@SuppressWarnings("unchecked")
class ValidationServiceTest {

    @Test
    @DisplayName("Проверка успешной валидации")
    void testValidationSuccess() {
        MessageValidator<String> validator = mock(MessageValidator.class);
        ValidationService validationService = new ValidationService(validator);
        ValidationResult expectedResult = new ValidationResult(true);

        ValidationResult actualResult = validationService.validate(new TextMessage("123"));

        assertEquals(expectedResult, actualResult, "Unexpected validation result. Should be true");
    }

    @Test
    @DisplayName("Проверка ответа приве возникновении ValidationException")
    void testValidationException() throws ValidationException {
        MessageValidator<String> validator = mock(MessageValidator.class);
        doThrow(new ValidationException("123")).when(validator).validate(any());
        ValidationService validationService = new ValidationService(validator);
        ValidationResult expectedResult = new ValidationResult(false);

        ValidationResult actualResult = validationService.validate(new TextMessage("123"));

        assertEquals(expectedResult, actualResult, "Unexpected validation result. Should be false");
    }

    @Test
    @DisplayName("Проверка ответа приве возникновении NPE")
    void testNPEException() throws ValidationException {
        MessageValidator<String> validator = mock(MessageValidator.class);
        doThrow(new NullPointerException()).when(validator).validate(any());
        ValidationService validationService = new ValidationService(validator);
        ValidationResult expectedResult = new ValidationResult(false);

        ValidationResult actualResult = validationService.validate(new TextMessage());

        assertEquals(expectedResult, actualResult, "Unexpected validation result. Should be false");
    }

    @Test
    @DisplayName("Проверка ответа приве возникновении IllegalArgumentException")
    void testIllegalArgumentException() throws ValidationException {
        MessageValidator<String> validator = mock(MessageValidator.class);
        doThrow(new IllegalArgumentException()).when(validator).validate(any());
        ValidationService validationService = new ValidationService(validator);
        ValidationResult expectedResult = new ValidationResult(false);

        ValidationResult actualResult = validationService.validate(new TextMessage());

        assertEquals(expectedResult, actualResult, "Unexpected validation result. Should be false");
    }
}
