package com.example.materialdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;

public class LoginActivity extends AppCompatActivity {
    private static final int CODIGO_PERMISO_ESCRIBIR_ALMACENAMIENTO = 1;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    private boolean tienePermisoParaEscribir = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onClick(View v) {
        Intent i = new Intent(LoginActivity.this,QrCodeActivity.class);
        Toast.makeText(this,"Va Inicializar el lector QR",Toast.LENGTH_SHORT).show();
        startActivityForResult(i,REQUEST_CODE_QR_SCAN);
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data) {

        super.onActivityResult( requestCode,resultCode,data );
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText( getApplicationContext(),"No se pudo obtener una respuesta",Toast.LENGTH_SHORT ).show();
            String resultado = data.getStringExtra( ".error_decodificacion_en la imagen" );
            if (resultado != null) {
                Toast.makeText( getApplicationContext(),"No se pudo escanear el código QR",Toast.LENGTH_SHORT ).show();
            }
            return;
        }
        if (requestCode == REQUEST_CODE_QR_SCAN) {
            if (data != null) {
                String lectura = data.getStringExtra( "codigo leido correctamente" );
                Toast.makeText( getApplicationContext(),"Leído: "+lectura,Toast.LENGTH_SHORT ).show();

            }

        }
        verificarYPedirPermisos();
    }

    private void verificarYPedirPermisos() {
        if (
                ContextCompat.checkSelfPermission(
                        LoginActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        ==
                        PackageManager.PERMISSION_GRANTED
        ) {
            // En caso de que haya dado permisos ponemos la bandera en true
            tienePermisoParaEscribir = true;
        } else {
            // Si no, entonces pedimos permisos

            ActivityCompat.requestPermissions(LoginActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    CODIGO_PERMISO_ESCRIBIR_ALMACENAMIENTO);
        }
    }

    private void noTienePermiso() {
        Toast.makeText(LoginActivity.this,"No has dado permiso para escribir",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults) {
        switch (requestCode) {
            case CODIGO_PERMISO_ESCRIBIR_ALMACENAMIENTO:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // SÍ dieron permiso
                    tienePermisoParaEscribir = true;

                } else {
                    // NO dieron permiso
                    noTienePermiso();
                }
        }

    }

    public void inicializar(View v){
        Intent i= new Intent(LoginActivity.this,InicioActivity.class);
        Toast.makeText(this,"Ha iniciado Sesión",Toast.LENGTH_SHORT).show();
        startActivity(i);

    }

}
