// tag::buildscript_block[]
import org.yaml.snakeyaml.Yaml
import java.io.File

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        mavenCentral()  // Where to find the plugin
    }
    dependencies {
        classpath("org.yaml:snakeyaml:1.19") // The library's classpath dependency
        classpath("com.github.johnrengelman:shadow:8.1.1") // The legacy version of Shadow Plugin that needs buildscript
    }
}

// Applies legacy Shadow plugin
apply(plugin = "com.github.johnrengelman.shadow")

// Uses the library in the build script
val yamlContent = """
        name: Project
    """.trimIndent()
val yaml = Yaml()
val data: Map<String, Any> = yaml.load(yamlContent)
// end::buildscript_block[]

// tag::plugin[]
class MyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        println("Plugin ${this.javaClass.simpleName} applied on ${project.name}")
    }
}

apply<MyPlugin>()
// end::plugin[]
