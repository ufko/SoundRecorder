package com.danielkim.soundrecorder.fragments;

import android.content.Intent;

import com.danielkim.soundrecorder.R;
import com.danielkim.soundrecorder.activities.SettingsActivity;
import com.danielkim.soundrecorder.util.ResourceUtils;

import junit.framework.TestCase;

import org.junit.Rule;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.PreferenceMatchers.withKey;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class LicensesFragmentTest extends TestCase {

    @Rule
    private final ActivityTestRule<SettingsActivity> activityTestRule = new ActivityTestRule<>(SettingsActivity.class, true, false);

    @Override
    public void setUp() {
        activityTestRule.launchActivity(new Intent());
    }

    @Override
    public void tearDown() {
        activityTestRule.finishActivity();
    }

    public void testLicencesDisplayedWhenAboutTapped() {
        onData(withKey(ResourceUtils.getString(R.string.pref_about_key))).perform(click());

        onView(withText(R.string.license_title_fab)).check(matches(isDisplayed()));
        onView(withText(R.string.license_author_fab)).check(matches(isDisplayed()));
        onView(withText(R.string.license_content_fab)).check(matches(isDisplayed()));
        onView(withText(R.string.license_title_tabs)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withText(R.string.license_author_tabs)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withText(R.string.license_content_tabs)).perform(scrollTo()).check(matches(isDisplayed()));
    }
}