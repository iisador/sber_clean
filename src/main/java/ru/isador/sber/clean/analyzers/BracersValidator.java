package ru.isador.sber.clean.analyzers;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Проверка текстового сообщения на корректность расстановки скобок.
 *
 * @since 1.0.0
 */
@ApplicationScoped
public class BracersValidator implements MessageValidator<String> {

    private static final Logger logger = LoggerFactory.getLogger(BracersValidator.class);

    /**
     * Проверка текстового сообщения на корректность расстановки скобок. Данный метод реализует на самый лучший алгоритм проверки, а именно O(n), зато позволяет легко определить
     * позицию ошибочного символа и не использует много памяти.
     * <p>
     * Проверка считается не пройденной в следующих случаях:
     * <ol>
     *     <li>Присутствуют лишние (или отсутствуют нужные) открывающие или закрывающие скобки.</li>
     *     <li>Между скобками нет содержательного текста.</li>
     * </ol>
     *
     * @param message текстовое сообщение. Передаваемое значение не может быть пустым или null.
     *
     * @throws ValidationException      если сообщение не прошло проверку.
     * @throws NullPointerException     если сообщение == null.
     * @throws IllegalArgumentException если сообщение пустое.
     * @since 1.0.0
     */
    @Override
    public void validate(String message) throws ValidationException {
        Objects.requireNonNull(message, "Text message should not be null");
        if (message.isBlank()) {
            throw new IllegalArgumentException("Message should not be empty");
        }

        Deque<Integer> stack = new LinkedList<>();
        boolean symbol = false;
        for (int i = 0; i < message.length(); i++) {
            switch (message.charAt(i)) {
                case '(':
                    stack.push(i);
                    break;
                case ')':
                    if (stack.isEmpty() || !symbol) {
                        throw new ValidationException("Invalid character ')'; position: " + i + ";");
                    }
                    stack.pop();
                    break;
                case '\u00A0':
                case '\u2007':
                case '\u202F':
                case '\u0009':
                case '\n':
                case '\u000B':
                case '\u000C':
                case '\r':
                case '\u001C':
                case '\u001D':
                case '\u001E':
                case '\u001F':
                    break;
                default:
                    symbol = true;
            }
        }
        if (!stack.isEmpty()) {
            throw new ValidationException("Invalid character '('; position: " + stack.pop() + ";");
        }
    }
}
