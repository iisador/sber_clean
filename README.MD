# Сервис проверки корректности расстановки скобок

## Требования
Java 17+

## Запуск
- Проверить что установлена системная переменная JAVA_HOME;
- Распаковать архив, зайти в папку с проектом и выполнить команду `mvnw quarkus:dev`;
- По умолчанию сервис развернётся на порту 8080. Изменить порт можно в файле `src/main/resources/application.properties` переменной `quarkus.http.port`;

## Проверка работы
Приложение будет доступно по адресу http://localhost:8080/. Проверить работоспособность сервиса можно через вебморду swagger, доступную по адресу http://localhost:8080/q/swagger-ui/.
