package com.foundation.buildsrc

import java.io.File
import java.util.*

private const val VERSION = "1.0.8"
private const val SNAPSHOT = false

/**
 * 如果空则为4级包名
 */
private const val ARTIFACT_ID = "base-dialog"

object Publish {
    object Version {
        var versionName = VERSION
            private set
            get() = when (SNAPSHOT) {
                true -> "$field-SNAPSHOT"
                false -> field
            }
        const val versionCode = 1

        private fun getTimestamp(): String {
            return java.text.SimpleDateFormat(
                "yyyy-MM-dd-hh-mm-ss",
                java.util.Locale.CHINA
            )
                .format(java.util.Date(System.currentTimeMillis()))
        }

        fun getVersionTimestamp(): String {
            return "$versionName-${getTimestamp()}"
        }
    }

    object Maven {
        val codingArtifactsRepoUrl =
            "https://mijukeji-maven.pkg.coding.net/repository/jileiku/base_maven/"
        val repositoryUserName: String
        val repositoryPassword: String
        val artifactId = "base-dialog"

        init {
            val localProperties = Properties()
            localProperties.load(File("local.properties").inputStream())
            val name = localProperties.getProperty("repositoryUserName")
            val password = localProperties.getProperty("repositoryPassword")
            if (name == null || password == null) {
                throw RuntimeException("请在local.properties添加私有仓库的用户名（repositoryUserName）和密码（repositoryPassword）")
            }
            repositoryUserName = name
            repositoryPassword = password
        }

        /**
         * 获取模块3级包名，如：com.foundation.widget
         */
        fun getThreePackage(projectDir: File): String {
            val st = getFourPackage(projectDir)
            return st.substring(0, st.lastIndexOf("."))
        }

        /**
         * 获取模块4级包名，如：com.foundation.widget.shape
         */
        fun getFourPackage(projectDir: File): String {
            return "com.foundation.app.basedialog"
        }

        /**
         * 上传库时的名字（如果配置为空则取四级包名名字）
         */
        fun getArtifactId(projectDir: File): String {
            return ARTIFACT_ID
        }

    }
}