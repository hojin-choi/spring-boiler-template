description = "domain"

plugins {
    kotlin("plugin.jpa")
}

tasks {
    bootJar {
        enabled = false
    }
    bootRun {
        enabled = false
    }
    jar {
        enabled = true
    }
}

dependencies {
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

    api("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("mysql:mysql-connector-java")
}
