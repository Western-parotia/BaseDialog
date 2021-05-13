package com.foundation.app

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics


class ScreenUtils {
    /**
     * dip to px
     *
     * @param dpValue
     * @return
     */
    companion object {

        fun dip2px(context: Context, dpValue: Float): Int {
            return (0.5f + dpValue * context.applicationContext
                .resources.displayMetrics.density).toInt()
        }

        /**
         * getScreenWidth
         *
         * @param context
         * @return
         */
        fun getScreenWidth(context: Context): Int {
            val localDisplayMetrics = DisplayMetrics()
            (context as Activity).windowManager.defaultDisplay.getMetrics(localDisplayMetrics)
            return localDisplayMetrics.widthPixels
        }

    }
}