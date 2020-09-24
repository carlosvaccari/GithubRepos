package com.cvaccari.features.commons.extensions

import android.view.View
import com.cvaccari.features.R
import com.google.android.material.snackbar.Snackbar


fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View?.showFeedback(message: String? = null) {
    this?.apply {
        Snackbar.make(
            this, message ?: this.context.getString(R.string.error_generic)
            , Snackbar.LENGTH_LONG
        ).show()
    }
}