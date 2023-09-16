package ru.isador.sber.clean.web.massages;

/**
 * Простое представление текстового сообщения.
 *
 * @since 1.0.0
 */
public class TextMessage extends Message<String> {

    public TextMessage() {
    }

    public TextMessage(String message) {
        this.message = message;
    }
}
