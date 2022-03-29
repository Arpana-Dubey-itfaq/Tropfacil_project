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
import androidx.fragment.app.DialogFragment
import com.tropfacil.R

import com.tropfacil.databinding.ViewPopupsBinding
import com.tropfacil.utils.gone
import com.tropfacil.utils.visible

class SuccessOrFailurePopup : DialogFragment() {
    private lateinit var binding: ViewPopupsBinding
    var options = Options()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewPopupsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCanceledOnTouchOutside(options.cancellable)
        binding.tvSuccessErrorMessage.text = options.message
        binding.btnOk.text = options.successBtnName

    /*    when (options.isSuccessPopUp) {
            1 -> {
                binding.btnNo.gone()
                binding.imgSuccessError.setImageResource(R.drawable.icon_popup_check)
            }
            2 -> {
                binding.btnNo.gone()
                binding.imgSuccessError.setImageResource(R.drawable.icon_popup_warning)
            }
            else -> {
                binding.btnNo.text = options.failureBtnName
                binding.btnNo.visible()
                binding.imgSuccessError.setImageResource(R.drawable.icon_popup_warning)
            }
        }
*/
        binding.btnOk.setOnClickListener {
            dismiss()
            options.onConfirm?.invoke()
        }

        binding.btnNo.setOnClickListener {
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
        var message = ""//APi response message or any message which wants to be displayed
        var successBtnName = ""//By default button name is "OK" else pass it here
        var failureBtnName = "" //By default button name is "NO" else pass it here
        var cancellable =
            false // pass false here if the popup shouldnt be dismissed by clicking outside
        var isSuccessPopUp =
            1//pass here 1 for success popup and 2 for api failure popup and 3 for 2 buttons display in case of failure
    }

    companion object {
        const val TAG = "SuccessOrFailurePopup"
        fun newInstance(block: Options.() -> Unit): SuccessOrFailurePopup {
            return SuccessOrFailurePopup().apply {
                this.options.apply(block)
            }
        }
    }
}