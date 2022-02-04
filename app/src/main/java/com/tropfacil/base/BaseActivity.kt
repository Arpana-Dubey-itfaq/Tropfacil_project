package com.tropfacil.base

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.tropfacil.R

open class BaseActivity : AppCompatActivity() {

    var progressLottieAnimationView: LottieAnimationView? = null
    val TAG = javaClass.simpleName

    fun loadProgressLoader(parentLayout: ConstraintLayout) {
        if (progressLottieAnimationView == null) {
            progressLottieAnimationView.let {
                progressLottieAnimationView = LottieAnimationView(this)
                progressLottieAnimationView?.layoutParams = ViewGroup.LayoutParams(200, 200)
                progressLottieAnimationView?.setAnimation("loader.json")
                progressLottieAnimationView?.repeatCount = LottieDrawable.INFINITE
                progressLottieAnimationView?.setBackgroundColor(
                    ActivityCompat.getColor(this, R.color.color_progress_transparent)
                )

                val set = ConstraintSet()
                progressLottieAnimationView?.id = View.generateViewId()
                parentLayout.addView(progressLottieAnimationView!!, parentLayout.childCount)

                set.clone(parentLayout)
                set.connect(
                    progressLottieAnimationView?.id!!, ConstraintSet.TOP, parentLayout.id,
                    ConstraintSet.TOP, 0
                )
                set.connect(
                    progressLottieAnimationView?.id!!, ConstraintSet.END, parentLayout.id,
                    ConstraintSet.END, 0
                )
                set.connect(
                    progressLottieAnimationView?.id!!, ConstraintSet.BOTTOM, parentLayout.id,
                    ConstraintSet.BOTTOM, 0
                )
                set.connect(
                    progressLottieAnimationView?.id!!, ConstraintSet.START, parentLayout.id,
                    ConstraintSet.START, 0
                )
                set.applyTo(parentLayout)
                progressLottieAnimationView?.visibility = View.INVISIBLE
            }
        }
    }

    fun showProgressView() {
        if (progressLottieAnimationView != null) {
            progressLottieAnimationView.let {
                if (progressLottieAnimationView?.visibility != View.VISIBLE) {
                    progressLottieAnimationView?.playAnimation()
                    progressLottieAnimationView?.visibility = View.VISIBLE
                    window.setFlags(
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    )
                }
            }
        }
    }

    fun hideProgressView() {
        if (progressLottieAnimationView != null) {
            progressLottieAnimationView.let {
                if (progressLottieAnimationView?.visibility == View.VISIBLE) {
                    progressLottieAnimationView?.pauseAnimation()
                    progressLottieAnimationView?.visibility = View.INVISIBLE
                    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                }
            }
        }
    }

//    fun showSnackBar(view: View, message: String?, actionMessage: String?, retryAction: Boolean) {
//        val errorSnackBar = Snackbar.make(view, message!!, Snackbar.LENGTH_LONG).setAction(actionMessage) {
//            if (retryAction) {
//                // need to call api recall method
//            }
//        }
//        errorSnackBar.setBackgroundTint(ContextCompat.getColor(this, R.color.dark_grey))
//        errorSnackBar.show()
//    }

    fun showSnackBar(message: String) {
          runOnUiThread(Runnable {
              if (message != null && !message.equals("null") && !message.equals("")) showMessage(message)
              else showMessage(getString(R.string.something_went_wrong))
          })
    }


    protected fun showMessage(message: String, isErrorMessage: Boolean = false) {
        window?.decorView?.rootView?.let {
            val snackBar = Snackbar.make(it, message, Snackbar.LENGTH_SHORT)
            showSnackBar(snackBar, isErrorMessage)
        }
    }

    private fun showSnackBar(snackBar: Snackbar, isErrorMessage: Boolean = false) {
        snackBar.setTextColor(Color.WHITE)
        val green = ContextCompat.getColor(this, R.color.green)
        snackBar.setBackgroundTint(if (isErrorMessage) Color.RED else green)
        val messageView: TextView =
            snackBar.view.findViewById(com.google.android.material.R.id.snackbar_text)
        messageView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        val view = snackBar.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        params.setMargins(0, 60, 0, 0)
        view.layoutParams = params
        snackBar.show()
    }


    fun showDialog(title: String?, onDismiss: () -> Unit) {
        val builder: AlertDialog.Builder = let {
            val builder = AlertDialog.Builder(it)
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




    fun showToast(message: String?) {
        runOnUiThread(Runnable {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })
    }

    fun changeFrag(fragment: Fragment?, isBackStack: Boolean, isPopBack: Boolean) {
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        if (isPopBack) {
            fm.popBackStack()
        }
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.replace(R.id.fragment_container, fragment!!)
        fragmentTransaction.commit()
    }

    fun getCurrentFrg(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.fragment_container)
    }

}