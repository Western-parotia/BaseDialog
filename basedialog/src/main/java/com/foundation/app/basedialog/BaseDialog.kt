package com.foundation.app.basedialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.annotation.StyleRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

abstract class BaseDialog : Dialog, LifecycleObserver {

    private lateinit var dialogView: View
    private var rootLayout: FrameLayout ?= null
    private var mActivity: ComponentActivity

    constructor(activity: ComponentActivity): this(activity, R.style.CustomDialog)

    constructor(activity: ComponentActivity, @StyleRes themeResId: Int): super(activity, themeResId){
        mActivity = activity
    }

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //绑定activity生命周期
        mActivity.lifecycle.addObserver(this)
        initRootLayout()
        initData()
        initView()
    }

    abstract fun initData()

    private fun initRootLayout() {
        rootLayout = FrameLayout(mActivity).also {
            it.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }

        //设置动画
        if (getAnimStyle() != 0) {
            window?.setWindowAnimations(getAnimStyle())
        }
    }

    override fun dismiss() {
        super.dismiss()
        onDismiss()
    }

    /**
     * 获取根布局
     */
    fun getRootLayout(): FrameLayout?{
        return rootLayout
    }

    abstract fun onShow()

    abstract fun onDismiss()

    /**
     * 如果设置了点击外部区域不dismiss，会调用该方法
     */
    abstract fun onClickOutside()

    override fun show() {
        super.show()
        onShow()
        //phonewindow永远是match_parent的
        val params: WindowManager.LayoutParams = window!!.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        window?.attributes = params
    }

    protected open fun isCancelableOutside(): Boolean {
        return false
    }

    protected open fun getAnimStyle(): Int {
        return 0
    }

    protected open fun initView() {
        dialogView = LayoutInflater.from(mActivity).inflate(getLayoutId(), rootLayout,false)
        dialogView.isClickable=true
        rootLayout?.let{
            it.addView(dialogView)
            it.setOnClickListener {
                if(isCancelableOutside()){
                    dismiss()
                }else{
                    onClickOutside()
                }
            }
            convertView(dialogView)
        }
        window?.setContentView(rootLayout)
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