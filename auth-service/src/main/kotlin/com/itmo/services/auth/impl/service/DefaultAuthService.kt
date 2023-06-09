package com.itmo.services.auth.impl.service

import com.itmo.services.auth.api.model.AuthenticationRequest
import com.itmo.services.auth.api.model.AuthenticationResult
import com.itmo.services.auth.api.service.AuthService
import com.itmo.services.common.exception.AccessDeniedException
import com.itmo.services.common.exception.NotFoundException
import com.itmo.services.users.api.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class DefaultAuthService(private val userService: UserService,
                         private val tokenManager: JwtTokenManager,
                         private val passwordEncoder: PasswordEncoder) : AuthService {

    override fun authenticate(request: AuthenticationRequest): AuthenticationResult {
        val user = userService.findUser(request.username)
                ?: throw NotFoundException("User with username ${request.username} not found")

        if (!passwordEncoder.matches(request.password, user.password))
            throw AccessDeniedException("Invalid password")

        val accessToken = tokenManager.generateToken(user.userDetails())
        val refreshToken = tokenManager.generateRefreshToken(user.userDetails())
        return AuthenticationResult(accessToken, refreshToken)
    }

    override fun refresh(authentication: Authentication): AuthenticationResult {
        val refreshToken = authentication.credentials as String
        val principal = authentication.principal as UserDetails
        val accessToken = tokenManager.generateToken(principal)
        return AuthenticationResult(accessToken, refreshToken)
    }
}
