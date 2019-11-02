package com.example.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.material.navigation.NavigationView;

public class InicioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
     NavigationView navigationView;
     DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        toolbar= findViewById( R.id.toolbar );
        setSupportActionBar(toolbar);

     drawerLayout= findViewById( R.id.navigation );
     ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.abrir,R.string.cerrar);

     drawerLayout.addDrawerListener( toggle );
     toggle.syncState();

     navigationView = findViewById( R.id.navigation_view );
     navigationView.setNavigationItemSelectedListener(this);

    }

    @NonNull
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.escanear:
                Intent i = new Intent( InicioActivity.this,LoginActivity.class );
                startActivity( i );
                break;
            case R.id.inicio:
                 Intent intent1= new Intent(InicioActivity.this,VozActivity.class);
                startActivity(intent1);
              //  Toast.makeText( this,"comandos de voz",Toast.LENGTH_SHORT ).show();
                break;

            case R.id.configuracion:
                Toast.makeText( this,"Mapas",Toast.LENGTH_SHORT ).show();
                break;

            case R.id.alerta:
                Intent intent= new Intent(InicioActivity.this,RegistroActivity.class);
                startActivity( intent );
                break;

            case R.id.megusta:
                Toast.makeText( this,"Autenticacion",Toast.LENGTH_SHORT ).show();
                break;


        }
        drawerLayout.closeDrawer( GravityCompat.START );
        return true;
    }


}







