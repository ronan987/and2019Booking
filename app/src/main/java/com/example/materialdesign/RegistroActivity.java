package com.example.materialdesign;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {
    private final String CHANNEL_ID="Notificacion";
    private final int NOTIFICATION_ID = 03;

    EditText textInputEmail;
    EditText textInputPassword;

    private static final Pattern PASSWORD_PATTERN=
            Pattern.compile( "^"+"(?=.*[0-9])"+"(?=.*[a-zA-Z])"+"(?=\\S+$)"+".{6,}"+"$" );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registro );


        textInputEmail= findViewById( R.id.correo );
        textInputPassword= findViewById( R.id.contraseña);
    }
    private boolean validarEmail() {
        String emailInput = textInputEmail.getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEmail.setError( "debe ingresar su correo" );
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher( emailInput ).matches()) {
            textInputEmail.setError( "ingrese nuevamente su correo" );
            return false;
        } else {
            textInputEmail.setError( null );
            return true;
        }
    }
    private boolean validarcontraseña() {
        String passwordInput = textInputPassword.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            textInputEmail.setError( "debe ingresar su correo" );
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher( passwordInput ).matches()) {
            textInputEmail.setError( "ingrese nuevamente su correo" );
            return false;

        } else {
            textInputEmail.setError( null );
            return true;
        }
    }
    public void confirmarInput(View view){
        if (!validarEmail()|validarcontraseña()){
            return;
        }

        String input ="Email :"+ textInputEmail.getText().toString();
        input +="\n";
        input +=" contraseña :"+ textInputPassword.getText().toString();

        Toast.makeText( this,input,Toast.LENGTH_SHORT ).show();

    }

        public void confirmar(View v){

            Intent i= new Intent( RegistroActivity.this,AmigosActivity.class );
            startActivity( i );

        }

        public void registro(View v) {
            createNotificationChannel();
            NotificationCompat.Builder builder = new NotificationCompat.Builder( getApplicationContext(),CHANNEL_ID );
            builder.setSmallIcon( R.drawable.logobooking );
            builder.setLargeIcon( BitmapFactory.decodeResource( getResources(),R.drawable.logobooking ) );
            builder.setContentTitle( "inicio de sesion" );
            builder.setStyle( new NotificationCompat.BigTextStyle().bigText( "Usted esta ingresando a la plataforma de booking."+"ingreso correctamente" ) );
            builder.setPriority( NotificationCompat.PRIORITY_DEFAULT );

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from( getApplicationContext() );
            notificationManagerCompat.notify( NOTIFICATION_ID,builder.build() );

        }



    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            CharSequence name="Notificacion";
            String description="Bienvenido a Booking la primera app de hoteles en popayán";

            int importance= NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel= null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = new NotificationChannel(CHANNEL_ID,name,importance);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel.setDescription(description);
            }

            NotificationManager notificationManager= (NotificationManager)getSystemService( NOTIFICATION_SERVICE );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel( notificationChannel );
            }

        }


      }

}









