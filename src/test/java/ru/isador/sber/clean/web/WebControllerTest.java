package ru.isador.sber.clean.web;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class WebControllerTest {

    @Test
    @DisplayName("Проверка корректной валидации сообщения.")
    void testValidationSuccess() {
        given()
            .header("Content-Type", "application/json")
            .body("{\"text\": \"some text\"}")
            .when()
            .post("/api/checkBrackets")
            .then()
            .statusCode(200)
            .body(is("{\"isCorrect\":true}"));
    }

    @Test
    @DisplayName("Ответ при отсутствии заголовка Content-Type.")
    void testNoMediaTypeRequest() {
        given()
            .body("{\"text\": \"some text\"}")
            .when()
            .post("/api/checkBrackets")
            .then()
            .statusCode(415);
    }

    @Test
    @DisplayName("Ответ при невозможности обработки json.")
    void testInvalidJsonRequest() {
        given()
            .header("Content-Type", "application/json")
            .body("123")
            .when()
            .post("/api/checkBrackets")
            .then()
            .statusCode(200)
            .body(is("{\"isCorrect\":false}"));
    }

    @Test
    @DisplayName("Ответ при ошибке валидации.")
    void testValidationFailedRequest() {
        given()
            .header("Content-Type", "application/json")
            .body("{\"text\": \"invalid\"}")
            .when()
            .post("/api/checkBrackets")
            .then()
            .statusCode(200)
            .body(is("{\"isCorrect\":false}"));
    }

    @Test
    @DisplayName("Ответ при возникновении IllegalArgumentException.")
    void testValidationIAERequest() {
        given()
            .header("Content-Type", "application/json")
            .body("{\"text\": \"illegal\"}")
            .when()
            .post("/api/checkBrackets")
            .then()
            .statusCode(200)
            .body(is("{\"isCorrect\":false}"));
    }
}