package com.foundation.app.basedialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

abstract class BaseDialog(@LayoutRes private val layoutId: Int) : DialogFragment(), DialogLifecycleListener {
    private lateinit var mContext: Context
    private var mConfig: BaseDialogConfig?= null
    private lateinit var dialogView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialogView = inflater.inflate(layoutId, container, false)
        onDialogCreateView()
        convertView(dialogView, this)
        return dialogView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mConfig = setConfig()
        //如果isCancelable()是false 则会屏蔽物理返回键
        dialog?.setCancelable(isCancelable)
        //如果isCancelableOutside()为false 点击屏幕外Dialog不会消失；反之会消失
        dialog?.setCanceledOnTouchOutside(isCancelableOutside())
        //如果isCancelable()设置的是false 会屏蔽物理返回键
        dialog?.setOnKeyListener { _, keyCode, event ->
            keyCode == KeyEvent.KEYCODE_BACK && event.action === KeyEvent.ACTION_DOWN && !isCancelable
        }
    }

    override fun onStart() {
        super.onStart()
        initParams()
    }


    protected open fun initParams() {
        val window = dialog?.window
        window?.let {
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val params: WindowManager.LayoutParams = window.attributes
            //设置黑暗度
            params.dimAmount = getDimAmount()
            //设置透明度
            params.alpha = getAlpha()

            //设置dialog显示位置
            params.gravity = getGravity()

            //设置dialog宽度
            when {
                getDialogWidth() == WindowManager.LayoutParams.WRAP_CONTENT -> {
                    params.width = WindowManager.LayoutParams.WRAP_CONTENT
                }
                getDialogWidth() == WindowManager.LayoutParams.MATCH_PARENT -> {
                    params.width = ScreenUtils.getScreenWidth(
                        mContext
                    ) - 2 * ScreenUtils.dip2px(
                        mContext,
                        getMargin().toFloat()
                    )
                }
                else -> {
                    params.width =
                        ScreenUtils.dip2px(
                            mContext,
                            getDialogWidth().toFloat()
                        )
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
                    params.height =
                        ScreenUtils.dip2px(
                            mContext,
                            getDialogHeight().toFloat()
                        )
                }
            }
            //设置动画
            if(getAnimStyle() !=0){
                window.setWindowAnimations(getAnimStyle())
            }

            window.attributes = params
        }
    }

    protected open fun setConfig(): BaseDialogConfig {
        return BaseDialogConfig()
    }


    protected open fun isCancelableOutside(): Boolean {
        return mConfig!!.isCancelableOutside
    }

    protected open fun getMargin(): Int {
        return mConfig!!.margin
    }

    protected open fun getAnimStyle(): Int {
        return mConfig!!.animStyle
    }

    protected open fun getDialogWidth(): Int {
        return mConfig!!.width
    }

    protected open fun getDialogHeight(): Int {
        return mConfig!!.height
    }

    open fun getDimAmount(): Float {
        return mConfig!!.dimAmount
    }

    open fun getAlpha(): Float {
        return mConfig!!.alpha
    }

    protected open fun getGravity(): Int {
        return mConfig!!.gravity
    }

    open fun show(activity: FragmentActivity) {
        show(activity.supportFragmentManager)
    }

    open fun show(fragment: Fragment) {
        show(fragment.childFragmentManager)
    }

    private fun show(manager: FragmentManager) {
        try {
            manager.beginTransaction().remove(this).commitAllowingStateLoss()
            super.show(manager, javaClass::getSimpleName.toString())
        } catch (e: Exception) {
        }
    }

    abstract fun convertView(view: View, dialog: BaseDialog)

    override fun onDestroyView() {
        onDialogDestroyView()
        super.onDestroyView()
    }

}