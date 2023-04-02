package com.itmo.microservices.demo.subscriptions.api.service

import com.itmo.microservices.demo.common.DefaultResponse
import com.itmo.microservices.demo.subscriptions.api.models.CreateSubscriptionRequest

interface SubscriptionService {
    suspend fun updateSubscriptionLevel(request : CreateSubscriptionRequest) : DefaultResponse
}