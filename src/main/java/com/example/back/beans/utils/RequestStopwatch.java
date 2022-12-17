package com.example.back.beans.utils;

import jakarta.ejb.Stateless;

@Stateless
public class RequestStopwatch {
    private long executionStartTime;
//    private Runnable stopwatch;
    // todo можно сделать секундомер в новом потоке

    public void end() {}

    public RequestStopwatch() {
        executionStartTime = System.currentTimeMillis();
    }
}
