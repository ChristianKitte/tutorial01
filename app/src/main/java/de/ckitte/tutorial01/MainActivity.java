package de.ckitte.tutorial01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "OnResume");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Lifecycle", "OnCreate");

        setContentView(R.layout.activity_main);
    }

    public void btnPressed(View v) {
        //expliziter Intent
        Intent myIntent = new Intent(this, SubActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(myIntent);

        //Toast ist eine Art Hinweistext, der auch zu Debugausgaben genutzt werden kann und nach einiger
        //Zeit verschwindet
        //Toast.makeText(this, getResources().getText(R.string.notification), Toast.LENGTH_SHORT).show();

        //Zum Ändern eines Textes gebraucht man eine Referenz auf diesen Text
        //TextView text = findViewById(R.id.txtChange);
        //text.setText(getResources().getText(R.string.newText));
    }
}