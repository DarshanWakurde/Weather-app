package com.example.wetherown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Splash extends AppCompatActivity {
    Switch switcn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        switcn =findViewById(R.id.switch2);


        switcn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    startActivity(new Intent(Splash.this,MainActivity.class));
                    compoundButton.setChecked(false);
                }
                else {
                    Toast.makeText(Splash.this, "Noo", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}