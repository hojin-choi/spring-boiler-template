description = "api"

plugins {
    id("com.netflix.dgs.codegen") version "5.1.16"
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    mainClass.set("dev.joseph.api.ApiApplicationKt")
}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
    schemaPaths = mutableListOf("${projectDir}/src/main/resources/schema/apis.graphqls")
    generateClient = true
    packageName = "dev.joseph.api.generated"
    generateDataTypes = true
    snakeCaseConstantNames = true
    language = "kotlin"
    typeMapping = mutableMapOf(
        "CrTimestamp" to "java.time.OffsetDateTime",
    )
}

dependencies {
    api(project(":domain"))

    api("org.springframework.boot:spring-boot-starter-web")

    implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:latest.release"))
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
    implementation("com.netflix.graphql.dgs:graphql-dgs-extended-scalars")
}
