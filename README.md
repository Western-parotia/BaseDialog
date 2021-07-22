#BaseDialog

###### 需要的依赖

```
com.foundation.app:base-dialog:1.0.1
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
    override fun onShow() {}
    override fun onDismiss() {}

    override fun isCancelableOutside(): Boolean {
        return false
    }

    override fun onClickOutside() {
        Toast.makeText(activity.application, "点击了外部", LENGTH_SHORT).show()
    }

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
    override fun onDestroyDialog() {

    }

    override fun initData() {

    }

    override fun onShow() {}

    override fun onDismiss() {}
    override fun onClickOutside() {
        Toast.makeText(activity.application, "点击了外部", Toast.LENGTH_SHORT).show()
    }
}
```

