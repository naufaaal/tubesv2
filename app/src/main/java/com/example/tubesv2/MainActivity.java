package com.example.tubesv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView control, counter, sort, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //define cards
        control = (CardView) findViewById(R.id.ctrl);
        counter = (CardView) findViewById(R.id.ctr);
        sort = (CardView) findViewById(R.id.sort);
        about = (CardView) findViewById(R.id.about);

        //set click
        control.setOnClickListener(this);
        counter.setOnClickListener(this);
        sort.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.ctrl: i = new Intent(this, menucontrol.class); startActivity(i); break;
            case R.id.ctr: i = new Intent(this, counter.class); startActivity(i); break;
            case R.id.sort: i = new Intent(this, sortir.class); startActivity(i); break;
            default:break;
        }
    }
}
