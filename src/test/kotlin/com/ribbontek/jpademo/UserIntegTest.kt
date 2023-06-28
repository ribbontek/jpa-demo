package com.ribbontek.jpademo

import com.ribbontek.jpademo.context.AbstractIntegTest
import com.ribbontek.jpademo.generator.UserGenerator
import com.ribbontek.jpademo.repository.user.UserRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

class UserIntegTest : AbstractIntegTest() {

    @Autowired
    private lateinit var userGenerator: UserGenerator

    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    fun `generates an admin user`() {
        val (user, addresses) = userGenerator.generateAdmin()
        val userEntity = userRepository.findUserEntityByEmailWithEagerAddresses(user.email)
        assertNotNull(userEntity)
        assertNotNull(userEntity!!.addresses)
        assertThat(addresses.size, equalTo(userEntity.addresses!!.size))
    }

    @Test
    fun `generates a standard user`() {
        val (user, addresses) = userGenerator.generateStandard()
        val userEntity = userRepository.findUserEntityByEmailWithEagerAddresses(user.email)
        assertNotNull(userEntity)
        assertNotNull(userEntity!!.addresses)
        assertThat(addresses.size, equalTo(userEntity.addresses!!.size))
    }

    @Test
    fun `deletes a standard user`() {
        val (user, _) = userGenerator.generateStandard()
        assertNotNull(user)
        userRepository.delete(user)
        assertNull(userRepository.findByIdOrNull(user.id))
    }
}
