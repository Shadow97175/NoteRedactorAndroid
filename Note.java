package com.example.gifin.noteapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Note implements Element {
    private int octave;
    private NoteSound noteSound;
    private NoteLength length;
    private int startTime;

    public Note()
    {
        octave = 1;
        noteSound = NoteSound.DO;
        length = new NoteLength();
        startTime = 0;
    }

    public Note(int octave, NoteSound noteSound, NoteLength length, int startTime)
    {
        this.octave = octave;
        this.noteSound = noteSound;
        this.length = length;
        this.startTime = startTime;
    }

    public Note(int startTime, int line)
    {
        this.startTime = startTime;
        this.length = new NoteLength();

        octave = line / 7 - 1;
        noteSound = NoteSound.values()[line % 7];

    }

    public int getLine()
    {
        return noteSound.ordinal() + (octave + 1) * 7;
    }

    @Override
    public int getStartTime() {
        return startTime;
    }

    @Override
    public void nextLength() {
        length.next();
    }

    @Override
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    @Override
    public int getDrawable() {
        switch (length.division) {
            case 0: return R.drawable.wholenote;
            case 1: return R.drawable.halfnote;
            case 2: return R.drawable.quarternote;
            case 3: return R.drawable.eighthnote;
            case 4: return R.drawable.sixteenthnote;
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


    public String toString(){
        return "Octave: " + octave + ", " + noteSound.toString() + ", " + "Length in seconds: " + length.getSeconds();
    }
}
