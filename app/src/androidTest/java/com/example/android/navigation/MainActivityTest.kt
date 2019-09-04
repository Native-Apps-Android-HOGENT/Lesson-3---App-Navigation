package com.example.android.navigation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isOpen
import androidx.test.espresso.contrib.NavigationViewActions
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

    @Test
    fun onHamburgerClicked_openDrawer() {
        onView(withId(R.id.drawerLayout)).perform(DrawerActions.open())
        onView(withId(R.id.drawerLayout)).check(matches(isOpen()))
    }

    @Test
    fun navigationMenu_rulesClicked_openRulesFragment() {
        onView(withId(R.id.drawerLayout)).perform(DrawerActions.open())
        onView(withId(R.id.navView)).perform(NavigationViewActions.navigateTo(R.id.nav_fragment_rules))
        onView(withId(R.id.scrollView_rules)).check(matches(isDisplayed()))
    }

    @Test
    fun navigationMenu_aboutClicked_openAboutFragment() {
        onView(withId(R.id.drawerLayout)).perform(DrawerActions.open())
        onView(withId(R.id.navView)).perform(NavigationViewActions.navigateTo(R.id.nav_fragment_about))
        onView(withId(R.id.constraint_about)).check(matches(isDisplayed()))
    }

    @Test
    fun gameScreen_upButtonClicked_backToTitle() {
        onView(withId(R.id.playButton)).perform(click())
        // There is no predefined Action to press the Up button.
        // Using the description is the best way, although being a bit hacky.
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        onView(withId(R.id.constraint_title)).check(matches(isDisplayed()))
    }
}


