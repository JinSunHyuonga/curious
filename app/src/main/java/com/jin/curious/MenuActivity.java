package com.jin.curious;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    String shared = "file";
    public String ipValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        refreshIP();
        if (ipValue.equals("")) {
            
        }

        Button buttonSound = findViewById(R.id.buttonSound);
        buttonSound.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                refreshIP();
                if (ipValue.equals("")) {
                    //Toast.makeText(MenuActivity.this, "IP를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    //IP입력 안하면 서비스로 자동으로 실행 테스트중
                    Intent intent = new Intent(getApplicationContext(), SoundActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), SoundActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button buttonScreen = findViewById(R.id.buttonScreen);
        buttonScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                refreshIP();
                if (ipValue.equals("")) {
                    Toast.makeText(MenuActivity.this, "IP를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MenuActivity.this, "구현되지 않은 기능입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button buttonFile = findViewById(R.id.buttonFile);
        buttonFile.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                refreshIP();
                if (ipValue.equals("")) {
                    Toast.makeText(MenuActivity.this, "IP를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MenuActivity.this, "구현되지 않은 기능입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button buttonLocate = findViewById(R.id.buttonLocate);
        buttonLocate.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                refreshIP();
                if (ipValue.equals("")) {
                    Toast.makeText(MenuActivity.this, "IP를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MenuActivity.this, "구현되지 않은 기능입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button buttonSearchApp = findViewById(R.id.buttonSearchApp);
        buttonSearchApp.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                refreshIP();
                if (ipValue.equals("")) {
                    Toast.makeText(MenuActivity.this, "IP를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MenuActivity.this, "구현되지 않은 기능입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button buttonSetting = findViewById(R.id.buttonSetting);
        buttonSetting.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

    }

    private void refreshIP() {
        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        ipValue = sharedPreferences.getString("ip","");
    }
}