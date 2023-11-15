package com.fdbanks.ui.base


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.padho.R

abstract class BaseActivity : AppCompatActivity() {
    abstract fun observeViewModel()
    protected abstract fun initViewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
       /// updateSatus()
        super.onCreate(savedInstanceState)
        initViewBinding()
        observeViewModel()

    }

    override fun onResume() {
        super.onResume()
        updateStatusBarColor(R.color.white,true,android.R.color.white)
    }

    fun updateStatusBarColor(
        @ColorRes colorId: Int,
        isStatusBarFontDark: Boolean = true,
        statusBarColor: Int = android.R.color.transparent
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(this, statusBarColor)
            // window.navigationBarColor = ContextCompat.getColor(this, statusBarColor)
            window.setBackgroundDrawable(ContextCompat.getDrawable(this, colorId))
            setSystemBarTheme(isStatusBarFontDark)
        }
    }

    private fun setSystemBarTheme(isStatusBarFontDark: Boolean) {
        window.decorView.systemUiVisibility =
            if (isStatusBarFontDark) {
                0
            } else {
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            }
    }


fun updateSatus(){
    // Change the status bar color
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = Color.RED // Change to your desired color
    }

    // Change the status bar text color to dark
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

    inline fun <reified T : Activity> Context.startActivity(block: Intent.() -> Unit = {}) {
        startActivity(Intent(this, T::class.java).apply(block))
    }

    inline fun <reified T : Activity> Context.startActivityWithFinish(block: Intent.() -> Unit = {}) {
        startActivity(Intent(this, T::class.java).apply(block))
        finish()
    }

    fun hideKeyBoard(input: View?) {
        input?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(input.windowToken, 0)
        }
    }

    private fun hideKeyBoard(): Boolean {
        try {
            if (currentFocus != null) {
                val inputMethodManager =
                    this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?

                inputMethodManager?.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }

    private var toast: Toast? = null
    fun showToast(message: String?) {
        hideKeyBoard()
        toast?.cancel()
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast?.show()
    }

}
