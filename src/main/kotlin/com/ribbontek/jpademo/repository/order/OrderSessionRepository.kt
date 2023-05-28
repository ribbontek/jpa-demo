package com.ribbontek.jpademo.repository.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderSessionRepository : JpaRepository<OrderSessionEntity, Long>
