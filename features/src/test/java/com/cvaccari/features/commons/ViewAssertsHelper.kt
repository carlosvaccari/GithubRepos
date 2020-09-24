package com.cvaccari.features.commons

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers

class ViewAssertsHelper {

    fun Int.isDisplayed(): Int {
        onView(ViewMatchers.withId(this))
            .check(matches(ViewMatchers.isDisplayed()))
        return this
    }

    fun String.isDisplayed() = apply {
        onView(withText(this))
            .check(matches(ViewMatchers.isDisplayed()))
    }
}

fun viewAsserts(asserts: ViewAssertsHelper.() -> Unit) {
    asserts(ViewAssertsHelper())
}
