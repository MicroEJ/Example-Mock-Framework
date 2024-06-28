/*
 * Kotlin
 *
 * Copyright 2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven {
            name = "localModuleRepository"
            url = uri("${rootDir}/module-repository")
        }
        maven {
            name = "microEJForgeSDK6"
            url = uri("https://forge.microej.com/artifactory/microej-sdk6-repository-release/")
        }
        ivy {
            name = "microEJForgeSDK6Ivy"
            url = uri("https://forge.microej.com/artifactory/microej-sdk6-repository-release/")
            patternLayout {
                artifact("[organisation]/[module]/[revision]/[artifact]-[revision](-[classifier])(.[ext])")
                ivy("[organisation]/[module]/[revision]/ivy-[revision].xml")
                setM2compatible(true)
            }
        }

        // Workaround for MicroEJ plugin transitive dependency:
        //     > com.microej.gradle.application.gradle.plugin:0.15.0
        //     > com.microej.gradle:plugins:0.15.0
        //     > com.microej.test:junit-test-engine:0.2.2
        //     > ej.library.test:junit:1.7.1
        //     > ej.library.eclasspath:collections:1.3.0
        //     > ej.api:edc:1.2.3

        ivy {
            name = "microEJForgeCentralIvy"
            url = uri("https://forge.microej.com/artifactory/microej-central-repository-release")
            patternLayout {
                artifact("[organisation]/[module]/[revision]/[artifact]-[revision](-[classifier])(.[ext])")
                ivy("[organisation]/[module]/[revision]/ivy-[revision].xml")
                setM2compatible(true)
            }
        }
    }
}

