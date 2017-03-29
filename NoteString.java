package com.example.gifin.noteapp;

import java.util.ArrayList;
import java.util.List;




public class NoteString {
    private List<Element> notes;

    public NoteString(){
        notes = new ArrayList<>();
    }

    public int count()
    {
        return notes.size();
    }

    public void addNote(Element note){
        notes.add(note);
    }

    public void addNote(int i, Element note){
        if(i < notes.size())
            notes.add(i, note);
        else
            notes.add(note);
    }

    public void removeNote(int i){
        if(i >= 0 && i < notes.size())
            notes.remove(i);
    }

    public int size(){
        return 21;
    }

    public Element getNote(int i)
    {
        return notes.get(i);
    }

    public int findNote(int startTime, int line) {
        for(int i = 0; i < notes.size(); i++){
            if (notes.get(i).getStartTime() == startTime && notes.get(i).getLine() == line)
                return i;
        }
        return -1;
    }
}
