package com.ribbontek.jpademo.security

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.test.context.TestSecurityContextHolder
import org.springframework.security.core.context.SecurityContext as SpringSecurityContext

object TestSecurityContext {

    fun unauthenticated() {
        SecurityContextHolder.getContext().authentication = null
    }

    fun configureUser(
        userId: Long,
        username: String,
        idpUserName: String,
        authorities: Set<String> = emptySet()
    ) {
        val principal = Principal(
            username = username,
            userId = userId,
            idpUserName = idpUserName,
            authorities = authorities.toGrantedAuthorities()
        )
        val securityContext = SecurityContextImpl().apply {
            authentication = CustomAuthenticationToken(
                accessToken = null,
                principal = principal,
                authorities = authorities.toGrantedAuthorities()
            )
        }
        updateSpringSecurityContext(securityContext)
    }

    private fun updateSpringSecurityContext(securityContext: SpringSecurityContext) {
        TestSecurityContextHolder.setContext(securityContext)
    }

    private fun Set<String>.toGrantedAuthorities() = this.mapTo(arrayListOf(), ::SimpleGrantedAuthority)
}
