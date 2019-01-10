package com.danielkim.soundrecorder.fragments;

import android.content.Context;
import android.content.Intent;
import android.test.AndroidTestCase;

import com.danielkim.soundrecorder.R;
import com.danielkim.soundrecorder.activities.MainActivity;

import org.junit.Rule;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

public class RecordFragmentNotificationTest extends AndroidTestCase {
    @Rule
    private final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, true, false);

    private MainActivity activity;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activity = activityTestRule.launchActivity(new Intent(getContext(), MainActivity.class));
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        activityTestRule.finishActivity();
    }

    public void testStartNotificationDisplayedWhenRecordButtonTapped() {
        onView(withId(R.id.btnRecord)).perform(click());
        onView(withText(R.string.toast_recording_start)).inRoot(withDecorView(not(activity.getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    public void testFinishNotificationDisplayedWhenRecordButtonTappedTwice() {
        onView(withId(R.id.btnRecord)).perform(click());
        onView(withId(R.id.btnRecord)).perform(click());
        onView(withText(containsString(getString(R.string.toast_recording_finish)))).inRoot(withDecorView(not(activity.getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    private String getString(int id) {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        return targetContext.getResources().getString(id);

    }
}