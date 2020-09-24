package com.cvaccari.features.commons.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

fun Fragment.goTo(navAction: NavDirections) {
    this.view?.findNavController()?.navigate(navAction)
}

fun Fragment.popUp() {
    this.view?.findNavController()?.popBackStack()
}

fun Fragment.finish() {
    requireActivity().finish()
}