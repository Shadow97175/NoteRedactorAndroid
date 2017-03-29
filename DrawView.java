package com.example.gifin.noteapp;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;




public class DrawView extends View
{

    private final static int noteWidth = 30;
    private final static int noteHeight = 30;

    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;

    private int currNoteLength = 1;
    private boolean delete;

    private Paint paint;

    private NoteString noteString;
    private boolean addDot;
    private boolean pauseTool;

    public DrawView(Context context, AttributeSet attrs){
        super(context, attrs);
        setupDrawing();
        noteString = new NoteString();
    }

    private void setupDrawing(){
//get drawing area setup for interaction
        paint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//view given size
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    void drawElement(Canvas canvas, float x, float y, Paint paint, Context context, Element a)
    {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), a.getDrawable());
        canvas.drawBitmap(bitmap, x, y, paint);
        if (a.hasDot())
        {
            canvas.drawCircle(x + noteWidth, y, 3f, paint);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {

        int lines = noteString.size();
        int width = getWidth();
        int height = getHeight();

        for(int j = 0; j < lines; j++)
        {
            int y = (int) (j * (height * 1.0 / lines));
            if (j > 8 && j % 2 == 1 && j < 20)
                canvas.drawLine(0, y, width, y, paint);
        }

        for(int i = 0; i < noteString.count(); ++i)
        {
            Element element = noteString.getNote(i);
            Drawable d = getResources().getDrawable(R.drawable.paint);
            assert d != null;

            int line = 0;
            if(i == 0 || element instanceof Note) {
                line = element.getLine();
            }
            else
            {
                if (line % 2 == 0) {
                    --line;
                }
            }

            float x = element.getStartTime() * noteWidth;
            float y = (float) (element.getLine() * (height * 1.0 / lines));

            if (line % 2 == 1 && (line <= 8 || line >= 20))
                canvas.drawLine(x, y, x + noteWidth, y, paint);

            drawElement(canvas, x, y - noteHeight / 2, paint, getContext(), element);
        }
    }

    private int calcStartTime(float x)
    {
        return (int) (x / noteWidth);
    }

    private int calcLine(float y, int lines)
    {
        return  (int) (y * lines * 1.0 / getHeight());
    }


    private int checkNote(float x, float y)
    {
        int startTime = calcStartTime(x);
        return noteString.findNote(startTime, calcLine(y, noteString.size()));
    }


    private void createNote(float x, float y, boolean pauseTool)
    {
        int startTime = calcStartTime(x);
        int line = calcLine(y, noteString.size());

        //System.err.println("Creating note: ");
        //System.err.println("Start time: " + startTime);
        //System.err.println("Line: " + line);

        Element note = pauseTool ? new Pause(startTime, line) : new Note(startTime, line);
        noteString.addNote(note);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int i = checkNote(touchX,touchY);
                if (i == -1 && !delete)
                {
                    createNote(touchX, touchY, pauseTool);
                }
                else
                {
                    if (delete)
                        noteString.removeNote(i);
                    else
                    {
                        if (addDot)
                            noteString.getNote(i).nextDot();
                        else
                            noteString.getNote(i).nextLength();
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }


    public void setColor(String newColor){
//set color
        invalidate();
        currNoteLength = Integer.parseInt(newColor);
    }

    public void setTool(boolean b) {
        delete = b;
    }

    public void setDotTool(boolean b) {
        addDot = b;
    }

    public void setPauseTool(boolean pauseTool) {
        this.pauseTool = pauseTool;
    }
}
