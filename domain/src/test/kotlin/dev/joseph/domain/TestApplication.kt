package dev.joseph.domain

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import java.util.TimeZone
import javax.annotation.PostConstruct

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
open class TestApplication {
    @PostConstruct
    fun started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
    }
}
