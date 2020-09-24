package com.cvaccari.features.commons.extensions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.cvaccari.features.R

fun AppCompatActivity.goTo(navItemId: Int, bundle: Bundle? = Bundle()) {
    Navigation.findNavController(this, R.id.fragment_host).navigate(navItemId, bundle)
}

fun AppCompatActivity.shouldCloseActivity(): Boolean {
    return (supportFragmentManager.fragments[0] as NavHostFragment).childFragmentManager.backStackEntryCount == 1
}