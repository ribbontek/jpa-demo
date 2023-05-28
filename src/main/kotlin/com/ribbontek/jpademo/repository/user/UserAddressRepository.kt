package com.ribbontek.jpademo.repository.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAddressRepository : JpaRepository<UserAddressEntity, Long>
