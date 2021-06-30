package com.foundation.app

import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.foundation.app.basedialog.BaseDialog

class ChangeFloorDialog2(private val activity: AppCompatActivity): BaseDialog(activity) {
    override fun getLayoutId(): Int {
        return R.layout.dialog_change_floor
    }

    override fun initData() {}
    override fun onShow() {}
    override fun onDismiss() {}

    override fun convertView(view: View) {
        view.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dismiss()
        }
        view.findViewById<Button>(R.id.btn_confirm).setOnClickListener {
            activity.finish()
        }
    }

    override fun onDestroyDialog() {
        Toast.makeText(activity.application, "销毁了", LENGTH_SHORT).show()
    }

    override fun getGravity(): Int {
        return (Gravity.TOP)
    }

    override fun getDialogWidth(): Int {
        return WindowManager.LayoutParams.MATCH_PARENT
    }

    override fun getDialogHeight(): Int {
        return WindowManager.LayoutParams.WRAP_CONTENT
    }
}