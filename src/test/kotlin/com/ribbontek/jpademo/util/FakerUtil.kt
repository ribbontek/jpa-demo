package com.ribbontek.jpademo.util

import io.github.serpro69.kfaker.Faker
import org.apache.commons.lang3.RandomUtils

object FakerUtil {
    private val faker = Faker()

    fun email() = faker.internet.email()
    fun firstName() = faker.name.firstName()
    fun lastName() = faker.name.lastName()
    fun addressLine() = faker.address.buildingNumber() + " " + faker.address.streetName()
    fun state() = faker.address.state()
    fun suburb() = faker.address.city()
    fun postcode() = faker.address.postcode()
    fun country() = faker.address.country()
    fun status() = faker.emotion.noun()
    fun quantity() = faker.random.nextInt(100)
    fun price() = (faker.random.nextInt(100) + faker.random.nextDouble()).toBigDecimal()
    fun alphanumeric(length: Int = 255) = faker.random.randomString(length = length)
    inline fun <reified T : Enum<*>> enum(): T =
        T::class.java.enumConstants[RandomUtils.nextInt(0, T::class.java.enumConstants.size)]
}
