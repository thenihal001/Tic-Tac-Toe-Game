package com.nihal.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    // 0 = circle , 1 = cross

int activePlayer = 0;

// 2 = not played yet

int[] gameState = {2,2,2,2,2,2,2,2,2};

boolean gameIsActive = true;

int[][] winningState = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    String message = "cross";

    public void dropIn(View View){

        ImageView counter = (ImageView)View;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.circle);

                counter.animate().alpha(1f).rotationXBy(360f).setDuration(800);

                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.cross);

                counter.animate().alpha(1f).rotation(360f).setDuration(800);

                activePlayer = 0;

            }
        }

        for( int[] winningState : winningState){

            if(gameState[winningState[0]] == gameState[winningState[1]] &&
                    gameState[winningState[1]] == gameState[winningState[2]] &&
                    gameState[winningState[0]] != 2) {

                // someone has won
                gameIsActive = false;

                if (gameState[winningState[0]] == 0) {

                    message = " circle ";
                }

                TextView winnerEditText = (TextView) findViewById(R.id.winnerEditText);

                winnerEditText.setText(message + " has won !! ");

                LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);

                playAgainLayout.setVisibility(View.VISIBLE);
            }

            else{

                boolean gameIsOver = true;

                for(int counterState : gameState){

                    if(counterState == 2){

                        gameIsOver = false;
                    }
                }
                 if(gameIsOver){

                     TextView winnerEditText = (TextView) findViewById(R.id.winnerEditText);

                     winnerEditText.setText("It's a draw !!");

                     LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);

                     playAgainLayout.setVisibility(View.VISIBLE);
                 }
            }
        }
    }

    public void playAgain(View view){

        gameIsActive = true;

         activePlayer = 0;

         LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);

         playAgainLayout.setVisibility(view.INVISIBLE);

         for(int i = 0; i<gameState.length;i++){

             gameState[i] = 2;
         }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

         for(int i =0; i<gridLayout.getChildCount(); i++){

             ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
         }

        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
