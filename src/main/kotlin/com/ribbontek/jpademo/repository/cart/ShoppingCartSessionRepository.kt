package com.ribbontek.jpademo.repository.cart

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShoppingCartSessionRepository : JpaRepository<ShoppingCartSessionEntity, Long>
