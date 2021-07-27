package com.foundation.app

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.foundation.app.basedialog.BaseViewBindingDialog
import com.foundation.app.databinding.DialogChangeFloorBinding

class ChangeFloorDialog(activity: AppCompatActivity) : BaseViewBindingDialog<DialogChangeFloorBinding>(activity) {

    override fun convertView(binding: DialogChangeFloorBinding) {
        binding.btnCancel.setOnClickListener { dismiss() }
        binding.btnConfirm.setOnClickListener {
           activity.startActivity(Intent(activity, TwoActivity::class.java))
        }
    }

    override fun onDestroyDialog() {

    }

    override fun initData() {

    }

    override fun onShow() {}

    override fun onDismiss() {}
    override fun onClickOutside() {
        binding.btnConfirm.text = "点击了"
        Toast.makeText(activity.application, "点击了外部", Toast.LENGTH_SHORT).show()
    }
}