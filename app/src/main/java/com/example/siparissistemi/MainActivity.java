package com.example.siparissistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseObject object = new ParseObject("Fruits");
//emre
        object.put("name","apple");
        object.put("calories",100);
        object.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null)
                {
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Obje Kaydedildi",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
