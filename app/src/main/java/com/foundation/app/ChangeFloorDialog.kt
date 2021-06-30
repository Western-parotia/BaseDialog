package com.foundation.app

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.foundation.app.basedialog.BaseViewBindingDialog
import com.foundation.app.basedialog.ScreenUtils.Companion.dip2px
import com.foundation.app.databinding.DialogChangeFloorBinding

class ChangeFloorDialog(private val activity: AppCompatActivity) : BaseViewBindingDialog<DialogChangeFloorBinding>(activity) {

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

    override fun getGravity(): Int {
        return Gravity.BOTTOM
    }


    override fun getDialogHeight(): Int {
        return 400
    }

    override fun getBackgroundDrawable(): Drawable {
        return ColorDrawable(Color.BLUE)
    }
}