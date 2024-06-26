apply(plugin = "org.jreleaser")
apply(plugin = "maven-publish")

/*
android {
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

def isReleaseBuild() {
    return VERSION_NAME.contains("SNAPSHOT") == false
}

publishing {
    publications {
        release(MavenPublication) {
            groupId = GROUP
            artifactId = POM_ARTIFACT_ID
            version = VERSION_NAME

            pom {
                name = POM_NAME
                description = POM_DESCRIPTION
                url = POM_URL

                scm {
                    url = POM_SCM_URL
                    connection = POM_SCM_CONNECTION
                    developerConnection = POM_SCM_DEV_CONNECTION
                }

                licenses {
                    license {
                        name = POM_LICENCE_NAME
                        url = POM_LICENCE_URL
                        distribution = POM_LICENCE_DIST
                    }
                }

                developers {
                    developer {
                        id = POM_DEVELOPER_ID
                        name = POM_DEVELOPER_NAME
                        email = POM_DEVELOPER_EMAIL
                        url = POM_DEVELOPER_URL
                    }
                }
            }

            afterEvaluate {
                from components.release
            }
        }
    }
    repositories {
        maven {
            if (isReleaseBuild()) {
                url = "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
            } else {
                url = "https://oss.sonatype.org/content/repositories/snapshots/"
            }
            credentials {
                username hasProperty('SONATYPE_NEXUS_USERNAME') ? SONATYPE_NEXUS_USERNAME : ""
                password hasProperty('SONATYPE_NEXUS_PASSWORD') ? SONATYPE_NEXUS_PASSWORD : ""
            }
        }
    }
}
signing {
    required { isReleaseBuild() && gradle.taskGraph.hasTask("uploadArchives") }
    sign publishing.publications.release
}

jreleaser {
    signing {
        setActive("ALWAYS")
        armored = true
    }
    deploy {
        maven {
            mavenCentral.create("sonatype") {
                setActive("ALWAYS")
                url = "https://central.sonatype.com/api/v1/publisher"
                stagingRepository("target/staging-deploy")
                setAuthorization("Basic")
                username = properties["SONATYPE_USERNAME"].toString()
                password = properties["SONATYPE_PASSWORD"].toString()
            }
        }
    }
}*/