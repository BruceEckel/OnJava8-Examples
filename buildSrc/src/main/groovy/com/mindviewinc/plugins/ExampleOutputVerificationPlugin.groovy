package com.mindviewinc.plugins

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.tasks.Exec

class ExampleOutputVerificationPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.tasks.create('verify', Exec) {
            description 'Uses Python tool to verify example output'
            commandLine 'python', '_verify_output.py'
            doFirst {
                println("execute 'gradlew run' first")
            }
        }
        
    }
}