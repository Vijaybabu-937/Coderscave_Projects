package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button[] buttons = new Button[9];
    private Button reset, playagain;
    boolean playerOneActive;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    int rounds;
    private int playerOneScoreCount, playerTwoScoreCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneScore = findViewById(R.id.score_player1);
        playerTwoScore = findViewById(R.id.score_player2);
        playerStatus = findViewById(R.id.textStatus);
        reset = findViewById(R.id.reset);
        playagain = findViewById(R.id.play_again);

        buttons[0] = findViewById(R.id.btn_1);
        buttons[1] = findViewById(R.id.btn_2);
        buttons[2] = findViewById(R.id.btn_3);
        buttons[3] = findViewById(R.id.btn_4);
        buttons[4] = findViewById(R.id.btn_5);
        buttons[5] = findViewById(R.id.btn_6);
        buttons[6] = findViewById(R.id.btn_7);
        buttons[7] = findViewById(R.id.btn_8);
        buttons[8] = findViewById(R.id.btn_9);

        for(int i=0; i<buttons.length; i++){
            buttons[i].setOnClickListener(this);
        }

        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        playerOneActive = true;
        rounds =0;



    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        } else if (CheckWinner()) {
            return;
        }
        String buttonID = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length() - 1, buttonID.length())) - 1;

        if (playerOneActive) {
            ((Button) view).setText("X");
            ((Button) view).setTextColor((Color.parseColor("#ffc34a")));
            gameState[gameStatePointer] = 0;
        } else {
            ((Button) view).setText("0");
            ((Button) view).setTextColor((Color.parseColor("#70fc3a")));
            gameState[gameStatePointer] = 1;
        }
        rounds++;

        if(CheckWinner())
        {
            if(playerOneActive){
                playerOneScoreCount++;
                updatePlayerScore();
                playerStatus.setText("Player-1 has WON");
            }
            else{
                playerTwoScoreCount++;
                updatePlayerScore();
                playerStatus.setText("Player-2 has WON");
            }
        } else if (rounds==9)
        {
            playerStatus.setText("No Winner");
        }
        else{
            playerOneActive = !playerOneActive;
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                updatePlayerScore();
            }
        });

        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });


    }

    private boolean CheckWinner() {
        boolean winnerResults = false;
        for (int[] position : winningPositions) {
            if (gameState[position[0]] == gameState[position[1]] &&
                    gameState[position[1]] == gameState[position[2]] &&
                    gameState[position[0]] != 2) {
                winnerResults = true;
            }
        }
        return winnerResults;
    }


    private void playAgain() {
        rounds = 0;
        playerOneActive = true;
        for(int i=0; i< buttons.length; i++){
            gameState[i] = 2;
            buttons[i].setText("");
        }
        playerStatus.setText("Status");
    }

    private void updatePlayerScore() {
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));

    }

}