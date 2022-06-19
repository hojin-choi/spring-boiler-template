import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm") version "1.6.10"

    // lint
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    id("org.jlleitschuh.gradle.ktlint-idea") version "10.2.1"

    // spring
    id("org.springframework.boot") version "2.6.4" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "1.6.10" apply false
    kotlin("plugin.jpa") version "1.6.10" apply false
}

allprojects {
    group = "dev.hojin.kotlinsample"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    ktlint {
        filter {
            exclude("*.kts")
            exclude("**/generated/**")
        }
    }
}

tasks {
    register<Exec>("lint") {
        commandLine = "./gradlew ktlintCheck".split(" ")
    }
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    dependencies {
        implementation(kotlin("stdlib"))

        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.mockk:mockk:1.12.3")
        testImplementation("com.ninja-squad:springmockk:3.1.1")

//        testImplementation(platform("org.junit:junit-bom:5.8.2"))
//        testImplementation("org.junit.jupiter:junit-jupiter")
//        testImplementation("org.hamcrest:hamcrest:2.2")
    }

    tasks.test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    // JSR-305 에 정의된 null 허용성 어노테이션 강제
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}
