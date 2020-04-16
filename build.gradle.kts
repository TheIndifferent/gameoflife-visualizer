plugins {
    application
    kotlin("jvm") version "1.3.70"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "io.github.theindifferent"
version = "0.3"

repositories {
    mavenCentral()
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-reflect:1.3.70")
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.springframework:spring-web:5.2.5.RELEASE")
    implementation("org.springframework:spring-webmvc:5.2.5.RELEASE")
    implementation("org.springframework.boot:spring-boot:2.2.5.RELEASE")
    implementation("org.springframework.boot:spring-boot-autoconfigure:2.2.5.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-logging:2.2.5.RELEASE")
    implementation("org.apache.tomcat.embed:tomcat-embed-core:9.0.31")
//    implementation("org.springframework.boot:spring-boot-starter-web:2.2.5.RELEASE")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.6.1")
}

tasks.withType<Jar> {
    manifest {
        attributes(
                mapOf(
                        "Main-Class" to "io.github.theindifferent.gameoflife.visualizer.Main"
                )
        )
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

application {
    mainClassName = "io.github.theindifferent.gameoflife.visualizer.Main"
}
