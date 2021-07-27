package com.foundation.app.basedialog

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.annotation.StyleRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

abstract class BaseDialog(val activity: ComponentActivity, @StyleRes themeResId: Int = R.style.CustomDialog) : Dialog(activity, themeResId), LifecycleObserver {
    val rootLayout: FrameLayout = FrameLayout(activity).also {
        it.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
    }
    lateinit var dialogView: View
    open var cancelOutSide = true

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //绑定activity生命周期
        activity.lifecycle.addObserver(this)
        initData()
        initView()
    }

    abstract fun initData()

    override fun dismiss() {
        super.dismiss()
        onDismiss()
    }

    abstract fun onShow()

    abstract fun onDismiss()

    /**
     * 如果设置了点击外部区域不dismiss，会调用该方法
     */
    open fun onClickOutside(){

    }

    override fun show() {
        super.show()
        onShow()
        //phonewindow永远是match_parent的
        val params: WindowManager.LayoutParams = window!!.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        window?.attributes = params
    }

    override fun setCanceledOnTouchOutside(cancel: Boolean) {
        cancelOutSide = cancel
    }

    open fun getAnimStyle(): Int {
        return 0
    }

    open fun initView() {
        dialogView = LayoutInflater.from(activity).inflate(getLayoutId(), rootLayout, false)
        dialogView.isClickable = true
        rootLayout.addView(dialogView)
        rootLayout.setOnClickListener {
            if (cancelOutSide) {
                dismiss()
            } else {
                onClickOutside()
            }
        }
        convertView(dialogView)
        window?.setContentView(rootLayout)
        //设置动画
        if (getAnimStyle() != 0) {
            window?.setWindowAnimations(getAnimStyle())
        }
    }

    abstract fun convertView(view: View)

    /**
     * 当activity销毁时调用，在该方法中做资源释放
     */
    abstract fun onDestroyDialog()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        if (isShowing) {
            dismiss()
        }
        onDestroyDialog()
    }


}