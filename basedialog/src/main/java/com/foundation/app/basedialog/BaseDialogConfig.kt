package com.foundation.app.basedialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.WindowManager

/**
 * dialog配置类
 */
class BaseDialogConfig {
    var isCancelableOutside = false
    var margin = 0
    var animStyle = 0
    var width = WindowManager.LayoutParams.WRAP_CONTENT
    var height = WindowManager.LayoutParams.WRAP_CONTENT
    var dimAmount = 0.2f
    var alpha = 1.0f
    var gravity = Gravity.CENTER
    var backgroundDrawable : Drawable = ColorDrawable(Color.TRANSPARENT)

    fun isCancelableOutside(isCancelableOutside: Boolean): BaseDialogConfig {
        this.isCancelableOutside = isCancelableOutside
        return this
    }

    fun margin(margin: Int): BaseDialogConfig {
        this.margin = margin
        return this
    }

    fun animStyle(animStyle: Int): BaseDialogConfig {
        this.animStyle = animStyle
        return this
    }

    fun width(width: Int): BaseDialogConfig {
        this.width = width
        return this
    }

    fun height(height: Int): BaseDialogConfig {
        this.height = height
        return this
    }

    fun dimAmount(dimAmount: Float): BaseDialogConfig {
        this.dimAmount = dimAmount
        return this
    }

    fun alpha(alpha: Float): BaseDialogConfig{
        this.alpha = alpha
        return this
    }

    fun gravity(gravity: Int): BaseDialogConfig {
        this.gravity = gravity
        return this
    }

    fun backgroundDrawable(backgroundDrawable: Drawable): BaseDialogConfig {
        this.backgroundDrawable = backgroundDrawable
        return this
    }


}