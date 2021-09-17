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

abstract class BaseDialog(
    val activity: ComponentActivity,
    @StyleRes themeResId: Int = R.style.CustomDialog
) : Dialog(activity, themeResId), LifecycleObserver {
    val rootLayout: FrameLayout = FrameLayout(activity).also {
        it.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
    }
    lateinit var dialogView: View

    private var clickRootDismiss = true

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //绑定activity生命周期
        activity.lifecycle.addObserver(this)
        initView()
        initData()

    }

    abstract fun initData()

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
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = dialogView.layoutParams.height
        params.gravity = (dialogView.layoutParams as FrameLayout.LayoutParams).gravity
        if (params.gravity == Gravity.LEFT || params.gravity == Gravity.START
            || params.gravity == Gravity.RIGHT || params.gravity == Gravity.END
        ) {
            params.gravity = params.gravity or Gravity.TOP
        }
        window?.attributes = params
        window?.setDimAmount(1.0F)
    }

    override fun setCanceledOnTouchOutside(cancel: Boolean) {
        clickRootDismiss = cancel
        super.setCanceledOnTouchOutside(cancel)
    }

    open fun getAnimStyle(): Int {
        return 0
    }

    private fun initView() {
        dialogView = getLayoutView(rootLayout)
        dialogView.isClickable = true
        rootLayout.addView(dialogView)
        rootLayout.setOnClickListener {
            if (clickRootDismiss) {
                dismiss()
            }
        }
        convertView(dialogView)
        window?.setContentView(rootLayout)
        //设置动画
        if (getAnimStyle() != 0) {
            window?.setWindowAnimations(getAnimStyle())
        }
    }

    protected open fun getLayoutView(rootLayout: FrameLayout): View =
        LayoutInflater.from(activity).inflate(getLayoutId(), rootLayout, false)

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