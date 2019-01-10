package com.danielkim.soundrecorder.util;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

public class ResourceUtils {
    public static String getString(int id) {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        return targetContext.getResources().getString(id);

    }
}
