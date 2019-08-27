package com.example.android.navigation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun onStartUp_showCorrectFragment() {
        onView(withId(R.id.constraint_title)).check(matches(isDisplayed()))
    }

    @Test
    fun onClickPlay_goToGameFragment() {
        onView(withId(R.id.playButton)).perform(click())
        onView(withId(R.id.scrollview_game)).check(matches(isDisplayed()))
    }

    @Test
    fun onAboutMenuClicked_goToAboutFragment() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText("About")).perform(click())
        onView(withId(R.id.constraint_about)).check(matches(isDisplayed()))
    }
}


