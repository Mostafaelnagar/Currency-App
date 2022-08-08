plugins {
  id(Config.Plugins.androidApplication)
  id(Config.Plugins.kotlinAndroid)
  id(Config.Plugins.kotlinKapt)
  id(Config.Plugins.kotlin_extensions)
  id(Config.Plugins.navigationSafeArgs)
  id(Config.Plugins.hilt)
}

android {
  compileSdk = Config.AppConfig.compileSdkVersion

  defaultConfig {
    applicationId = Config.AppConfig.appId
    minSdk = Config.AppConfig.minSdkVersion
    targetSdk = Config.AppConfig.compileSdkVersion
    versionCode = Config.AppConfig.versionCode
    versionName = Config.AppConfig.versionName
    vectorDrawables.useSupportLibrary = true
    multiDexEnabled = true
    testInstrumentationRunner = Config.AppConfig.testRunner

  }

  buildTypes {
    getByName("debug") {
      manifestPlaceholders["appName"] = "@string/app_name"
      manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher_release"
      manifestPlaceholders["appRoundIcon"] = "@mipmap/ic_launcher_release_round"
      buildConfigField("String", "API_BASE_URL", Config.Environments.debugBaseUrl)
    }

    signingConfigs {
      create("releaseConfig") {
        storeFile = file("/home/t-e-s/Osman.jks")
        storePassword = "te2018"
        keyAlias = "te"
        keyPassword = "te2018"
      }
    }

    getByName("release") {
      signingConfig = signingConfigs.getByName("releaseConfig")

      isMinifyEnabled = true
      isShrinkResources = true
      isDebuggable = true
      manifestPlaceholders["appName"] = "@string/app_name"
      manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher_release"
      manifestPlaceholders["appRoundIcon"] = "@mipmap/ic_launcher_release_round"

      buildConfigField("String", "API_BASE_URL", Config.Environments.releaseBaseUrl)
    }
    buildTypes.all {
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {

    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    jvmTarget = "11"
  }

  dataBinding {
    isEnabled = true
  }
  lint {
    isCheckReleaseBuilds = false
    // Or, if you prefer, you can continue to check for errors in release builds,
    // but continue the build even when errors are found:
    isAbortOnError = false
  }
  buildFeatures {
    viewBinding = true
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

  // Networking
  implementation(Libraries.retrofit)
  implementation(Libraries.retrofitConverter)
  implementation(Libraries.gson)
  implementation(Libraries.interceptor)
  implementation(Libraries.chuckLogging)

  // Utils
  implementation(Libraries.playServices)
  implementation(Libraries.localization)
  implementation(Libraries.multidex)
  implementation(Libraries.gson)

  // Hilt
  implementation(Libraries.hilt)
  kapt(Libraries.hiltDaggerCompiler)
  // Support
  implementation(Libraries.appCompat)
  implementation(Libraries.coreKtx)
  implementation(Libraries.androidSupport)

  // Arch Components
  implementation(Libraries.viewModel)
  implementation(Libraries.lifeData)
  implementation(Libraries.lifecycle)
  implementation(Libraries.viewModelState)

  // Kotlin Coroutines
  implementation(Libraries.coroutinesCore)
  implementation(Libraries.coroutinesAndroid)
  //DATA STORE
  implementation(Libraries.datastore_preferences)
  implementation(Libraries.datastore_core)
  implementation(Libraries.datastore_protobuf)

  // UI
  implementation(Libraries.materialDesign)
  implementation(Libraries.navigationFragment)
  implementation(Libraries.navigationUI)
  implementation(Libraries.loadingAnimations)
  implementation(Libraries.alerter)
  implementation(Libraries.coil)
  implementation(Libraries.ssp)
  implementation(Libraries.ssdp)

  // Project Modules
  implementation(project(Config.Modules.prettyPopUp))

}
