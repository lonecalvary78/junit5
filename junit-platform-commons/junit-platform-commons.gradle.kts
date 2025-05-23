import junitbuild.extensions.javaModuleName

plugins {
	id("junitbuild.kotlin-library-conventions")
	`java-test-fixtures`
}

description = "JUnit Platform Commons"

dependencies {
	api(platform(projects.junitBom))

	compileOnlyApi(libs.apiguardian)

	compileOnly(kotlin("stdlib"))
	compileOnly(kotlin("reflect"))
	compileOnly(libs.kotlinx.coroutines)
}

tasks.compileJava {
	options.compilerArgs.add("-Xlint:-module") // due to qualified exports
	val moduleName = javaModuleName
	val mainOutput = files(sourceSets.main.get().output)
	options.compilerArgumentProviders.add(CommandLineArgumentProvider {
		listOf("--patch-module", "${moduleName}=${mainOutput.asPath}")
	})
}

tasks.jar {
	bundle {
		val importAPIGuardian: String by extra
		bnd("""
			Import-Package: \
				$importAPIGuardian,\
				kotlin.*;resolution:="optional",\
				kotlinx.*;resolution:="optional",\
				*
		""")
	}
}
