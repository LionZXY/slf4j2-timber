slf4j2-timber
============
[![Build Action](https://github.com/github/docs/actions/workflows/push.yml/badge.svg)](https://github.com/LionZXY/slf4j2-timber/actions/workflows/push.yml) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/uk.kulikov/slf4j2-timber/badge.svg)](https://maven-badges.herokuapp.com/maven-central/uk.kulikov/slf4j2-timber) [![Apache License](http://img.shields.io/badge/license-Apache%20License%202.0-lightgrey.svg)](http://choosealicense.com/licenses/apache-2.0/) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-slf4j--timber-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/272)

[SLF4J 2][1] binding for Jake Wharton's [Timber][2] Android logging library

Usage
-----

Just put `slf4j2-timber`, `timber` and `slf4j-api` (see note in Download)
artifacts to your project and use `slf4j` like before:

```kotlin
import com.sun.org.slf4j.internal.LoggerFactory

public class YourClass {
    private val logger = LoggerFactory.getLogger(YourClass::class.java)

    fun yourMethod() {
        logger.debug("Hello, world!")
    }
}
```

Don't forget to plant tree to Timber, check [Timber usage][3].

Download
-----

Download [the latest AAR][4] or grab it via Maven:

```xml
<dependency>
    <groupId>uk.kulikov</groupId>
    <artifactId>slf4j2-timber</artifactId>
    <version>1.0</version>
    <type>aar</type>
</dependency>
```

or Gradle:

```kotlin
implementation("com.arcao:slf4j2-timber:1.0")
```

> Note: `timber` and `slf4j-api` are the transitive dependencies of `slf4j2-timber`,
> so you don't need to take care of them in Maven POM and Gradle build script.

Transitive dependencies by version
-----

 slf4j2-timber | Timber | SLF4J  
---------------|--------|--------
 1.0           | 5.0.1  | 2.0.13 

[1]: http://www.slf4j.org/

[2]: https://github.com/JakeWharton/timber

[3]: https://github.com/JakeWharton/timber#usage

[4]: https://central.sonatype.com/artifact/uk.kulikov/slf4j2-timber/versions

[5]: http://www.slf4j.org/legacy.html

[6]: http://www.slf4j.org/download.html
