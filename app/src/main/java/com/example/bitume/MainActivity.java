package com.example.bitume;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.bitume.environment.Environment;
import com.example.bitume.environment.Loot;
import com.example.bitume.environment.Room;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Environment room = new Room();

    public MainActivity() throws JSONException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button env1 = (Button) findViewById(R.id.env1);
        Button env2 = (Button) findViewById(R.id.env2);
        Button env3 = (Button) findViewById(R.id.env3);

        env1.setOnClickListener((View.OnClickListener) this);
        env2.setOnClickListener((View.OnClickListener) this);
        env3.setOnClickListener((View.OnClickListener) this);

        room.setJsonString(loadJSONFromAsset(this));
        try {
            room.loadList();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }

    public void onClick(View view){
        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.env1){
            String l = room.search();
            showDialog(l);
        }
        else if (clickedButton.getId()==R.id.env2){

        }
        else if (clickedButton.getId()==R.id.env3){

        }
    }

    public void showDialog(String loot){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Loot");
        builder.setMessage(loot);
        //builder.setPositiveButton("OK", (dialogInterface, i) ->actionSuite(score));
        builder.show();
        onPause();
    }

    //Lecture d'un fichier json
    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("test.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}