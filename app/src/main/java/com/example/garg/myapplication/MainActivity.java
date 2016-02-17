package com.example.garg.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity  {
    String colors[] = {"#F44336","#F50057","#D500F9","#651FFF","#536DFE","#2196F3","#00B0FF","#18FFFF","#1DE9B6","#00E676","#AEEA00","#FFEA00","#FF9100","#F4511E"};
    // All these above colors are from google color palette.There are some 500s and some 600s. Total 15 color. and 14 according to array.
    private int count = 0;
    private int count2 = 0;
    public MediaPlayer mp1;
    public MediaPlayer mp2;
    LinearLayout myLayout;
    LinearLayout myLayout2;
    int s1 = 1;
    int s2 = 1;
    int s_inc1 = 0;
    int s_inc2 = 0;
    int sec[] = new int[20];
    int task1 = 0;
    int task2 = 0;
    int task_progress1 = 1;
    int task_progress2 = 1;
    long time_p1;
    long time_p2;
    int x = 0;
    int x11;
    int x12;
    int x21;
    int x22;
    String last_color1;
    String last_color2;
    int last_task1=0;
    int last_task2=0;
    Runnable myRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mp1 = MediaPlayer.create(getApplicationContext(), R.raw.blop);
        this.mp2 = MediaPlayer.create(getApplicationContext(), R.raw.blop);
    }
    public String color_palette(int b)
    {
        int s;
        String color;
        if (b==1) {
            do {
                s = (int) (Math.random() * 14.0D);
                color = colors[s];
            } while (color.equals(last_color1) || color.equals(last_color2));
            last_color1 = color;
        } else
        {
            do {
                s = (int) (Math.random() * 14.0D);
                color = colors[s];
            } while (color.equals(last_color1) || color.equals(last_color2));
            last_color2 = color;
        }
        return color;
    }
    // This following function helps to assign random and different task every time.
    public int tasker(int a) {
        int tasker;
        if(a==1) {
            do {
                tasker = ((int) (Math.random() * 6.0D) + 1);
            } while (tasker == last_task1);
            last_task1 = tasker;
        }
        else
        {
            do {
                tasker = ((int) (Math.random() * 6.0D) + 1);
            } while (tasker == last_task2);
            last_task2 = tasker;
        }
        return tasker;
    }

    @Override
    protected void onStart() {
        super.onStart();
        myLayout = (LinearLayout) findViewById(R.id.LinearLayout1);
        myLayout2 = (LinearLayout) findViewById(R.id.LinearLayout2);
        myRunnable = new Runnable() {
            @Override
            public void run() {
                /* Giving numbers from 3-6 for waiting time for seconds. it puts them into a string */
                for(int i=0;i<20;i++)
                {
                    sec[i] = 3 + (int)(Math.random() * ((6 - 3) + 1));

                }
                //the main while loop
                while (x < 60) {
                    try {
                        Thread.sleep(1000);// Waits for 1 second (1000 milliseconds)
                        Log.d("s1=", "" + s1);
                        if (task_progress1 == 0)
                        {
                            Log.d("task_progress1 = ", "" + task_progress1);
                            if (x == s1)
                            {
                                s1 = sec[s_inc1] + s1;
                                s_inc1++;
                            }
                        }
                        if (task_progress2 == 0)
                        {
                            Log.d("task_progress2 = ", "" + task_progress2);
                            if (x == s2)
                            {
                                s2 = sec[s_inc2] + s2;
                                s_inc2 ++;
                            }
                        }
                        if (task_progress2 == 1)
                        {
                            Log.d("task_progress2 = ", "" + task_progress2);
                            if (x == s2) {

                                task_progress2 = 0;
                                task2 = tasker(2);
                                Log.d("task2/s=" + s2, "" + task2);
                                s2 = sec[s_inc2] + s2;

                                s_inc2 ++;
                                if (task2 == 6) {
                                    task_progress2 = 1;
                                }
                            }
                            else {
                                task2 = 0;
                            }
                        }
                        if (task_progress1 == 1)
                        {
                            Log.d("task_progress1 = ", "" + task_progress1);
                            if (x == s1) {

                                task_progress1 = 0;
                                task1 = tasker(1);
                                Log.d("task1/s=" + s1, "" + task1);
                                s1 = sec[s_inc1] + s1;
                                s_inc1 ++;
                                if (task1 == 6) {
                                    task_progress1 = 1;
                                }
                            }
                            else
                            {
                                task1 = 0;
                            }
                        }
                    /* CAUTION this if statement is a hardcoding thing where its to help me a lot. Thanks to my instant idea. Don't change anything here. */
                        if(x<3)
                        {
                            s1=sec[s_inc1];
                            s2=sec[s_inc2];
                        }
                        x++;
                        myLayout.post(new Runnable() {
                            @Override
                            public void run() {
                            TextView textView1 = (TextView) findViewById(R.id.textView);
                            TextView textView2 = (TextView) findViewById(R.id.textView3);
                            switch(task1) {
                                case 1 :
                                        textView1.setText("Swipe up");

                                        break;
                                case 2 :
                                        textView1.setText("Swipe Right");

                                        break;
                                case 3 :
                                        textView1.setText("Swipe left");

                                        break;
                                case 4 :
                                        textView1.setText("Swipe down");

                                        break;
                                case 5 :
                                        textView1.setText("Long Press");

                                        break;
                                case 6 :
                                        textView1.setText("Don't Tap");

                                        break;
                                case 0 :
                                        textView1.setText("Tap Fast");

                                        break;
                                default :
                            }
                            switch(task2) {
                                case 1 :

                                    textView2.setText("Swipe up");
                                    break;
                                case 2 :

                                    textView2.setText("Swipe Right");
                                    break;
                                case 3 :

                                    textView2.setText("Swipe left");
                                    break;
                                case 4 :

                                    textView2.setText("Swipe down");
                                    break;
                                case 5 :

                                    textView2.setText("Long Press");
                                    break;
                                case 6 :

                                    textView2.setText("Don't Tap");
                                    break;
                                case 0 :

                                    textView2.setText("Tap Fast");
                                    break;
                                default :
                            }
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                };
            };
        };
            Thread myThread = new Thread(myRunnable);
            myThread.start();


            //OnTouchListener for PLAYER 1
            myLayout.setOnTouchListener(new LinearLayout.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent m) {

                    switch(task1) {
                        case 1 :
                            swipe_up1(m);
                            break;
                        case 2 :
                            swipe_right1(m);
                            break;
                        case 3 :
                            swipe_left1(m);
                            break;
                        case 4 :
                            swipe_down1(m);
                            break;
                        case 5 :
                            long_press1(m);
                            break;
                        case 6 :
                            dont_tap1(m);
                            break;
                        case 0 :
                            tap1(m);
                            break;
                        default :

                    }
                    return true;

                }
            }
            );
            //OnTouchListener for PLAYER 2

            myLayout2.setOnTouchListener(new LinearLayout.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent m) {
                    switch(task2) {
                        case 1 :
                            swipe_up2(m);
                            break;
                        case 2 :
                            swipe_right2(m);
                            break;
                        case 3 :
                            swipe_left2(m);
                            break;
                        case 4 :
                            swipe_down2(m);
                            break;
                        case 5 :
                            long_press2(m);
                            break;
                        case 6 :
                            dont_tap2(m);
                            break;
                        case 0 :
                            tap2(m);
                            break;
                        default :

                    }
                    return  true;

                }
            });


    }
        // PLAYER 1 : myLayout :  LinearLayout1 = Functions for the player1.
    void swipe_up1(MotionEvent m)
    {
        task_progress1 = 0;
        int eventAction = m.getAction();
        if(eventAction == MotionEvent.ACTION_DOWN)
        {
            x11 = (int) m.getY();
        }
        if(eventAction == MotionEvent.ACTION_MOVE)
        {
            x21 = (int) m.getY();
            if (x21 - x11 > 250)
            {
                task_progress1 = 1;
                String color = color_palette(1);
                myLayout.setBackgroundColor(Color.parseColor(color));
                task1=0;
                ((TextView)findViewById(R.id.textView)).setText("Tap Fast");
            } else {
                task_progress1 = 0;
            }
        }

    }
    void swipe_right1(MotionEvent m)
    {
        task_progress1 = 0;
        int eventAction = m.getAction();
        if(eventAction == MotionEvent.ACTION_DOWN)
        {
            x11 = (int) m.getX();
        }
        if(eventAction == MotionEvent.ACTION_MOVE)
        {
            x21 = (int) m.getX();
            if (x21 - x11 < -250)
            {
                task_progress1 = 1;
                String color = color_palette(1);
                myLayout.setBackgroundColor(Color.parseColor(color));
                task1=0;
                ((TextView)findViewById(R.id.textView)).setText("Tap Fast");
            } else {
                task_progress1 = 0;
            }
        }

    }
    void swipe_left1(MotionEvent m)
    {
        task_progress1 = 0;
        int eventAction = m.getAction();
        if(eventAction == MotionEvent.ACTION_DOWN)
        {
            x11 = (int) m.getX();
        }
        if(eventAction == MotionEvent.ACTION_MOVE)
        {
            x21 = (int) m.getX();
            if (x21 - x11 > 250)
            {
                task_progress1 = 1;
                String color = color_palette(1);
                myLayout.setBackgroundColor(Color.parseColor(color));
                task1=0;
                ((TextView)findViewById(R.id.textView)).setText("Tap Fast");
            } else {
                task_progress1 = 0;
            }
        }
    }
    void swipe_down1(MotionEvent m)
    {
        task_progress1 = 0;
        int eventAction = m.getAction();
        if(eventAction == MotionEvent.ACTION_DOWN)
        {
            x11 = (int) m.getY();
        }
        if(eventAction == MotionEvent.ACTION_MOVE)
        {
            x21 = (int) m.getY();
            if (x21 - x11 < -250)
            {
                task_progress1 = 1;
                String color = color_palette(1);
                myLayout.setBackgroundColor(Color.parseColor(color));
                task1=0;
                ((TextView)findViewById(R.id.textView)).setText("Tap Fast");
            } else
            {
                task_progress1 = 0;
            }
        }
    }
    void long_press1(MotionEvent m)
    {
        task_progress1 = 0;
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        int eventAction = m.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {
            time_p1 = System.currentTimeMillis();
        }
        if (eventAction == MotionEvent.ACTION_MOVE)
        {
            if (System.currentTimeMillis() - time_p1 >= 900) {
                vibrator.vibrate(80);
                task_progress1 = 1;
                task1 = 0;
                ((TextView)findViewById(R.id.textView)).setText("Tap Fast");
            }
        }
        if(eventAction == MotionEvent.ACTION_UP)
        {
            time_p1 = 0;
        }
    }
    void dont_tap1(MotionEvent m)
    {
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        if (m.getAction() == MotionEvent.ACTION_DOWN)
        {
            count -= 2;
            vibrator.vibrate(100);
            task_progress1 = 1;
            ((TextView)findViewById(R.id.textView1)).setText("" + count);
        }
        task_progress1 = 1;
    }
    void tap1(MotionEvent m)
    {
        int eventAction = m.getAction();
        TextView CountDisplay = (TextView)findViewById(R.id.textView1);
        if (eventAction == MotionEvent.ACTION_DOWN)
        {
            mp1.start();
            count += 1;
            CountDisplay.setText("" + count);
        }
        task_progress1 = 1;


    }

    // PLAYER 2 : myLayout2 :  LinearLayout2 = Functions for the player2.
    void swipe_up2(MotionEvent m)
    {
        task_progress2 = 0;
        int eventAction = m.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {
            x12 = ((int)m.getY());
        }
        if (eventAction == MotionEvent.ACTION_MOVE)
        {
            x22 = ((int)m.getY());
            if (x22 - x12 < -250) {
                task_progress2 = 1;
                String color = color_palette(2);
                myLayout2.setBackgroundColor(Color.parseColor(color));
                task2 = 0;
                ((TextView)findViewById(R.id.textView3)).setText("Tap Fast");
            } else {
                task_progress2 = 0;
            }
        }
    }
    void swipe_right2(MotionEvent m)
    {
        task_progress2 = 0;
        int eventAction = m.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {
            x12 = ((int)m.getX());
        }
        if (eventAction == MotionEvent.ACTION_MOVE)
        {
            x22 = ((int)m.getX());
            if (x22 - x12 > 250) {
                task_progress2 = 1;
                String color = color_palette(2);
                myLayout2.setBackgroundColor(Color.parseColor(color));
                task2 = 0;
                ((TextView)findViewById(R.id.textView3)).setText("Tap Fast");
            } else {
                task_progress2 = 0;
            }
        }
    }
    void swipe_left2(MotionEvent m)
    {
        task_progress2 = 0;
        int eventAction = m.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {
            x12 = ((int)m.getX());
        }
        if (eventAction == MotionEvent.ACTION_MOVE)
        {
            x22 = ((int)m.getX());
            if (x22 - x12 < -250) {
                task_progress2 = 1;
                String color = color_palette(2);
                myLayout2.setBackgroundColor(Color.parseColor(color));
                task2 = 0;
                ((TextView)findViewById(R.id.textView3)).setText("Tap Fast");
            } else {
                task_progress2 = 0;
            }
        }
    }
    void swipe_down2(MotionEvent m)
    {
        task_progress2 = 0;
        int eventAction = m.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {
            x12 = ((int)m.getY());
        }
        if (eventAction == MotionEvent.ACTION_MOVE)
        {
            x22 = ((int)m.getY());
            if (x22 - x12 > 250) {
                task_progress2 = 1;
                String color = color_palette(2);
                myLayout2.setBackgroundColor(Color.parseColor(color));
                task2 = 0;
                ((TextView)findViewById(R.id.textView3)).setText("Tap Fast");
            } else {
                task_progress2 = 0;
            }
        }
    }
    void long_press2(MotionEvent m)
    {
        task_progress2 = 0;
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        int eventAction = m.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {
            time_p2 = System.currentTimeMillis();
        }
        if (eventAction == MotionEvent.ACTION_MOVE)
        {
            if (System.currentTimeMillis() - time_p2 >= 900) {
                task_progress2 = 1;
                vibrator.vibrate(80);
                task2 = 0;
                ((TextView)findViewById(R.id.textView3)).setText("Tap Fast");
            }
        }
        if(eventAction == MotionEvent.ACTION_UP)
        {
            time_p2 = 0;
        }
    }
    void dont_tap2(MotionEvent m)
    {
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        if (m.getAction() == MotionEvent.ACTION_DOWN)
        {
            count2 -= 2;
            vibrator.vibrate(100);
            task_progress2 = 1;
            ((TextView)findViewById(R.id.textView2)).setText("" + count2);
        }
        task_progress2 = 1;
    }
    void tap2(MotionEvent m)
    {
        int eventAction = m.getAction();
        TextView CountDisplay2 = (TextView)findViewById(R.id.textView2);
        if (eventAction == MotionEvent.ACTION_DOWN)
        {
            mp2.start();
            count2 += 1;
            CountDisplay2.setText("" + count2);
        }
        task_progress2 = 1;

    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

}



