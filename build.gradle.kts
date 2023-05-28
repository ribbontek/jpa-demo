import org.apache.tools.ant.taskdefs.condition.Os
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    id("org.liquibase.gradle") version "2.1.1"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    kotlin("plugin.jpa") version "1.8.21"
}

group = "com.ribbontek"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    // BOMS
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.1.0"))
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.21"))

    implementation(kotlin("reflect"))
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude("org.springframework.boot", "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.liquibase:liquibase-core")
    runtimeOnly("org.postgresql:postgresql")
    implementation("io.hypersistence:hypersistence-utils-hibernate-62:3.4.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("io.github.serpro69:kotlin-faker:1.14.0")
    testImplementation("net.sourceforge.plantuml:plantuml:1.2023.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kotlin {
    allOpen {
        annotations(
            "javax.persistence.Entity",
            "javax.persistence.MappedSuperclass",
            "javax.persistence.Embedabble"
        )
    }
}

liquibase {
    activities.register("main") {
        val dbUrl by project.extra.properties
        val dbUser by project.extra.properties
        val dbPassword by project.extra.properties

        this.arguments = mapOf(
            "logLevel" to "info",
            "changeLogFile" to "src/main/resources/liquibase/db.changelog-master.xml",
            "url" to (project.findProperty("DATABASE_URL")?.toString() ?: dbUrl),
            "username" to (project.findProperty("DATABASE_USERNAME")?.toString() ?: dbUser),
            "password" to (project.findProperty("DATABASE_PASSWORD")?.toString() ?: dbPassword),
            "driver" to "org.postgresql.Driver"
        )
    }
    runList = "main"
}

tasks {
    register<Copy>("copyGitHooks") {
        description = "Copies the git hooks from git-hooks to the .git folder."
        group = "GIT_HOOKS"
        from("$rootDir/git-hooks/") {
            include("**/*.sh")
            rename("(.*).sh", "$1")
        }
        into("$rootDir/.git/hooks")
    }

    register<Exec>("installGitHooks") {
        description = "Installs the git hooks from the git-hooks folder"
        group = "GIT_HOOKS"
        workingDir(rootDir)
        commandLine("chmod")
        args("-R", "+x", ".git/hooks/")
        dependsOn(named("copyGitHooks"))
        onlyIf {
            !Os.isFamily(Os.FAMILY_WINDOWS)
        }
        doLast {
            logger.info("Git hooks installed successfully.")
        }
    }

    register<Delete>("deleteGitHooks") {
        description = "Delete the git hooks."
        group = "GIT_HOOKS"
        delete(fileTree(".git/hooks/"))
    }

    afterEvaluate {
        tasks["clean"].dependsOn(tasks.named("installGitHooks"))
    }
}
