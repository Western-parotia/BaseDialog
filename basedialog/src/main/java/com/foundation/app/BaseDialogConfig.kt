package com.foundation.app

import android.view.Gravity
import android.view.WindowManager

class BaseDialogConfig {
    var isCancelableOutside = false
    var margin = 0
    var animStyle = 0
    var width = WindowManager.LayoutParams.WRAP_CONTENT
    var height = WindowManager.LayoutParams.WRAP_CONTENT
    var dimAmount = 0.2f
    var gravity = Gravity.CENTER
    var animRes = 0

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

    fun gravity(gravity: Int): BaseDialogConfig {
        this.gravity = gravity
        return this
    }

    fun animRes(animRes: Int): BaseDialogConfig {
        this.animRes = animRes
        return this
    }


}