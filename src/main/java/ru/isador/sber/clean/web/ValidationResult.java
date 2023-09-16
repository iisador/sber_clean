package ru.isador.sber.clean.web;

/**
 * Представление результата проверки сообщения.
 *
 * @param isCorrect результат проверки сообщения.
 *
 * @since 1.0.0
 */
public record ValidationResult(boolean isCorrect) {
}
