package com.foundation.app.basedialog

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager




class ScreenUtils {

    companion object {

        /**
         * 获取屏幕高不包括状态栏
         *
         * @param context
         * @return
         */
        fun getScreenHeightNoStatusBar(context: Context): Int {
            val localDisplayMetrics = DisplayMetrics()
            val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            manager.defaultDisplay.getMetrics(localDisplayMetrics)
            return localDisplayMetrics.heightPixels
        }

    }
}