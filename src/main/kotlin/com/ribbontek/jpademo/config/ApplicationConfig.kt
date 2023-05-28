package com.ribbontek.jpademo.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackages = ["com.ribbontek.jpademo.repository"])
@EnableJpaRepositories(basePackages = ["com.ribbontek.jpademo.repository"])
class ApplicationConfig
