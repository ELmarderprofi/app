package com.quicknote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.quicknote.R;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private ImageView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Find views by id
        ImageView icon = findViewById(R.id.icon);
        cancel = findViewById(R.id.cancel);
        button = findViewById(R.id.button);

        // Set listener
        cancel.setOnClickListener(this);
        button.setOnClickListener(this);

        // Animate icon
        Animation rotation = AnimationUtils.
                loadAnimation(this, R.anim.rotate);
        rotation.setRepeatCount(Animation.INFINITE);
        icon.startAnimation(rotation);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(button)) {
            Toast.makeText(this,
                    getString(R.string.message_logged_in),
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (view.equals(cancel)) {
            finish();
        }
    }
}
