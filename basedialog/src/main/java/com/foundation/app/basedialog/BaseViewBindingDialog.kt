package com.foundation.app.basedialog

import android.view.*
import androidx.activity.ComponentActivity
import androidx.annotation.StyleRes
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingDialog<T : ViewBinding>(activity: ComponentActivity, @StyleRes themeResId: Int = R.style.CustomDialog) : BaseDialog(activity, themeResId) {
    lateinit var binding: T
    private set

    override fun initView() {
        binding = ViewBindingInitHelper.getViewBindingInstance(
            this@BaseViewBindingDialog,
            LayoutInflater.from(activity),
            rootLayout,
            false
        )!!
        binding.root.isClickable = true
        rootLayout.addView(binding.root)
        rootLayout.setOnClickListener {
            if (cancelOutSide) {
                dismiss()
            } else {
                onClickOutside()
            }
        }
        convertView(binding)
        window?.setContentView(rootLayout)
    }

    abstract fun convertView(binding: T)

    final override fun getLayoutId(): Int {
        return 0
    }

    final override fun convertView(view: View) {}

}