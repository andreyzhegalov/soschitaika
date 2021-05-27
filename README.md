# Тренажёр развития вычислительных навыков на базе микросервисной архитектуры "СосчитайКа"

[![Build Status](https://travis-ci.org/andreyzhegalov/soschitaika.svg?branch=main)](https://travis-ci.org/andreyzhegalov/soschitaika)
[![codecov](https://codecov.io/gh/andreyzhegalov/soschitaika/branch/main/graph/badge.svg?token=s9BbEd1xif)](https://codecov.io/gh/andreyzhegalov/soschitaika)

## Описание

Выполнено в рамках курсовой работы по курсу OTUS "Разработчик на Spring Framework"

### Цель

Закрепить полученные навыки и знания. Поработать с новыми инструментами.

#### Результат

Приложение на базе микросервисной архитектуры.

### Описание реализации

Приложение состоит их следующих сервисов:
1. **game-session-service** - Основной интеграционный сервис, хранящий состояние сессии
2. **expression-service** - Сервис генерации выражений и ответов
3. **report-service** - Сервис формирования отчетов

Взаимодействие *game-session-service* c *expression-service* выполнено на базе aouth2 с использованием [spring-authorization-server](https://github.com/spring-projects-experimental/spring-authorization-server).  Взаимодействие *game-session-service* c *report-service* происходит через RabbitMQ.  Для обращения к *game-session-service* используется протокол STOMP на базе Websocket.  Далее через поток [spring integration](https://spring.io/projects/spring-integration) запрос через RabbitMQ отправляется в *report-service*.  По той же цепочке возвращается ответ.
