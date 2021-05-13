package com.foundation.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 *@Desc:
 *-
 *- 获取ViewBinding 实际例
 *- 在特殊的地方一定需要一个viewBinding 占位，但却没有布局实例，
 * 可使用empty layout 完成绑定[com.nmm.smallfatbear.databinding.AppEmptyLayoutForViewBindingBinding]
 * -调用者应该明确在Superclass中声明了[B : ViewBinding]范型，否则将会提示空指针
 *create by zhusw on 4/22/21 10:48
 */
object ViewBindingInitHelper {
    /**
     * @param obj 当前类实例
     */
    fun <B : ViewBinding> getViewBindingInstance(obj: Any, layoutInflater: LayoutInflater
                                                 , container: ViewGroup?
                                                 , attachToParent: Boolean): B? {
        runCatching {
            val pt = obj::class.java.genericSuperclass as? ParameterizedType
            pt?.let { p ->
                 p.actualTypeArguments.forEach { aT ->
                    val clz = aT as Class<*>
                    if (clz.simpleName.contains("Binding")) {
                        val clzSuperClass = clz.genericInterfaces
                        clzSuperClass.forEach { clT ->
                            val supClz = clT as Class<*>
                            if (supClz == ViewBinding::class.java) {
                                @Suppress("UNCHECKED_CAST")
                                val bindingClass = aT as Class<B>
                               return getViewBindingInstanceByClass(bindingClass,layoutInflater,container, attachToParent)
                            }
                        }
                    }
                }
            }
        }
        return null
    }

    /**
     * 反射调用 viewbinding 实现类的 inflate 方法获取viewBinding实例
     * @param clz viewbinding 实现类型
     */
    fun <B : ViewBinding> getViewBindingInstanceByClass(clz: Class<out ViewBinding>
                                                        , layoutInflater: LayoutInflater
                                                        , container: ViewGroup?
                                                        , attachToParent: Boolean): B? {
         runCatching {
            val method = clz.getDeclaredMethod("inflate"
                    , LayoutInflater::class.java
                    , ViewGroup::class.java
                    , Boolean::class.java)
            @Suppress("UNCHECKED_CAST")
           return method.invoke(null, layoutInflater, container, attachToParent) as B
        }
        return null
    }

}