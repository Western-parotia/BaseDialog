package com.foundation.app

import android.view.View
import android.widget.Button
import android.widget.Toast
import com.foundation.app.basedialog.BaseDialog
import com.foundation.app.basedialog.BaseViewBindingDialog
import com.foundation.app.databinding.DialogChangeFloorBinding

class ChangeFloorDialog1 : BaseDialog(R.layout.dialog_change_floor) {
    override fun convertView(view: View, dialog: BaseDialog) {
        view.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }
        view.findViewById<Button>(R.id.btn_confirm).setOnClickListener {
            Toast.makeText(
                dialog.activity, "确认",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}