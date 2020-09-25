package com.cvaccari.features.commons.extensions

import android.text.Spanned
import android.widget.TextView
import com.cvaccari.features.R

fun TextView.setSafeText(text: Spanned) {
   this.text =  when (text.isEmpty()) {
        true -> this.context.getString(R.string.no_information)
        false -> text
    }
}