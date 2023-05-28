package com.ribbontek.jpademo.context

import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.output.OutputFrame

@IntegrationTest
abstract class AbstractIntegTest {

    init {
        setupPostgresContainer()
    }

    private fun setupPostgresContainer() {
        PostgreSQLContainer<Nothing>("postgres:12.2-alpine").apply {
            withDatabaseName("ribbontek")
            withUsername("postgres")
            withEnv("POSTGRES_HOST_AUTH_METHOD", "trust")
            withInitScript("init_script.sql")
            getLogs(OutputFrame.OutputType.STDERR)
            start()
            System.setProperty("spring.datasource.url", jdbcUrl)
            System.setProperty("spring.liquibase.url", jdbcUrl)
        }
    }
}
