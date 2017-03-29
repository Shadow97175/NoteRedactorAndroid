package com.example.gifin.noteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private DrawView drawView;
    private ImageButton currPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView = (DrawView)findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);
        currPaint = (ImageButton)paintLayout.getChildAt(0);
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
    }

    public void normalPaint(View view){
        drawView.setTool(false);
        drawView.setDotTool(false);
        drawView.setPauseTool(false);
    }

    public void deletePaint(View view){
        drawView.setTool(true);
    }


    public void dotPaint(View view)
    {
        drawView.setDotTool(true);
    }

    public void pausePaint(View view)
    {
        drawView.setPauseTool(true);
    }
}
