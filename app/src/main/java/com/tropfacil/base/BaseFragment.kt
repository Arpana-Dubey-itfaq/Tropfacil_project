package com.tropfacil.base

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tropfacil.R

open class BaseFragment : Fragment() {

    val TAG = javaClass.simpleName

    fun showDialog(title: String?, onDismiss: () -> Unit) {
        val builder: AlertDialog.Builder = let {
            val builder = AlertDialog.Builder(requireContext())
            builder.apply {
                setPositiveButton(getString(R.string.ok)) { dialog, id ->
                    onDismiss()
                    dialog.dismiss()
                }
            }
        }
        builder.setTitle(title)?.setCancelable(false)
        builder.create()
        builder.show()
    }
/*
    fun showLeustDialog(message: String?) {
        val binding = DialogLeustBinding.inflate(layoutInflater)
        val builder: AlertDialog.Builder = let {
            val builder = AlertDialog.Builder(requireContext())
            builder.apply {
                setView(binding.root)
                binding.txtMessage.text = message

            }
        }

        val dialog = builder.show()

        binding.txtClose.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun showLeustDialog(message: String?, onDismiss: () -> Unit) {
        val binding = DialogLeustBinding.inflate(layoutInflater)
        val builder: AlertDialog.Builder = let {
            val builder = AlertDialog.Builder(requireContext())
            builder.apply {
                setView(binding.root)
                binding.txtMessage.text = message

            }
        }

        val dialog = builder.show()

        binding.txtClose.setOnClickListener {
            onDismiss()
            dialog.dismiss()
        }
    }*/

 /*   fun showBottomSheet(header: String, message: String, onPositive: () -> Unit,onNegetive: () -> Unit) {


        val binding = DialogBottomsheetConfirmLeustBinding.inflate(layoutInflater)

        val builder = BottomSheetDialog(requireContext())
        builder.apply {
            setContentView(binding.root)
        }
        var dialog = builder.show()
        binding.txtHeader.text = header
        binding.txtMessage.text = message

        binding.txtCancel.setOnClickListener {
            onNegetive()
            builder.dismiss()
        }
        binding.txtYes.setOnClickListener {
            onPositive()
            builder.dismiss()

        }


    }

    fun showLeustConfirmDialog(message: String?, onPositive: () -> Unit, onNegetive: () -> Unit) {
        val binding = DialogConfirmLeustBinding.inflate(layoutInflater)
        val builder: AlertDialog.Builder = let {
            val builder = AlertDialog.Builder(requireContext())
            builder.apply {
                setView(binding.root)
                binding.txtMessage.text = message

            }
        }

        val dialog = builder.show()
        binding.tvNo.setOnClickListener {
            onNegetive()
            dialog.dismiss()
        }
        binding.tvYes.setOnClickListener {
            onPositive()
            dialog.dismiss()
        }
    }

    interface LeustDialogListner {
        fun FirstButtonClickListner()
        fun SecondButtonClickListner()
    }

    fun showLeustOKCancelDialog(message: String?, leustDialogListner: LeustDialogListner) {
        val binding = DialogOkcancelleustBinding.inflate(layoutInflater)
        val builder: AlertDialog.Builder = let {
            val builder = AlertDialog.Builder(requireContext())
            builder.apply {
                setView(binding.root)
                binding.txtMessage.text = message

            }
        }

        val dialog = builder.show()

        binding.txtCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.txtOk.setOnClickListener {

            leustDialogListner.FirstButtonClickListner()
            dialog.dismiss()

        }
    }*/

    open fun getBase(): BaseActivity? {
        return context as BaseActivity?
    }

    fun expandCollapsSheet(
        bottomSheet: BottomSheetBehavior<ConstraintLayout>,
        viewTransprent: View
    ) {
        if (bottomSheet.state == BottomSheetBehavior.STATE_EXPANDED) {
            viewTransprent.visibility = View.GONE
            bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
        } else {
            bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
            viewTransprent.visibility = View.VISIBLE
        }
    }

    fun handleBottomSheetCallback(
        sheet: BottomSheetBehavior<ConstraintLayout>,
        viewTransprent: View
    ) {
        fun handleBottomSheetCallback(
            sheet: BottomSheetBehavior<ConstraintLayout>,
            viewTransprent: View
        ) {

            sheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) { // handle onSlide
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        viewTransprent.visibility = View.GONE
                    } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                        viewTransprent.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

  /*  fun getTitle(value: String?): String {
        if (value.equals("event", true)) {
            return getString(R.string.event_campaign)
        } else {
            return getString(R.string.product_campaign)
        }
    }*/
}