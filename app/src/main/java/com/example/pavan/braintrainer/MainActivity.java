package com.example.pavan.braintrainer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startbutton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button resetButton;
    TextView resultTest;
    TextView points;
    TextView sumtextView;
    TextView timerTextview;
    TextSwitcher text;
    FrameLayout frameLayout;
    //animate text

    //anser in arraylist
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationCorrectanser;
    //score and fails
    int score=0;
    int nunberofquestion =0;


    public void resetvalue(View view)
    {
        score=0;
        nunberofquestion=0;

        timerTextview.setText("30s");
        points.setText("0 / 0");
        resultTest.setText("");
        resetButton.setVisibility(view.INVISIBLE);
        generateQuestion();
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);

        button3.setEnabled(true);

        //timer
        CountDownTimer count  = new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secondsLeft = 0;
                if (Math.round((float)millisUntilFinished / 1000.0f) != secondsLeft)
                {
                    secondsLeft = Math.round((float)millisUntilFinished / 1000.0f);
                    timerTextview.setText(String.valueOf(millisUntilFinished/1000)+"s");
                }

            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"Thank You",Toast.LENGTH_LONG).show();
                //boolean s = false;
                timerTextview.setText("0s");
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);

                button3.setEnabled(false);


                resultTest.setText(" YOUR SCORE IS"+score);
                resetButton.setVisibility(View.VISIBLE);

            }
        }.start();




    }


    public void generateQuestion()
    {
        //two number to be added
        Random rand = new Random();

            int a = rand.nextInt(21);
            int b = rand.nextInt(21);

            //dispaly the test view
            sumtextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

            //location of correctansyer
            locationCorrectanser = rand.nextInt(4);
            int incorrectanswer = 0;

            //reset array list to make it for 4
            answer.clear();

            //assing correct and incorrect answer
            for (int i = 0; i < 4; i++) {
                if (i == locationCorrectanser) {
                    answer.add(a + b);
                } else {
                    incorrectanswer = rand.nextInt(41);
                    while (incorrectanswer == a + b) {


                        incorrectanswer = rand.nextInt(41);
                    }
                    answer.add(incorrectanswer);
                }

            }



        //difininng the value for buttons
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));

    }


    public void Cliked(View view)
    {
        Log.i("tag",(String)view.getTag());
        if(view.getTag().toString().equals
                (Integer.toString(locationCorrectanser)))
        {
            Log.i("tag","correct");
            score++;

            resultTest.setText("Correct");


        }
        else
        {
            Log.i("tag","false");
            resultTest.setText("In Correct");


        }
         nunberofquestion++;
        points.setText(Integer.toString(score)
                +" / "+Integer.toString(nunberofquestion));
        generateQuestion();
       //System.out.print("tag"+(view.getTag()).toString());
    }


    public void run(View view)
    {
        //to start timer at click of the run method of start button
        resetvalue((Button)findViewById(R.id.resetButton));

        startbutton.setVisibility(view.INVISIBLE);
        frameLayout.setVisibility(View.VISIBLE);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_main);

        startbutton =(Button)findViewById(R.id.StartButton) ;
        //access text view of add,result,time
         sumtextView = (TextView)findViewById(R.id.sumTextView);
       resultTest= (TextView)findViewById(R.id.resultTextview);
        points=(TextView)findViewById(R.id.pointertextView2);
        timerTextview=(TextView)findViewById(R.id.timertextView);
        frameLayout=(FrameLayout) findViewById(R.id.Frameid);

        //4buttons
         button0= (Button)findViewById(R.id.button0);
         button1= (Button)findViewById(R.id.button1);
         button2= (Button)findViewById(R.id.button2);
         button3= (Button)findViewById(R.id.button3);
         resetButton=(Button)findViewById(R.id.resetButton);

//        Intent i = new Intent(this,SimpleActivity.class);
//        startActivity(i);
//
//
//
//
//
//
//
//
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
