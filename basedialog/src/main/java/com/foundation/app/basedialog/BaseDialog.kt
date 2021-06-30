package com.foundation.app.basedialog

import android.app.Dialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

abstract class BaseDialog(private val activity: ComponentActivity) : Dialog(activity), LifecycleObserver {


    private var mConfig: BaseDialogConfig? = null
    private lateinit var dialogView: View

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //绑定activity生命周期
        activity.lifecycle.addObserver(this)
        mConfig = initConfig()
        initParams()
        initData()
        initView()
    }

    abstract fun initData()

    private fun initParams() {
        window?.requestFeature(Window.FEATURE_NO_TITLE)
        val params: WindowManager.LayoutParams = window!!.attributes
        //设置黑暗度
        params.dimAmount = getDimAmount()
        //设置透明度
        params.alpha = getAlpha()
        //设置dialog显示位置
        params.gravity = getGravity()


        //设置动画
        if (getAnimStyle() != 0) {
            window?.setWindowAnimations(getAnimStyle())
        }
        window?.attributes = params
        setCanceledOnTouchOutside(isCancelableOutside())
    }

    override fun dismiss() {
        super.dismiss()
        onDismiss()
    }

    abstract fun onShow()

    abstract fun onDismiss()

    override fun show() {
        super.show()
        onShow()
        val params: WindowManager.LayoutParams = window!!.attributes
        //设置dialog宽度
        when {
            getDialogWidth() == WindowManager.LayoutParams.WRAP_CONTENT -> {
                params.width = WindowManager.LayoutParams.WRAP_CONTENT
            }
            getDialogWidth() == WindowManager.LayoutParams.MATCH_PARENT -> {
                params.width = ScreenUtils.getScreenWidth(activity) - 2 * ScreenUtils.dip2px( activity, getMargin().toFloat())
            }
            else -> {
                params.width = ScreenUtils.dip2px(activity, getDialogWidth().toFloat())
            }
        }

        //设置Dialog的Height
        when {
            getDialogHeight() == WindowManager.LayoutParams.WRAP_CONTENT -> {
                params.height = WindowManager.LayoutParams.WRAP_CONTENT
            }
            getDialogHeight() == WindowManager.LayoutParams.MATCH_PARENT -> {
                params.height = WindowManager.LayoutParams.MATCH_PARENT
            }
            else -> {
                params.height = ScreenUtils.dip2px(activity, getDialogHeight().toFloat())
            }
        }
        window?.attributes = params
    }

    protected open fun isCancelableOutside(): Boolean {
        return mConfig!!.isCancelableOutside
    }

    //直接使用的dp值
    protected open fun getMargin(): Int {
        return mConfig!!.margin
    }

    protected open fun getAnimStyle(): Int {
        return mConfig!!.animStyle
    }

    /**
     * 这里支持WindowManager.LayoutParams.WRAP_CONTENT、WindowManager.LayoutParams.MATCH_PARENT、具体dp值
     */
    protected open fun getDialogWidth(): Int {
        return mConfig!!.width
    }

    /**
     * 这里支持WindowManager.LayoutParams.WRAP_CONTENT、WindowManager.LayoutParams.MATCH_PARENT、具体dp值
     */
    protected open fun getDialogHeight(): Int {
        return mConfig!!.height
    }

    open fun getDimAmount(): Float {
        return mConfig!!.dimAmount
    }

    open fun getAlpha(): Float {
        return mConfig!!.alpha
    }

    /**
     * 这里支持系统提供的Gravity值
     */
    protected open fun getGravity(): Int {
        return mConfig!!.gravity
    }

    protected open fun getBackgroundDrawable(): Drawable {
        return mConfig!!.backgroundDrawable
    }

    protected open fun initView() {
        dialogView = LayoutInflater.from(activity).inflate(getLayoutId(), null)
        window?.setContentView(dialogView)
        window?.setBackgroundDrawable(getBackgroundDrawable())
        convertView(dialogView)
    }

    abstract fun convertView(view: View)

    /**
     * 当activity销毁时调用，在该方法中做资源释放
     */
    abstract fun onDestroyDialog()

    protected open fun initConfig(): BaseDialogConfig {
        return BaseDialogConfig()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        if (isShowing) {
            dismiss()
        }
        onDestroyDialog()
    }


}