package com.ribbontek.jpademo.context

import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import java.lang.annotation.Inherited

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@Profile("integration")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
annotation class IntegrationTest
