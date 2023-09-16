package ru.isador.sber.clean.web;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import ru.isador.sber.clean.web.massages.TextMessage;

/**
 * Контроллер предоставляющий внешнее API для доступа к функционалу проверки.
 * <p>
 * На вход принимается текстовое сообщение.
 *
 * @since 1.0.0
 */
@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WebController {

    private final ValidationService validationService;

    public WebController(ValidationService validationService) {
        this.validationService = validationService;
    }

    /**
     * Проверка входящего сообщения.
     *
     * @param message текстовое сообщение.
     *
     * @return результат проверки.
     *
     * @since 1.0.0
     */
    @POST
    @Path("/checkBrackets")
    public ValidationResult checkBrackets(TextMessage message) {
        return validationService.validate(message);
    }
}
