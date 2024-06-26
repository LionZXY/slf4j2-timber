import org.jreleaser.model.Active
import org.jreleaser.model.Signing

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("org.jreleaser")
    id("maven-publish")
}

android {
    namespace = "uk.kulikov.slf4j2.timber"
    compileSdk = 34

    defaultConfig {
        aarMetadata {
            minCompileSdk = 34
        }
        minSdk = 9
        proguardFiles(
            "consumer-proguard-rules.pro"
        )
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    lint {
        textReport = true
    }
}

dependencies {
    compileOnly(libs.annotations)

    implementation(libs.slf4j)
    implementation(libs.timber)

    testImplementation(libs.festandroid)
    testImplementation(libs.festassert)
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
}

android {
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

version = properties["VERSION_NAME"].toString()
description = properties["POM_DESCRIPTION"].toString()

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = properties["GROUP"].toString()
            artifactId = properties["POM_ARTIFACT_ID"].toString()

            pom {
                name.set(project.properties["POM_NAME"].toString())
                description.set(project.properties["POM_DESCRIPTION"].toString())
                url.set(project.properties["POM_URL"].toString())
                issueManagement {
                    url.set(project.properties["POM_ISSUE_URL"].toString())
                }

                scm {
                    url.set(project.properties["POM_SCM_URL"].toString())
                    connection.set(project.properties["POM_SCM_CONNECTION"].toString())
                    developerConnection.set(project.properties["POM_SCM_DEV_CONNECTION"].toString())
                }

                licenses {
                    license {
                        name.set(project.properties["POM_LICENCE_NAME"].toString())
                        url.set(project.properties["POM_LICENCE_URL"].toString())
                        distribution.set(project.properties["POM_LICENCE_DIST"].toString())
                    }
                }

                developers {
                    developer {
                        id.set("LionZXY")
                        name.set("Nikita Kulikov")
                        email.set("n@kulikov.uk")
                        url.set("https://kulikov.uk")
                    }
                    developer {
                        id.set("arcao")
                        name.set("Martin Sloup")
                        email.set("arcao@arcao.com")
                        url.set("https://arcao.com")
                    }
                }

                afterEvaluate {
                    from(components["release"])
                }
            }
        }
    }
    repositories {
        maven {
            setUrl(layout.buildDirectory.dir("staging-deploy"))
        }
    }
}

jreleaser {
    environment {
        variables = file("/home/lionzxy/.jreleaser/config.toml")
    }
    project {
        inceptionYear = "2024"
        author("@LionZXY")
        author("@arcao")
    }
    gitRootSearch = true
    signing {
        active = Active.ALWAYS
        armored = true
        verify = true
        mode = Signing.Mode.FILE
        publicKey = "/home/lionzxy/Downloads/public.pgp"
        secretKey = "/home/lionzxy/Downloads/private.pgp"

    }
    release {
        github {
            skipRelease = true
        }
    }
    deploy {
        maven {
            enabled = true
            mavenCentral.create("sonatype") {
                active = Active.ALWAYS
                url = "https://central.sonatype.com/api/v1/publisher"
                stagingRepository(layout.buildDirectory.dir("staging-deploy").get().toString())
                setAuthorization("Basic")
                applyMavenCentralRules = false
            }
        }
    }
}
