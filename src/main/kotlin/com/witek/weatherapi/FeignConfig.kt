package com.witek.weatherapi

import feign.Logger
import feign.slf4j.Slf4jLogger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class FeignConfig {

    @Bean
    open fun feignLoggerLevel(): Logger.Level = Logger.Level.FULL

    @Bean
    open fun feignLogger(): Logger = Slf4jLogger()
}
