package com.example.gifin.noteapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;




public interface Element {

    int getLine();

    int getStartTime();

    void nextLength();

    void setStartTime(int i);

    int getDrawable();

    boolean hasDot();

    void nextDot();
}
