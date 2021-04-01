package de.ckitte.tutorial01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

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

        Button myFragment = findViewById(R.id.btnFragmentButton);
        myFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.open:
                //impliziter Intent
                Intent myIntent = new Intent(Intent.ACTION_VIEW);
                myIntent.setData(Uri.parse("http://www.ckitte.de"));
                startActivity(myIntent);

                return true;
            case R.id.close:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void btnNextPage(View v) {
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

    public void btnNextPage2(View v) {
        Intent myIntent = new Intent(this, MainActivity2.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(myIntent);
}

    public void btnClosePressed(View v) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setCancelable(false);

        dialog.setTitle(getResources().getText(R.string.appCloseTitle));
        dialog.setMessage(getResources().getText(R.string.appCloseMessage));

        dialog.setPositiveButton(getResources().getText(R.string.appRunText), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing. Positive means go on...
            }
        });

        dialog.setNegativeButton(getResources().getText(R.string.appCloseText), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Close the App. Negative means shut down...
                MainActivity.this.finish();
            }
        });

        dialog.show();
    }

    public void switchFragment() {
        myReplaceFragment x = new myReplaceFragment();

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.fragment, x);
        trans.addToBackStack(x.getClass().toString());
        trans.commit();
    }
}
