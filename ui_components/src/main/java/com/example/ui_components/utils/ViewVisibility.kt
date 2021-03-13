package com.example.ui_components.utils

import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.enable() {
    this.isEnabled = true
}

fun View.enable(isEnabled: Boolean) {
    this.isEnabled = isEnabled
}

fun View.disable() {
    this.isEnabled = false
}