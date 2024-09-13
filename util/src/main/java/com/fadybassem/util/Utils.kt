package com.fadybassem.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment

/**
 * show toast message with context
 */
fun Context.showToastMessage(message: String?) {
    Toast.makeText(this, message ?: "\t", Toast.LENGTH_LONG).show()
}

/**
 * hide keyboard within the fragment
 */
fun Fragment.hideKeyboard() {
    val imm =
        this.requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var currentView = this.requireActivity().currentFocus
    if (currentView == null) {
        currentView = View(this.requireActivity())
    }
    imm.hideSoftInputFromWindow(currentView.windowToken, 0)
}

/**
 * hide keyboard within the activity
 */
fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var currentView = this.currentFocus
    if (currentView == null) {
        currentView = View(this)
    }
    imm.hideSoftInputFromWindow(currentView.windowToken, 0)
}

fun Activity.disableTouch() {
    this.window.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}

fun Fragment.disableTouch() {
    this.requireActivity().window.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}

fun Activity.enableTouch() {
    this.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Fragment.enableTouch() {
    this.requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Activity.hideSystemUI() {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, this.window.decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun Activity.showSystemUI() {
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(
        window, this.window.decorView
    ).show(WindowInsetsCompat.Type.systemBars())
}