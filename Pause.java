package com.example.gifin.noteapp;

import android.graphics.Canvas;
import android.graphics.Paint;





public class Pause implements Element {

    private NoteLength length;
    private int startTime;
    private int line;

    public Pause(NoteLength length, int startTime)
    {
        this.length = length;
        this.startTime = startTime;
    }

    public Pause(int startTime, int line)
    {
        this.startTime = startTime;
        this.length = new NoteLength();
        this.line = line;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    @Override
    public int getDrawable() {
        switch (length.division) {
            case 0: return R.drawable.wholepause;
            case 1: return R.drawable.halfpause;
            case 2: return R.drawable.quarterpause;
            case 3: return R.drawable.eighthpause;
            case 4: return R.drawable.sixteenthpause;
            default: return R.drawable.brush;
        }
    }

    @Override
    public boolean hasDot() {
        return length.dot;
    }

    @Override
    public void nextDot() {
        length.changeDot();
    }

    @Override
    public int getStartTime() {
        return startTime;
    }

    @Override
    public void nextLength() {
        length.next();
    }
}
