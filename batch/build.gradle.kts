description = "batch"

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    mainClass.set("dev.joseph.batch.BatchApplicationKt")
}

dependencies {
    api(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter-batch")
}
