package com.foundation.buildsrc

object Dependencies {
    private const val kotlinVersion = "1.4.32"

    object Kotlin {
        const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    }

    object AndroidX {
        const val core_ktx = "androidx.core:core-ktx:1.3.2"
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val fragment = "androidx.fragment:fragment:1.3.3"
        const val activity = "androidx.activity:activity:1.2.2"
    }

    object Material {
        const val material = "com.google.android.material:material:1.3.0"
    }

    object Company {
        const val basedialog = "com.foundation.app:base-dialog:1.0.1"
    }
}