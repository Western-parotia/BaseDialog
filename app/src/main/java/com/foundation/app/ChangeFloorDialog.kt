package com.foundation.app

import android.widget.Toast
import com.foundation.app.basedialog.BaseDialog
import com.foundation.app.basedialog.BaseViewBindingDialog
import com.foundation.app.databinding.DialogChangeFloorBinding

class ChangeFloorDialog : BaseViewBindingDialog<DialogChangeFloorBinding>() {
    override fun convertView(
        binding: DialogChangeFloorBinding,
        dialog: BaseViewBindingDialog<DialogChangeFloorBinding>
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