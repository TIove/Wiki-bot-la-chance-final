package com.itmo.microservices.demo.external.models

enum class AnswerMethod {
    /**
    Долгая синхронная транзакция. Запрос будет висеть, пока не обработается.
     */
    TRANSACTION,

    /**
    Запрос кладётся в очередь. По завершении делается http запрос с результатом.
     */
    CALLBACK,

    /**
    Запрос кладётся в очередь. Результат операции необходимо получать отдельным запросом.
     */
    POLLING
}