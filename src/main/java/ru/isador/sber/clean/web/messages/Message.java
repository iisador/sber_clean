package ru.isador.sber.clean.web.messages;

/**
 * Общий класс представления входящего сообщения.
 *
 * @param <T> тело сообщения.
 *
 * @since 1.0.0
 */
public class Message<T> {

    /** Сообщение. */
    protected T message;

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
