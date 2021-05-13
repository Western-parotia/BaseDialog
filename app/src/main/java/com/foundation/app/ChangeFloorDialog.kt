package com.foundation.app

import android.widget.Toast
import com.foundation.app.databinding.DialogChangeFloorBinding

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