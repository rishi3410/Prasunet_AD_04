package com.rishi.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    //0 - X
    //1 - O
    //2 - blank
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
  public void Tap(View view ){
      ImageView img = (ImageView)view;
      int tappedImage = Integer.parseInt(img.getTag().toString());

      if(gameState[tappedImage] == 2 && gameActive){
          gameState[tappedImage] = activePlayer;
          img.setTranslationY(-1000f);

          if(activePlayer == 0){
              img.setImageResource(R.drawable.x);
              activePlayer = 1;
              TextView status = findViewById(R.id.status);
              status.setText("O's turn");
          }
          else{
              img.setImageResource(R.drawable.o);
              activePlayer = 0;
              TextView status = findViewById(R.id.status);
              status.setText("X's turn");
          }


          if(gameState[0]!= 2 && gameState[1]!= 2 && gameState[2]!= 2 && gameState[3]!= 2 && gameState[4]!= 2 && gameState[5]!= 2  && gameState[6]!= 2  && gameState[7]!= 2  && gameState[8]!= 2 ){
              TextView status = findViewById(R.id.status);
              status.setText("Draw");
          }
          img.animate().translationYBy(1000f).setDuration(300);
      }


      //For win case
      for(int[] winPos: winPos){
         if( gameState[winPos[0]] != 2 && gameState[winPos[0]] == gameState[winPos[1]] &&  gameState[winPos[1]] == gameState[winPos[2]]){
             String winner;
             if(gameState[winPos[0]] == 0){
                 winner = "X has won";
             }
             else{
                 winner = "O has won";
             }
             TextView status = findViewById(R.id.status);
             status.setText(winner);
             gameActive = false;



         }
      }


  }


  public void reset(View view) {
      for(int i=0; i<gameState.length; i++){
          gameState[i] = 2;
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
      TextView status = findViewById(R.id.status);
      status.setText("X's turn");
      activePlayer = 0;
      gameActive = true;

  }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}