package com.danielkim.soundrecorder.fragments;

import androidx.test.espresso.IdlingResource;

public class ChronometerIdlingResource implements IdlingResource {
    private volatile ResourceCallback resourceCallback;
    private final long startTime;
    private final long recordingTime;

    ChronometerIdlingResource(long recordingTime) {
        this.startTime = System.currentTimeMillis();
        this.recordingTime = recordingTime;
    }

    @Override
    public String getName() {
        return ChronometerIdlingResource.class.getName() + ":" + recordingTime;
    }

    @Override
    public boolean isIdleNow() {
        long elapsed = System.currentTimeMillis() - startTime;
        boolean isIdle = (elapsed >= recordingTime);
        if (isIdle) resourceCallback.onTransitionToIdle();
        return isIdle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.resourceCallback = callback;
    }
}