package com.tropfacil.base

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tropfacil.R
import com.tropfacil.base.BaseActivity

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






    interface LeustDialogListner {
        fun FirstButtonClickListner()
        fun SecondButtonClickListner()
    }



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


}