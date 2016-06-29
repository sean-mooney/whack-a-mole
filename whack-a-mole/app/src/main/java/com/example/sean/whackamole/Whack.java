package com.example.sean.whackamole;


import android.app.Activity;
import android.view.View;

import java.util.Random;


/**
 * Created by Sean on 12/15/2015.
 */
public class Whack extends Activity{
    private int score = 0;
    private int timer = 0;
    public int moleSpawn1 = 0;
    public int moleSpawn2= 0;
    public int moleSpawn3 = 0;
    public int moleSpawn4 = 0;
    public int moleSpawn5 = 0;
    public int moleSpawn6 = 0;
    public boolean paused = false;
    public boolean endGame = false;
    private MainActivity main;


    public Whack(MainActivity main) {
        this.main = main;
    }

    protected void spawnMoles(){
        if (!paused && !endGame) {
            Random r = new Random();
            moleSpawn1 = r.nextInt(3);
            moleSpawn2 = r.nextInt(3);
            moleSpawn3 = r.nextInt(3);
            moleSpawn4 = r.nextInt(3);
            moleSpawn5 = r.nextInt(3);
            moleSpawn6 = r.nextInt(3);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    main.mole1.setImageResource(R.drawable.mole_hole);
                    main.mole2.setImageResource(R.drawable.mole_hole);
                    main.mole3.setImageResource(R.drawable.mole_hole);
                    main.mole4.setImageResource(R.drawable.mole_hole);
                    main.mole5.setImageResource(R.drawable.mole_hole);
                    main.mole6.setImageResource(R.drawable.mole_hole);

                    if (moleSpawn1 == 1) {
                        main.mole1.setImageResource(R.drawable.mole_popup);
                    }
                    if (moleSpawn2 == 1) {
                        main.mole2.setImageResource(R.drawable.mole_popup);
                    }
                    if (moleSpawn3 == 1) {
                        main.mole3.setImageResource(R.drawable.mole_popup);
                    }
                    if (moleSpawn4 == 1) {
                        main.mole4.setImageResource(R.drawable.mole_popup);
                    }
                    if (moleSpawn5 == 1) {
                        main.mole5.setImageResource(R.drawable.mole_popup);
                    }
                    if (moleSpawn6 == 1) {
                        main.mole6.setImageResource(R.drawable.mole_popup);
                    }

                }
            });
        }
    }

    public void setPaused(){
        if (paused){
            paused = false;
            System.out.println("resumed and paused = "+paused);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    main.pause.setText("Pause");
                }
            });
        } else if (!paused) {
            paused = true;
            System.out.println("paused and pause = " + paused);
            runOnUiThread (new Runnable() {
                @Override
                public void run() {
                    main.mole1.setImageResource(R.drawable.mole_hole);
                    main.mole2.setImageResource(R.drawable.mole_hole);
                    main.mole3.setImageResource(R.drawable.mole_hole);
                    main.mole4.setImageResource(R.drawable.mole_hole);
                    main.mole5.setImageResource(R.drawable.mole_hole);
                    main.mole6.setImageResource(R.drawable.mole_hole);
                    moleSpawn1 = 0;
                    moleSpawn2 = 0;
                    moleSpawn3 = 0;
                    moleSpawn4 = 0;
                    moleSpawn5 = 0;
                    moleSpawn6 = 0;
                    main.pause.setText("Resume");
                }
            });

        }

    }

    public void setTimer(){
        if (timer<30 && !paused) {
            timer = timer+1;
            runOnUiThread (new Runnable() {
                @Override
                public void run() {
                    main.timeText.setText("Time: " + timer);
                }
            });

        }

        if (timer==30){
            endGame=true;

            runOnUiThread (new Runnable() {
                @Override
                public void run() {
                    main.endMessage.setVisibility(View.VISIBLE);
                    main.mole1.setImageResource(R.drawable.mole_hole);
                    main.mole2.setImageResource(R.drawable.mole_hole);
                    main.mole3.setImageResource(R.drawable.mole_hole);
                    main.mole4.setImageResource(R.drawable.mole_hole);
                    main.mole5.setImageResource(R.drawable.mole_hole);
                    main.mole6.setImageResource(R.drawable.mole_hole);
                    moleSpawn1 = 0;
                    moleSpawn2 = 0;
                    moleSpawn3 = 0;
                    moleSpawn4 = 0;
                    moleSpawn5 = 0;
                    moleSpawn6 = 0;
                }
            });
        }
    }

    public void addScore() {
        if (!paused && !endGame) {
            score += 1;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    main.scoreText.setText("Score: " + score);
                }
            });

        }

    }

    public void newGame(){
        paused = false;
        score = 0;
        timer = 0;
        endGame = false;
        runOnUiThread (new Runnable() {
            @Override
            public void run() {
                main.mole1.setImageResource(R.drawable.mole_hole);
                main.mole2.setImageResource(R.drawable.mole_hole);
                main.mole3.setImageResource(R.drawable.mole_hole);
                main.mole4.setImageResource(R.drawable.mole_hole);
                main.mole5.setImageResource(R.drawable.mole_hole);
                main.mole6.setImageResource(R.drawable.mole_hole);
                moleSpawn1 = 0;
                moleSpawn2 = 0;
                moleSpawn3 = 0;
                moleSpawn4 = 0;
                moleSpawn5 = 0;
                moleSpawn6 = 0;
                main.scoreText.setText("Score: 0");
                main.timeText.setText("Time: 0");
                main.endMessage.setVisibility(View.INVISIBLE);
                main.pause.setText("Pause");
            }
        });
    }
}
