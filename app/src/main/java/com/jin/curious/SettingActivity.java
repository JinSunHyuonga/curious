package com.jin.curious;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    String shared = "file";
    EditText ip_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        ip_save = findViewById(R.id.editTextIp);
        Button buttonSave = findViewById(R.id.buttonSave);

        String ipValue = sharedPreferences.getString("ip","");
        ip_save.setText(ipValue);

        buttonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String ipValue = ip_save.getText().toString();
                editor.putString("ip",ipValue);
                editor.commit();

                Toast.makeText(SettingActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}