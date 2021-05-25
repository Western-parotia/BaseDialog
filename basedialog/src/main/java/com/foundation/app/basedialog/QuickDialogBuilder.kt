package com.foundation.app.basedialog


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * 快速构建dialog，需传入viewbing和ConvertViewImp回调类
 */
class QuickDialogBuilder<T : ViewBinding>:
    BaseDialog<T> {
    private var imp: ConvertViewImp<T>
    private var cls : Class<out ViewBinding>
    private var config = BaseDialogConfig()

    /**
     * 使用默认配置初始化
     */
    constructor(cls : Class<out ViewBinding>, imp: ConvertViewImp<T>){
        this.cls = cls
        this.imp = imp
    }

    /**
     * 传入自定义个config初始化
     */
    constructor(cls : Class<out ViewBinding>, imp: ConvertViewImp<T>, config: BaseDialogConfig){
        this.cls = cls
        this.imp = imp
        this.config = config
    }

    override fun setConfig(): BaseDialogConfig {
        return config
    }

    override fun getBinding(container: ViewGroup?): T {
        return ViewBindingInitHelper.getViewBindingInstanceByClass(
            cls,
            LayoutInflater.from(context),
            null,
            false
        )!!
    }


    interface ConvertViewImp<T : ViewBinding> {
        fun convertView(binding: T, dialog: BaseDialog<T>)
    }

    override fun convertView(binding: T, dialog: BaseDialog<T>) {
        imp.convertView(binding, dialog)
    }


}
