#BaseDialog

###### 需要的依赖

```
com.foundation.app:BaseDialog:1.0.0
```

###### 使用示例

```
//继承BaseDialog创建
ChangeFloorDialog2(this@TwoActivity).show()


class ChangeFloorDialog2(private val activity: AppCompatActivity): BaseDialog(activity) {
    override fun getLayoutId(): Int {
        return R.layout.dialog_change_floor
    }

    override fun initData() {}

    //回调dialog的view
    override fun convertView(view: View) {
        view.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dismiss()
        }
        view.findViewById<Button>(R.id.btn_confirm).setOnClickListener {
            activity.finish()
        }
    }

    //当activity销毁时调用，在该方法中做资源释放
    override fun onDestroyDialog() {
        Toast.makeText(activity.application, "销毁了", LENGTH_SHORT).show()
    }

    //这里支持系统提供的Gravity值
    override fun getGravity(): Int {
        return (Gravity.TOP)
    }

    //这里支持WindowManager.LayoutParams.WRAP_CONTENT、WindowManager.LayoutParams.MATCH_PARENT、具体dp值
    override fun getDialogWidth(): Int {
        return WindowManager.LayoutParams.MATCH_PARENT
    }

    //这里支持WindowManager.LayoutParams.WRAP_CONTENT、WindowManager.LayoutParams.MATCH_PARENT、具体dp值
    override fun getDialogHeight(): Int {
        return WindowManager.LayoutParams.WRAP_CONTENT
    }
}
```

```
//继承BaseViewBindingDialog创建
ChangeFloorDialog(this@MainActivity).show()


class ChangeFloorDialog(private val activity: AppCompatActivity) : BaseViewBindingDialog<DialogChangeFloorBinding>(activity) {

    //回调dialog的viewbinding
    override fun convertView(binding: DialogChangeFloorBinding) {
        binding.btnCancel.setOnClickListener { dismiss() }
        binding.btnConfirm.setOnClickListener {
           activity.startActivity(Intent(activity, TwoActivity::class.java))
        }
    }

    //当activity销毁时调用，在该方法中做资源释放
    override fun onDestroyDialog() {}

    override fun initData() {}

    //这里支持系统提供的Gravity值
    override fun getGravity(): Int {
        return Gravity.BOTTOM
    }

    //这里支持WindowManager.LayoutParams.WRAP_CONTENT、WindowManager.LayoutParams.MATCH_PARENT、具体dp值
    override fun getDialogHeight(): Int {
        return 400
    }

    override fun getBackgroundDrawable(): Drawable {
        return ColorDrawable(Color.BLUE)
    }
}
```

