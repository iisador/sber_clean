package ru.isador.sber.clean.analyzers;

/**
 * Общий интерфейс, определяющий набор правил, для анализа сообщения.
 *
 * @since 1.0.0
 */
public interface MessageValidator<T> {

    /**
     * Проанализировать сообщение.
     *
     * @param message входное сообщение.
     *
     * @throws ValidationException если возникла ошибка в процессе анализа текста, либо сообщение не удовлетворяет правилам анализа.
     * @since 1.0.0
     */
    void validate(T message) throws ValidationException;
}
