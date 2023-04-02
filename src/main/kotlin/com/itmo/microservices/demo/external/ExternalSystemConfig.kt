package com.itmo.microservices.demo.external

object ExternalSystemConfig {
    private const val host = "http://localhost:8080"
    const val paymentUrl = "$host/transactions/payment"
    const val projectUrl = "$host/management/projects"
    const val clientSecretUrl = "$host/management/accounts"
}