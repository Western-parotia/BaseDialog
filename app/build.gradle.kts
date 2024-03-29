import com.foundation.buildsrc.Dependencies
import com.foundation.buildsrc.Statics

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion(31)

    defaultConfig {
        applicationId = "com.foundation.basedialog"
        minSdkVersion(21)
        targetSdkVersion(31)

        resValue("string", "app_name", Statics.APP_NAME)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.Kotlin.kotlin_stdlib)
    implementation(Dependencies.AndroidX.core_ktx)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.fragment)
    implementation(Dependencies.AndroidX.activity)
    implementation(Dependencies.Material.material)
//    implementation(Dependencies.Company.basedialog)
    implementation(project(":BaseDialog"))

}
configurations.all {
    resolutionStrategy {
        // don't cache changing modules at all
        cacheChangingModulesFor(10, "seconds")
    }
}