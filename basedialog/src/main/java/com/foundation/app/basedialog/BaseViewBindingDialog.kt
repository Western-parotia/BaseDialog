package com.foundation.app.basedialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.annotation.StyleRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingDialog<T : ViewBinding> : Dialog, LifecycleObserver {
    private lateinit var binding: T
    private var rootLayout: FrameLayout ?= null
    private var activity: ComponentActivity

    constructor(mActivity: ComponentActivity): this(mActivity, R.style.CustomDialog)

    constructor(mActivity: ComponentActivity, @StyleRes themeResId: Int): super(mActivity, themeResId){
        activity = mActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //绑定activity生命周期
        activity.lifecycle.addObserver(this)
        initRootLayout()
        initData()
        initView()
    }

    abstract fun initData()

    private fun initRootLayout() {
        rootLayout = FrameLayout(activity).also {
            it.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        //设置动画
        if (getAnimStyle() != 0) {
            window?.setWindowAnimations(getAnimStyle())
        }
    }

    protected open fun getAnimStyle(): Int {
        return 0
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
        //phonewindow永远是match_parent的
        val params: WindowManager.LayoutParams = window!!.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        window?.attributes = params
    }


    private fun initView() {
        binding = ViewBindingInitHelper.getViewBindingInstance(
            this@BaseViewBindingDialog,
            LayoutInflater.from(activity),
            rootLayout,
            false
        )!!
        binding.root.isClickable = true
        rootLayout?.let{
            it.addView(binding.root)
            it.setOnClickListener {
                if(isCancelableOutside()){
                    dismiss()
                }else{
                    onClickOutside()
                }
            }
            convertView(binding)
        }
        window?.setContentView(rootLayout)
    }

    protected open fun isCancelableOutside(): Boolean {
        return false
    }

    abstract fun convertView(t: T)

    /**
     * 如果设置了点击外部区域不dismiss，会调用该方法
     */
    abstract fun onClickOutside()

    /**
     * 获取根布局
     */
    fun getRootLayout(): FrameLayout?{
        return rootLayout
    }

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