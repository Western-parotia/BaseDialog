package com.foundation.app.basedialog

import android.view.*
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.annotation.StyleRes
import androidx.viewbinding.ViewBinding
import com.foundation.widget.binding.ViewBindingHelper

abstract class BaseViewBindingDialog<T : ViewBinding>(activity: ComponentActivity, @StyleRes themeResId: Int = R.style.CustomDialog) : BaseDialog(activity, themeResId) {
    lateinit var binding: T
    private set

    override fun getLayoutView(rootLayout: FrameLayout): View {
        binding = ViewBindingHelper.getViewBindingInstance(
            this@BaseViewBindingDialog,
            LayoutInflater.from(activity),
            rootLayout,
            false
        )
        convertView(binding)
        return binding.root
    }

    abstract fun convertView(binding: T)

    final override fun getLayoutId(): Int {
        return 0
    }

    final override fun convertView(view: View) {}

}