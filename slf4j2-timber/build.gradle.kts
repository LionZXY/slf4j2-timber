import org.jreleaser.model.Active

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("org.jreleaser")
    id("maven-publish")
    id("signing")
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
    // Transitive dependencies
    api(libs.slf4j)
    api(libs.timber)

    testImplementation(libs.festandroid)
    testImplementation(libs.festassert)
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
}

// Deploy

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
                url.set("https://github.com/lionzxy/slf4j2-timber")
                issueManagement {
                    url.set("https://github.com/LionZXY/slf4j2-timber/issues")
                }

                scm {
                    url.set("https://github.com/LionZXY/slf4j2-timber")
                    connection.set("scm:git://github.com/LionZXY/slf4j2-timber.git")
                    developerConnection.set("scm:git://github.com/LionZXY/slf4j2-timber.git")
                }

                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
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
    }
    release {
        github {
            skipTag = true
            sign = true
            branch = "master"
            branchPush = "master"
            overwrite = true
        }
    }
    deploy {
        maven {
            mavenCentral.create("sonatype") {
                active = Active.ALWAYS
                url = "https://central.sonatype.com/api/v1/publisher"
                stagingRepository(layout.buildDirectory.dir("staging-deploy").get().toString())
                setAuthorization("Basic")
                applyMavenCentralRules = false // Wait for fix: https://github.com/kordamp/pomchecker/issues/21
                sign = true
                checksums = true
                sourceJar = true
                javadocJar = true
                retryDelay = 60
            }
        }
    }
}
