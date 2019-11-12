package com.example.siparissistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class Bilgilerim extends AppCompatActivity {
EditText kullaniciadi,kullanicimail,kullaniciadres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgilerim);

        ParseUser parseUser=ParseUser.getCurrentUser();

      kullaniciadi=findViewById(R.id.bilgilerim_isim);
      kullaniciadi.setText(parseUser.getUsername());
      kullaniciadi.setEnabled(false);

      kullanicimail=findViewById(R.id.bilgilerim_mail);
      kullanicimail.setText(parseUser.getEmail());

        kullaniciadres=findViewById(R.id.bilgilerim_adres);

    }


    public void Guncelle(View view)
    {

        ParseUser parseUser=ParseUser.getCurrentUser();

        parseUser.setEmail(kullanicimail.getText().toString());//Maili değiştir
parseUser.saveInBackground(new SaveCallback() {//Kaydet
    @Override
    public void done(ParseException e) {
        if(e!=null)
        {
            Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Mail Güncellendi",Toast.LENGTH_LONG).show();
        }
    }
});

        if(!(kullaniciadres.getText().toString().trim().equals("")))//Adres kutusu boş değilse gir
        {
            ParseObject object = new ParseObject("Adresler");

            object.put("UserId",parseUser.getObjectId());
            object.put("Adres",kullaniciadres.getText().toString());
            object.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e!=null)
                    {
                        Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Adres Güncellendi",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


    }



}
