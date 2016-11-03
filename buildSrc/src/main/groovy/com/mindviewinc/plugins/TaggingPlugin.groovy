package com.mindviewinc.plugins

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.tasks.JavaExec
import org.gradle.internal.jvm.Jvm

import org.apache.tools.ant.util.TeeOutputStream

class TaggingPlugin implements Plugin<Project> {
    private final static String DEBUG_PROJECT_PROPERTY_KEY = 'debug'
    
    void apply(Project project) {
        boolean debug = project.hasProperty(DEBUG_PROJECT_PROPERTY_KEY) ? Boolean.valueOf(project.getProperty(DEBUG_PROJECT_PROPERTY_KEY)) : false        
        List createdTasks = []

        project.projectDir.eachFileRecurse { file ->
            if (file.name.endsWith('.java')) {

                Tags tags = new Tags(file)
                if(debug && tags.hasTags()) println tags

                // Exclude java sources that will not compile
                if (tags.compileTimeError) {
                    project.sourceSets.main.java.excludes.add(file.name)
                } else {
                    JavaExec javaTask = null
                    // Add tasks for java sources with main methods
                    if (tags.hasMainMethod || tags.javaCmd) {
                        javaTask = project.tasks.create(name: tags.fileRoot, type: JavaExec, dependsOn: tags.runFirst) {
                            main = tags.mainClass
                            classpath = project.sourceSets.main.runtimeClasspath
                            args = tags.args
                            jvmArgs = tags.jVMArgs
                        }
                    } else if (tags.javap) {
                        // Create task for running javap
                        javaTask = project.tasks.create(name: "${tags.fileRoot}", type: JavaExec, dependsOn: tags.runFirst) {
                            main = "com.sun.tools.javap.Main"
                            classpath = project.sourceSets.main.runtimeClasspath + project.files(Jvm.current().toolsJar)
                            // Assuming javap represents all the args and there's no need to jVMArgs
                            args tags.javap.split()
                        }
                    }

                    if (javaTask) {
                        def baseName = file.name.substring(0, file.name.lastIndexOf('.'))
                        File outFile = new File(file.parentFile, baseName + '.out')
                        File errFile = new File(file.parentFile, baseName + '.err')

                        javaTask.configure {
                            ignoreExitValue = tags.validateByHand || tags.throwsException
                            doFirst {
                                if(outFile.exists())
                                    outFile.delete()
                                if(tags.outputLine)
                                    outFile << tags.outputLine + "\n"

                                standardOutput = new TeeOutputStream(new FileOutputStream(outFile, true), System.out)
                                errorOutput = new TeeOutputStream(new FileOutputStream(errFile), System.err)
                            }
                            doLast {
                                if(outFile.size() == 0)
                                    outFile.delete()
                                else if(!outFile.text.contains("/* Output:"))
                                    outFile.delete()
                                if(errFile.size() == 0) errFile.delete()
                           }
                        }

                        if (!tags.validateByHand) {
                            // Only add tasks that we know we can run successfully to the task list
                            createdTasks.add(javaTask)
                        }
                    }
                }
            }
        }
        
        project.tasks.create('run') {
            dependsOn createdTasks
        }
    }
}