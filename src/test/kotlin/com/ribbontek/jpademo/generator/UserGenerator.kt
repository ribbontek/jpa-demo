package com.ribbontek.jpademo.generator

import com.ribbontek.jpademo.factory.RoleEntityFactory
import com.ribbontek.jpademo.factory.UserAddressFactory
import com.ribbontek.jpademo.factory.UserEntityFactory
import com.ribbontek.jpademo.repository.user.AddressTypeEnum
import com.ribbontek.jpademo.repository.user.AddressTypeRepository
import com.ribbontek.jpademo.repository.user.PolicyEntity
import com.ribbontek.jpademo.repository.user.RoleRepository
import com.ribbontek.jpademo.repository.user.RoleType.ADMIN
import com.ribbontek.jpademo.repository.user.RoleType.STANDARD
import com.ribbontek.jpademo.repository.user.UserAddressEntity
import com.ribbontek.jpademo.repository.user.UserAddressRepository
import com.ribbontek.jpademo.repository.user.UserEntity
import com.ribbontek.jpademo.repository.user.UserRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

interface UserGenerator {
    fun generateAdmin(): Pair<UserEntity, List<UserAddressEntity>>
    fun generateStandard(): Pair<UserEntity, List<UserAddressEntity>>
}

@Component
class UserGeneratorImpl(
    private val userRepository: UserRepository,
    private val addressTypeRepository: AddressTypeRepository,
    private val userAddressRepository: UserAddressRepository,
    private val roleRepository: RoleRepository,
) : UserGenerator {
    @Transactional
    override fun generateAdmin(): Pair<UserEntity, List<UserAddressEntity>> {
        val userEntity = userRepository.save(UserEntityFactory.create())
        roleRepository.save(
            RoleEntityFactory.create(
                userEntity = userEntity,
                roleType = ADMIN,
                policies = listOf(
                    PolicyEntity(permission = "shopping:*"),
                    PolicyEntity(permission = "product:*"),
                    PolicyEntity(permission = "order:*"),
                    PolicyEntity(permission = "payment:*"),
                    PolicyEntity(permission = "discount:*")
                )
            )
        )
        val userAddresses = userAddressRepository.saveAll(
            listOf(
                UserAddressFactory.create(
                    userEntity = userEntity,
                    addressTypeEntity = addressTypeRepository.findByCode(AddressTypeEnum.RESIDENTIAL.code)!!
                ),
                UserAddressFactory.create(
                    userEntity = userEntity,
                    addressTypeEntity = addressTypeRepository.findByCode(AddressTypeEnum.POSTAL.code)!!
                )
            )
        )
        return userEntity to userAddresses
    }

    @Transactional
    override fun generateStandard(): Pair<UserEntity, List<UserAddressEntity>> {
        val userEntity = userRepository.save(UserEntityFactory.create())
        roleRepository.save(
            RoleEntityFactory.create(
                userEntity = userEntity,
                roleType = STANDARD,
                policies = listOf(
                    PolicyEntity(permission = "shopping:*"),
                    PolicyEntity(permission = "order:*"),
                    PolicyEntity(permission = "payment:*"),
                    PolicyEntity(permission = "discount:view"),
                    PolicyEntity(permission = "product:view")
                )
            )
        )
        val userAddresses = userAddressRepository.saveAll(
            listOf(
                UserAddressFactory.create(
                    userEntity = userEntity,
                    addressTypeEntity = addressTypeRepository.findByCode(AddressTypeEnum.RESIDENTIAL.code)!!
                ),
                UserAddressFactory.create(
                    userEntity = userEntity,
                    addressTypeEntity = addressTypeRepository.findByCode(AddressTypeEnum.POSTAL.code)!!
                )
            )
        )
        return userEntity to userAddresses
    }
}
