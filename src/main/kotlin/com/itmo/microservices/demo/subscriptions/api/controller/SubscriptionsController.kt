package com.itmo.microservices.demo.subscriptions.api.controller

import com.itmo.microservices.demo.subscriptions.api.models.UpdateSubscriptionRequest
import com.itmo.microservices.demo.subscriptions.impl.service.SubscriptionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/subscriptions")
class SubscriptionsController(private val subscriptionService : SubscriptionService) {

    @PostMapping
    @Operation(
        summary = "Update subscription level",
        responses = [
            ApiResponse(description = "OK", responseCode = "200"),
            ApiResponse(description = "Bad request", responseCode = "400", content = [Content()])
        ],
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun updateSubscriptions (
        @RequestBody request : UpdateSubscriptionRequest
    ) = runBlocking { subscriptionService.updateSubscriptionLevel(request) }
}