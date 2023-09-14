package ru.isador.sber.clean.analyzers;

/**
 * Исключительная ситуация в процесса проверки сообщения.
 *
 * @since 1.0.0
 */
public class ValidationException extends Exception {

    /**
     * Простой конструктор для вывода сообщения об ошибке проверки. Пока для скорости не будем считать стек ошибки.
     *
     * @param message сообщение об ошибке.
     *
     * @since 1.0.0
     */
    protected ValidationException(String message) {
        super(message, null, false, false);
    }
}
