package com.danielkim.soundrecorder.fragments;

import android.content.Intent;
import android.test.AndroidTestCase;
import android.text.format.DateUtils;

import com.danielkim.soundrecorder.R;
import com.danielkim.soundrecorder.activities.MainActivity;

import org.junit.Rule;

import java.util.concurrent.TimeUnit;

import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class RecordFragmentChronometerTest extends AndroidTestCase {
    @Rule
    private final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, true, false);

    private static IdlingResource chronometer;
    private static long recordingTime;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activityTestRule.launchActivity(new Intent(getContext(), MainActivity.class));
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        activityTestRule.finishActivity();
    }

    public void testRecordFor5Seconds() {
        recordingTime = DateUtils.SECOND_IN_MILLIS * 5;
        recordUntil("00:05");
    }

    public void testRecordFor65Seconds() {
        recordingTime = DateUtils.SECOND_IN_MILLIS * 65;
        recordUntil("01:05");
    }

    private static void recordUntil(String displayedTime) {
        setTimeout();

        onView(withId(R.id.btnRecord)).perform(click());
        startRecording();

        onView(withId(R.id.chronometer)).check(matches(withText(displayedTime)));
        stopRecording();

        onView(withId(R.id.btnRecord)).perform(click());
    }

    private static void setTimeout() {
        IdlingPolicies.setMasterPolicyTimeout(recordingTime * 2, TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(recordingTime * 2, TimeUnit.MILLISECONDS);
    }

    private static void startRecording() {
        chronometer = new ChronometerIdlingResource(recordingTime);
        IdlingRegistry.getInstance().register(chronometer);
    }

    private static void stopRecording() {
        IdlingRegistry.getInstance().unregister(chronometer);

    }
}