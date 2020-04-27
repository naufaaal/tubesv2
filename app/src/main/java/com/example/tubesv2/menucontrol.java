package com.example.tubesv2;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import androidx.cardview.widget.CardView;

import org.json.JSONException;
import org.json.JSONObject;

import id.co.telkom.iot.AntaresHTTPAPI;
import id.co.telkom.iot.AntaresResponse;

import android.os.Bundle;

public class menucontrol extends AppCompatActivity implements AntaresHTTPAPI.OnResponseListener {

    private Button refresh;
    private CardView nyala, mati;
    private TextView stat;
    private String TAG = "https://platform.antares.id:8443/~/antares-cse/antares-id/Smart-Weather/main1";
    private AntaresHTTPAPI antaresAPIHTTP;
    private String dataDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menucontrol);

        nyala = (CardView) findViewById(R.id.on);
        mati = (CardView) findViewById(R.id.off);
        refresh = (Button) findViewById(R.id.refresh);

        stat = (TextView) findViewById(R.id.stat);

        antaresAPIHTTP = new AntaresHTTPAPI();
        antaresAPIHTTP.addListener(this);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                antaresAPIHTTP.getLatestDataofDevice("90bdfae59104dc83:36f72dfc4b9558f9","Smart-Weather","main1");

            }
        });

        nyala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                antaresAPIHTTP.storeDataofDevice(1, "90bdfae59104dc83:36f72dfc4b9558f9", "Smart-Weather", "main1", "{\\\"status\\\":\\\"1\\\"}");

            }
        });

        mati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                antaresAPIHTTP.storeDataofDevice(1, "90bdfae59104dc83:36f72dfc4b9558f9", "Smart-Weather", "main1", "{\\\"status\\\":\\\"0\\\"}");
            }
        });
    }

    @Override
    public void onResponse(AntaresResponse antaresResponse) {
        //Log.d(TAG,antaresResponse.toString());
        Log.d(TAG,Integer.toString(antaresResponse.getRequestCode()));
        if(antaresResponse.getRequestCode()==0){
            try {
                JSONObject body = new JSONObject(antaresResponse.getBody());
                dataDevice = body.getJSONObject("m2m:cin").getString("con");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stat.setText(dataDevice);
                    }
                });
                Log.d(TAG,dataDevice);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
