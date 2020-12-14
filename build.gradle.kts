import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.1"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
    kotlin("plugin.jpa") version "1.4.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
// java.sourceCompatibility = JavaVersion.VERSION_15

repositories {
    mavenCentral()
    jcenter()
}

dependencies {

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.shell:spring-shell-starter:2.0.1.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")

    // https://adinugroho.medium.com/using-sqlite-with-spring-boot-kotlin-73b365f6c576
    implementation("org.xerial:sqlite-jdbc:3.32.3.2")
    implementation("com.github.gwenn:sqlite-dialect:0.1.0")

    // also add `jcenter()` to `repositories {} block
    implementation("org.jsoup:jsoup:1.13.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
