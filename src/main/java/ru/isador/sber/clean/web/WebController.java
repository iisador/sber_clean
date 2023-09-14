package ru.isador.sber.clean.web;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WebController  {

    private final ValidationService validationService;

    public WebController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @POST
    @Path("/checkBrackets")
    public ValidationResult checkMessage(Message message) {
        return validationService.validate(message);
    }
}
