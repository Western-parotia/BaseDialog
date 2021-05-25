package com.foundation.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.foundation.app.databinding.DialogChangeFloorBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tv_show).setOnClickListener {
            //通过QuickDialogBuilder快速创建
            QuickDialogBuilder(DialogChangeFloorBinding::class.java, object: QuickDialogBuilder.ConvertViewImp<DialogChangeFloorBinding>{
                override fun convertView(binding: DialogChangeFloorBinding, dialog: BaseDialog<DialogChangeFloorBinding>) {
                    binding.btnCancel.setOnClickListener { dialog.dismiss() }
                    binding.btnConfirm.setOnClickListener { Toast.makeText(this@MainActivity, "确认", LENGTH_SHORT).show() }
                }
            }, BaseDialogConfig().height(600)).show(this)

            //继承BaseDialog创建
            ChangeFloorDialog().show(this)
        }

    }
    
}