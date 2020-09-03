package com.procrastinator.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.pm.ActivityInfo.*;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    // 1-->X
    // 0-->O
    // 2-->NULL
    int activePlayer=1;

    int[] gameStates={2,2,2,2,2,2,2,2,2};

    //Winning Positions
    int[][] winningPosition={{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};

    public void playerTap(View view){
        boolean flag=true;
        ImageView img=(ImageView)view;
        //getTag() return-type is object
        int tappedImage=Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }else if(gameStates[tappedImage]==2){
            gameStates[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);
            TextView status = findViewById(R.id.status);
            if(activePlayer==1){
                img.setImageResource(R.drawable.x);
                activePlayer=0;

                status.setText("O's Turn - Tap To play.");
            }else{
                img.setImageResource(R.drawable.o);
                activePlayer=1;
                status.setText("X's Turn - Tap To play.");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }else{
            for (int counterState :gameStates){
                if(counterState==2){
                    flag=false;
                }
            }
            if(flag){
                gameActive=false;
                TextView status = findViewById(R.id.status);
                status.setText("It's a Tie! ");
            }
        }

        String winner;

        for (int[] winPos : winningPosition){
            if(gameStates[winPos[0]]==gameStates[winPos[1]]
                    && gameStates[winPos[1]]==gameStates[winPos[2]]
                    && gameStates[winPos[0]]!=2){
                //Somebody has won

                gameActive=false;
                if(gameStates[winPos[0]]==1){
                    winner="X has won";
                }else{
                    winner="O has won";
                }
                TextView status=findViewById(R.id.status);
                status.setText(winner);


            }

        }


    }

    public void gameReset(View view){
        gameActive=true;
        activePlayer=1;
        for (int itr1=0;itr1<gameStates.length;itr1++){
            gameStates[itr1]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status=findViewById(R.id.status);
        status.setText("X's turn - Tap to play.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_main);

    }
}
