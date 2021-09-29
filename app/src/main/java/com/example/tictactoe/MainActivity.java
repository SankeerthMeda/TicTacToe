   package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   private Button[] buttons=new Button[9];
    Button resetgame;
    int playerone,playertwo,roundcount;
    boolean activePlayer;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winning={
            {0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}
    };
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetgame=findViewById(R.id.button10);
        for(int i=0;i<buttons.length;i++){
            String ButtonID="button"+i;
            int ResouceId=getResources().getIdentifier(ButtonID,"id",getPackageName());
            buttons[i]=(Button)findViewById(ResouceId);
            buttons[i].setOnClickListener(this);
        }
        roundcount=0;
        playerone=0;
        playertwo=0;
        activePlayer=true;

    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return ;
        }
        String buttonId=getResources().getResourceEntryName(v.getId());
        int gameStatePointer=Integer.parseInt(buttonId.substring(buttonId.length()-1));
        if(activePlayer){
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#FFC34A"));
            gamestate[gameStatePointer]=0;
        }
        else{
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#70FFEA"));
            gamestate[gameStatePointer]=1;
        }
        roundcount++;
        if(chaeckWinner()){
            if(activePlayer){
                Toast.makeText(this,"Woohoo..! Player 1 has WON",Toast.LENGTH_LONG).show();
                playAgain();
            }
            else {
                Toast.makeText(this,"Woohoo..! Player 2 has WON",Toast.LENGTH_LONG).show();
                playAgain();
            }
        }
        else if(roundcount==9){
            Toast.makeText(this,"OOPS...!no player has WON",Toast.LENGTH_LONG).show();
        }
        else{
            activePlayer=!activePlayer;
        }
    }
    public boolean chaeckWinner(){
        boolean Winnerresult=false;
        for(int[] WinningPosition:winning){
            if(gamestate[WinningPosition[0]]==gamestate[WinningPosition[1]]&&gamestate[WinningPosition[1]]==gamestate[WinningPosition[2]]&&gamestate[WinningPosition[0]]!=2){
                Winnerresult=true;
            }
        }
        return Winnerresult;


    }
    public void playAgain(){
        roundcount=0;
        activePlayer=true;
        for(int i=0;i<buttons.length;i++){
            gamestate[i]=2;
            buttons[i].setText("");
        }
    }


    public void reset(View view) {
        playAgain();
    }

    @Override
    protected void onStart() {
        Toast.makeText(this, "click on the buttons", Toast.LENGTH_SHORT).show();
        super.onStart();
    }

    public void play(View view) {
        if(mediaPlayer==null){
mediaPlayer=MediaPlayer.create(this,R.raw.chunky1);
        }
        mediaPlayer.start();
    }

    public void pause(View view) {
        if(mediaPlayer!=null){
mediaPlayer.pause();
Toast.makeText(getBaseContext(),"paused",Toast.LENGTH_SHORT).show();
        }
    }
}