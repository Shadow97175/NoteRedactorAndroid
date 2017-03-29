package com.example.gifin.noteapp;




public class NoteLength {
    public int division;
    public boolean dot;

    public NoteLength(int division, boolean dot){
        this.division = division;
        this.dot = dot;
    }

    public NoteLength(){
        division = 0;
        dot = false;
    }

    double getSeconds(){
        return (dot ? 1.5 : 1) * Math.pow(2,-division);
    }

    public void next() {
        ++division;
        if (division > 4)
            division = 0;
    }

    public void changeDot() {
        dot = !dot;
    }
}
