#BaseDialog

###### 需要的依赖

```
com.foundation.app:BaseDialog:0.0.1-SNAPSHOT
```

###### 使用示例

```
//通过QuickDialogBuilder快速创建
QuickDialogBuilder(DialogChangeFloorBinding::class.java, object: QuickDialogBuilder.ConvertViewImp<DialogChangeFloorBinding>{
                override fun convertView(binding: DialogChangeFloorBinding, dialog: BaseDialog<DialogChangeFloorBinding>) {
                    binding.btnCancel.setOnClickListener { dialog.dismiss() }
                    binding.btnConfirm.setOnClickListener { Toast.makeText(this@MainActivity, "确认", LENGTH_SHORT).show() }
                }
            }, BaseDialogConfig().height(600)).show(this)
```

```
//继承BaseDialog创建
ChangeFloorDialog().show(this)


class ChangeFloorDialog : BaseDialog<DialogChangeFloorBinding>() {
    override fun convertView(
        binding: DialogChangeFloorBinding,
        dialog: BaseDialog<DialogChangeFloorBinding>
    ) {
        binding.btnCancel.setOnClickListener { dialog.dismiss() }
        binding.btnConfirm.setOnClickListener {
            Toast.makeText(
                dialog.activity, "确认",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

```