package com.example.siparissistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    EditText  kullaniciadiText,sifreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kullaniciadiText= findViewById(R.id.mainactivity_isim_text);
        sifreText=findViewById(R.id.mainactivity_sifre_text);

        //Güncel kullanıcıyı al
        ParseUser parseUser=ParseUser.getCurrentUser();
        //Güncel kullanıcı varsa anasayfaya yönlendir
        if(parseUser != null )
        {
            Toast.makeText(getApplicationContext(),"Hoşgeldin "+parseUser.getUsername(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(),AnaSayfa.class);
            startActivity(intent);
        }


    }

    public void GirisYap(View view)
    {
    ParseUser.logInInBackground(kullaniciadiText.getText().toString(), sifreText.getText().toString(), new LogInCallback() {
        @Override
        public void done(ParseUser user, ParseException e) {
            //Hata varsa
            if(e!=null)
            {
                kullaniciadiText.setError(e.getLocalizedMessage());
                //Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
            //Hata yoksa
            else
            {
                Toast.makeText(getApplicationContext(),"Hoşgeldin "+user.getUsername(),Toast.LENGTH_LONG).show();
                //Ana sayfaya yönlendir
                Intent intent = new Intent(getApplicationContext(),AnaSayfa.class);
                startActivity(intent);
            }
        }
    });
    }
    public void KayitOl(View view)
    {
        //Yeni bir Parse user oluştur
        ParseUser kullanici = new ParseUser();
        //Kullanıcı adı ve şifre bilgilerini veritabanına aktar
        kullanici.setUsername(kullaniciadiText.getText().toString());
        kullanici.setPassword(sifreText.getText().toString());
        //Veritabanının kaydet
        kullanici.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
              //Bir hata çıktıysa
                if(e!=null)
                {
                    kullaniciadiText.setError(e.getLocalizedMessage());
                   // Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }//Bir hata çıkmadıysa
                else
                {
                    Toast.makeText(getApplicationContext(),"Kullanıcı Oluşturuldu",Toast.LENGTH_LONG).show();
                    //Ana sayfaya yönlendir
                    Intent intent = new Intent(getApplicationContext(),AnaSayfa.class);
                    startActivity(intent);
                }

            }
        });
    }

}
