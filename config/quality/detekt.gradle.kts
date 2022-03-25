val detekt: Configuration by configurations.creating

dependencies {
    detekt(Dependencies.quality.detekt)
}

tasks.register<JavaExec>("detekt") {
    main = "io.gitlab.arturbosch.detekt.cli.Main"
    classpath = configurations.getByName("detekt")
    val input = "$projectDir"
    val config = "$rootDir/config/quality/detekt/detekt-config.yml"
    val excludes = ".*/resources/.*,.*/build/.*,**/build.gradle.kts,**/test/**,**/build/**"
    val report = "html:$buildDir/reports/detekt.html"
    val params = listOf("-i", input, "-c", config, "-ex", excludes, "-r", report, "--fail-fast")
    args(params)
}

afterEvaluate {
    tasks.named("check") {
        dependsOn(tasks.getByName("detekt"))
    }
}
