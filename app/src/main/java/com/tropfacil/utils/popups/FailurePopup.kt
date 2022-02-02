package com.tropfacil.utils.popups

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.fragment.app.DialogFragment
import com.tropfacil.R
import kotlinx.android.synthetic.main.view_dialog_success.*


class FailurePopup : DialogFragment() {

    var options = Options()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_dialog_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCanceledOnTouchOutside(options.cancellable)
        isCancelable = false
        tv_title.text = getString(R.string.failure)

        tv_title.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorRed))
        tv_message.text = options.message
        options.actionButtonText?.let {
            btn_confirm.text = it
        }
        btn_confirm.setOnClickListener {
            options.onConfirm?.invoke()
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val w = dialog.window
        w?.requestFeature(Window.FEATURE_NO_TITLE)
        w?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        w?.decorView?.right = 100
        w?.decorView?.left = 100
        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )


    }

    class Options {
        var onConfirm: (() -> Unit)? = null
        var message = ""
        var actionButtonText: String? = null
        var cancellable = false
    }

    companion object {
        const val TAG = "FailurePopup"
        fun newInstance(block: Options.() -> Unit): FailurePopup {
            return FailurePopup().apply {
                this.options.apply(block)
            }
        }
    }
}