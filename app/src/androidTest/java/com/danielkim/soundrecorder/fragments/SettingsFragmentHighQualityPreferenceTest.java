package com.danielkim.soundrecorder.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.test.AndroidTestCase;

import com.danielkim.soundrecorder.R;
import com.danielkim.soundrecorder.activities.SettingsActivity;
import com.danielkim.soundrecorder.util.ResourceUtils;

import org.junit.Rule;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.PreferenceMatchers.withKey;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static org.hamcrest.CoreMatchers.not;

public class SettingsFragmentHighQualityPreferenceTest extends AndroidTestCase {
    @Rule
    private final ActivityTestRule<SettingsActivity> activityTestRule = new ActivityTestRule<>(SettingsActivity.class, true, false);

    private SharedPreferences defaultSharedPreferences;

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        defaultSharedPreferences.edit().clear().commit();
        activityTestRule.finishActivity();
    }

    public void testEnableHighQuality() {
        setHighQualityPreference(false);

        activityTestRule.launchActivity(new Intent(getContext(), SettingsActivity.class));

        onData(withKey(ResourceUtils.getString(R.string.pref_high_quality_key))).perform(click());
        onView(withResourceName("checkbox")).check(matches(isChecked()));
    }

    public void testDisableHighQuality() {
        setHighQualityPreference(true);

        activityTestRule.launchActivity(new Intent(getContext(), SettingsActivity.class));

        onData(withKey(ResourceUtils.getString(R.string.pref_high_quality_key))).perform(click());
        onView(withResourceName("checkbox")).check(matches(not(isChecked())));
    }

    private void setHighQualityPreference(boolean preference) {
        defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        defaultSharedPreferences.edit().putBoolean("pref_high_quality", preference).commit();
    }
}