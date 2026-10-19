//
//
//plugins {
//    alias(libs.plugins.android.application)
//    alias(libs.plugins.ksp)
//}
//
//android {
//    namespace = "com.example.tuskmeneger"
//    compileSdk {
//        version = release(37) {
//
//        }
//    }
//
//    defaultConfig {
//        applicationId = "com.example.tuskmeneger"
//        minSdk = 24
//        targetSdk = 36
//        versionCode = 1
//        versionName = "1.0"
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//    }
//
//    buildTypes {
//        release {
//            optimization {
//                enable = false
//            }
//        }
//    }
//
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//        isCoreLibraryDesugaringEnabled = true
//    }
//    buildFeatures {
//        viewBinding = true
//    }
//
//}
//val roomVersion = "2.6.1"
//dependencies {
//
//    implementation("androidx.room:room-runtime:$roomVersion")
//    implementation("androidx.room:room-ktx:$roomVersion") // если нужны корутины/Flow
//    ksp("androidx.room:room-compiler:$roomVersion")
//    implementation ("android.arch.persistence.room:runtime:1.0.0")
//    annotationProcessor ("android.arch.persistence.room:compiler:1.0.0")
//    implementation(libs.androidx.adapters)
//    implementation(libs.androidx.navigation.fragment.ktx)
//    implementation(libs.androidx.navigation.ui.ktx)
//    implementation("androidx.core:core-ktx:1.17.0")
//    implementation("androidx.core:core:1.17.0")
//    implementation("com.github.skydoves:expandablelayout:1.0.7")
//    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
//    implementation(libs.androidx.activity.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.androidx.constraintlayout)
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.material)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(libs.androidx.junit)
//}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp) // <-- ДОБАВЛЕНО: без этого ksp(...) не работает
}

android {
    namespace = "com.example.tuskmeneger"
    compileSdk {
        version = release(37) {}
    }

    defaultConfig {
        applicationId = "com.example.tuskmeneger"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }
    buildFeatures {
        viewBinding = true
    }
}

// Room 3.x: используем androidx.room3, а не androidx.room или android.arch
val roomVersion = "3.0.3" // <-- ОБНОВЛЕНО до Room 3

dependencies {
    // --- Room 3 (KSP обязателен) ---
    implementation("androidx.room3:room3-runtime:$roomVersion")
    implementation(libs.androidx.adapters)
    implementation(libs.androidx.room.common.jvm)
    // room-ktx для Room 3 пока не требуется, так как поддержка корутин встроена в runtime
    ksp("androidx.room3:room3-compiler:$roomVersion")

    // --- УДАЛЕНО: устаревшие android.arch.persistence.room артефакты ---
    // implementation("android.arch.persistence.room:runtime:1.0.0")
    // annotationProcessor("android.arch.persistence.room:compiler:1.0.0")

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Версии androidx.core берём из version catalog (1.19.0), без ручного понижения
    implementation(libs.androidx.core.ktx)
    // implementation("androidx.core:core:1.17.0") // <-- УДАЛЕНО: конфликт с catalog

    implementation("com.github.skydoves:expandablelayout:1.0.7")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}