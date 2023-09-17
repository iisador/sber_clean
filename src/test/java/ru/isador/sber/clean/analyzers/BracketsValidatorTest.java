package ru.isador.sber.clean.analyzers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BracketsValidatorTest {

    @Test
    @DisplayName("Проверка на null")
    void testNullString() {
        MessageValidator<String> validator = new BracketsValidator();

        var npe = assertThrows(NullPointerException.class, () -> validator.validate(null));

        assertNotNull(npe, "NPE should be thrown");
        assertEquals(npe.getMessage(), "Text message should not be null", "Invalid exception message");
    }

    @Test
    @DisplayName("Проверка на пустую строку")
    void testEmptyString() {
        MessageValidator<String> validator = new BracketsValidator();
        var text = "";

        var e = assertThrows(IllegalArgumentException.class, () -> validator.validate(text));

        assertNotNull(e, "IllegalArgumentException should be thrown");
        assertEquals(e.getMessage(), "Message should not be empty", "Invalid exception message");
    }

    @Test
    @DisplayName("Проверка на пустую строку без содержательных символов")
    void testBlankString() {
        MessageValidator<String> validator = new BracketsValidator();
        var text = "   ";

        var e = assertThrows(IllegalArgumentException.class, () -> validator.validate(text));

        assertNotNull(e, "IllegalArgumentException should be thrown");
        assertEquals(e.getMessage(), "Message should not be empty", "Invalid exception message");
    }

    @Test
    @DisplayName("Успешная проверка простой строки")
    void testValidSimpleString() {
        MessageValidator<String> validator = new BracketsValidator();
        var text = "(123)";

        assertDoesNotThrow(() -> validator.validate(text));
    }

    @Test
    @DisplayName("Успешная проверка сложной строки")
    void testValidComplexString() {
        MessageValidator<String> validator = new BracketsValidator();
        var text = "Сшила( ма)ма (мне) штаны (из (б(е))резовой коры)";

        assertDoesNotThrow(() -> validator.validate(text));
    }

    @Test
    @DisplayName("Проверка на лишнюю открывающую скобку")
    void testExtraOpenBracketString() {
        MessageValidator<String> validator = new BracketsValidator();
        var text = "(Сшила мама мне штаны из (березовой коры)";

        var e = assertThrows(ValidationException.class, () -> validator.validate(text));

        assertNotNull(e, "ValidationException should be thrown");
        assertEquals(e.getMessage(), "Invalid character '('; position: 1;", "Invalid exception message");
    }

    @Test
    @DisplayName("Проверка на отсутствующую открывающую скобку")
    void testMissingOpenBracketString() {
        MessageValidator<String> validator = new BracketsValidator();
        var text = "Сшила) мама мне штаны из (березовой коры)";

        var e = assertThrows(ValidationException.class, () -> validator.validate(text));

        assertNotNull(e, "ValidationException should be thrown");
        assertEquals(e.getMessage(), "Invalid character ')'; position: 6;", "Invalid exception message");
    }

    @Test
    @DisplayName("Проверка пустые скобки")
    void testEmptyBracketsString() {
        MessageValidator<String> validator = new BracketsValidator();
        var text = "Сшила мама мне штаны из березовой коры()";

        var e = assertThrows(ValidationException.class, () -> validator.validate(text));

        assertNotNull(e, "ValidationException should be thrown");
        assertEquals(e.getMessage(), "Empty brackets at position: 39;", "Invalid exception message");
    }

    @Test
    @DisplayName("Проверка пустые скобки без содержательных символов")
    void testBlankBracketsString() {
        MessageValidator<String> validator = new BracketsValidator();
        var text = "Сшила мама мне штаны из березовой коры ( )";

        var e = assertThrows(ValidationException.class, () -> validator.validate(text));

        assertNotNull(e, "ValidationException should be thrown");
        assertEquals(e.getMessage(), "Empty brackets at position: 40;", "Invalid exception message");
    }
}
