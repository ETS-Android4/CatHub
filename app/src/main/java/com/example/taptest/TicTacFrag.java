package com.example.taptest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TicTacFrag extends Fragment implements View.OnClickListener {

    View v;

    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button[] buttons = new Button[9];
    private Button resetGame;

    private int playerOneScoreCount, playerTwoScoreCount, roundCount;
    boolean activePlayer;

    //p1 => 0
    //p2 => 1
    // empty => 2

    int [] gamestate = {2,2,2,2,2,2,2,2,2};
    int [][] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8}, // rows
            {0,3,6}, {1,4,7}, {2,5,8}, // columns
            {0,4,8}, {2,4,6} // cross
    };

    public TicTacFrag() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tictac, container, false);

        playerOneScore = (TextView) v.findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) v.findViewById(R.id.playerTwoScore);
        playerStatus = (TextView) v.findViewById(R.id.playerStatus);

        resetGame = (Button) v.findViewById(R.id.resetGame);

        for (int i = 0; i < buttons.length; i++) {
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getActivity().getPackageName());
            buttons[i] = (Button) v.findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }

        roundCount = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activePlayer = true;

        return v;
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId()); // btn_2
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length() - 1, buttonID.length())); //2

        if (activePlayer) {
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.parseColor("#FF0202"));
            gamestate[gameStatePointer] = 0;
        } else {
            ((Button) v).setText("O");
            ((Button) v).setTextColor(Color.parseColor("#FFFC02"));
            gamestate[gameStatePointer] = 1;
        }
        roundCount++;

        if (checkWinner()) {
            if (activePlayer) {
                playerOneScoreCount++;
                updatePlayerScore();
                Toast.makeText(getContext(), "Player One Win!", Toast.LENGTH_SHORT).show();
                playAgain();
            } else {
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(getContext(), "Player Two Win!", Toast.LENGTH_SHORT).show();
                playAgain();
            }
        } else if (roundCount == 9) {
            playAgain();
            Toast.makeText(getContext(), "Player Two Win!", Toast.LENGTH_SHORT).show();
        } else {
            activePlayer = !activePlayer;
        }

        if (playerOneScoreCount > playerTwoScoreCount) {
            playerStatus.setText("Player One is Winning!");
        } else if (playerOneScoreCount < playerTwoScoreCount) {
            playerStatus.setText("Player Two is Winning!");
        } else {
            playerStatus.setText("");
        }

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                playerStatus.setText("");
                updatePlayerScore();
            }
        });
    }

    public boolean checkWinner() {
        boolean winnerResult = false;

        for(int [] winningPosition : winningPositions) {
            if(gamestate[winningPosition[0]] == gamestate[winningPosition[1]] &&
                    gamestate[winningPosition[1]] == gamestate[winningPosition[2]] &&
                    gamestate[winningPosition[0]] != 2) {
                winnerResult = true;

            }
        }
        return winnerResult;
    }

    public void updatePlayerScore() {
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

    public void playAgain() {
        roundCount = 0;
        activePlayer = true;

        for(int i = 0; i < buttons.length; i++) {
            gamestate[i] = 2;
            buttons[i].setText("");
        }
    }
}
