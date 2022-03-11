package com.example.newsapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.newsapp.ui.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Let the app load
        Thread.sleep(4_000)
        // perform a click on an article
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview)).perform(ViewActions.click())
        // check if we are on the lats page
        Espresso.onView(ViewMatchers.withId(R.id.url)).check(matches(withText("Full article here")))
    }
}