package com.example.sean.whackamole;

import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Timer moleTimer;
    private Timer gameTimer;
    private TimerTask moleTask;
    private TimerTask gameTask;
    private Whack whack;
    public static ImageView mole1;
    public static ImageView mole2;
    public static ImageView mole3;
    public static ImageView mole4;
    public static ImageView mole5;
    public static ImageView mole6;
    public static TextView timeText;
    public static TextView scoreText;
    public static Button pause;
    public static Button newGame;
    public static TextView endMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Whack-A-Mole");
        moleTimer = new Timer();
        gameTimer = new Timer();
        whack = new Whack(this);
        moleTask = new TimerTask() {
            @Override
            public void run() {
                whack.spawnMoles();

            }
        };
        gameTask = new TimerTask(){
            @Override
            public void run() {
                whack.setTimer();
            }
        };
        moleTimer.schedule(moleTask,0, 1500);
        gameTimer.schedule(gameTask, 0, 1000);
        timeText = (TextView)findViewById(R.id.txtTime);
        scoreText = (TextView)findViewById(R.id.txtScore);
        endMessage = (TextView)findViewById(R.id.lblGameOver);
        timeText.setKeyListener(null);
        scoreText.setKeyListener(null);
        mole1 = (ImageView)findViewById(R.id.btnMole1);
        mole2 = (ImageView)findViewById(R.id.btnMole2);
        mole3 = (ImageView)findViewById(R.id.btnMole3);
        mole4 = (ImageView)findViewById(R.id.btnMole4);
        mole5 = (ImageView)findViewById(R.id.btnMole5);
        mole6 = (ImageView)findViewById(R.id.btnMole6);
        pause = (Button)findViewById(R.id.btnPause);
        newGame = (Button)findViewById(R.id.btnNew);
        mole1.setOnClickListener(this);
        mole2.setOnClickListener(this);
        mole3.setOnClickListener(this);
        mole4.setOnClickListener(this);
        mole5.setOnClickListener(this);
        mole6.setOnClickListener(this);
        pause.setOnClickListener(this);
        newGame.setOnClickListener(this);
    }

    public void onClick(View view){
        if(whack.paused==false && whack.endGame==false) {
            if (mole1 == view && whack.moleSpawn1 == 1) {
                whack.moleSpawn1 = 0;
                mole1.setImageResource(R.drawable.mole_hole);
                whack.addScore();
            } else if (mole2 == view && whack.moleSpawn2 == 1) {
                whack.moleSpawn2 = 0;
                mole2.setImageResource(R.drawable.mole_hole);
                whack.addScore();
            } else if (mole3 == view && whack.moleSpawn3 == 1) {
                whack.moleSpawn3 = 0;
                mole3.setImageResource(R.drawable.mole_hole);
                whack.addScore();
            } else if (mole4 == view && whack.moleSpawn4 == 1) {
                whack.moleSpawn4 = 0;
                mole4.setImageResource(R.drawable.mole_hole);
                whack.addScore();
            } else if (mole5 == view && whack.moleSpawn5 == 1) {
                whack.moleSpawn5 = 0;
                mole5.setImageResource(R.drawable.mole_hole);
                whack.addScore();
            } else if (mole6 == view && whack.moleSpawn6 == 1) {
                whack.moleSpawn6 = 0;
                mole6.setImageResource(R.drawable.mole_hole);
                whack.addScore();
            }
        }

        if(pause==view){
            whack.setPaused();
        }
        if(newGame==view){
            whack.newGame();
        }

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
