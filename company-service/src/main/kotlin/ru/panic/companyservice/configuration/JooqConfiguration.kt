package ru.panic.companyservice.configuration


import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jooq.ConnectionProvider
import org.jooq.SQLDialect
import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class JooqConfiguration {
    @Value("\${spring.datasource.url}")
    private lateinit var dbUrl: String

    @Value("\${spring.datasource.username}")
    private lateinit var dbUsername: String

    @Value("\${spring.datasource.password}")
    private lateinit var dbPassword: String

    @Value("\${spring.datasource.driver-class-name}")
    private lateinit var dbDriver: String

    @Bean
    fun dataSource(): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = dbUrl
        config.username = dbUsername
        config.password = dbPassword
        config.driverClassName = dbDriver
        return HikariDataSource(config)
    }

    @Bean
    fun connectionProvider(): ConnectionProvider {
        return DataSourceConnectionProvider(dataSource())
    }

    @Bean
    fun dsl(): DefaultDSLContext {
        return DefaultDSLContext(configuration())
    }
    @Bean
    fun configuration(): DefaultConfiguration {
        val jooqConfiguration = DefaultConfiguration()
        jooqConfiguration.set(connectionProvider())
        jooqConfiguration.set(SQLDialect.POSTGRES)
        return jooqConfiguration
    }
}






