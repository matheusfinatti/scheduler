val ktlint: Configuration by configurations.creating

val disabledRules = listOf(
    "import-ordering"
)

dependencies {
    ktlint(Dependencies.quality.ktlint)
}

tasks.register<JavaExec>("ktlint") {
    val outputDir = "${project.buildDir}/reports/ktlint/"

    group = "verification"
    description = "Check Kotlin code style."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    inputs.files(project.fileTree("dir" to "src", "include" to "**/*.kt"))
    outputs.dir(outputDir)
    args(
        "src/**/*.kt",
        "--disabled_rules=${disabledRules.joinToString(",")}",
        "--reporter=checkstyle,output=$outputDir/ktlint-checkstyle-report.xml",
        "--reporter=plain"
    )
}

tasks.register<JavaExec>("ktlintFormat") {
    group = "formatting"
    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args("-F", "src/**/*.kt")
}

afterEvaluate {
    tasks.named("check") {
        dependsOn(tasks.getByName("ktlint"))
    }
}
