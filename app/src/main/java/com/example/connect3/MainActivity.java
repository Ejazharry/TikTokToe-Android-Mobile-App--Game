package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //o-yellow 1-red
    int [] gameState={2,2,2,2,2,2,2,2,2,};
    int [][] winningPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6,},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int activePlayer = 0;
    boolean gameActive = true;
    public void dropin(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                    gameActive=false;
                    String Winner;
                    if (activePlayer == 1) {
                        Winner = "Yellow";
                    } else {
                        Winner = "Red";
                    }

                    Button playagainbutton =(Button)findViewById((R.id.playagainbutton));
                    TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);
                    winnerTextView.setText(Winner + " has won");
                    playagainbutton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
        public void playAgain(View view){
        Button playagainbutton =(Button)findViewById((R.id.playagainbutton));
        TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);
        playagainbutton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        Arrays.fill(gameState, 2);
        activePlayer=0;
        gameActive=true;



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
