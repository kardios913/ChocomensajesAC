package com.example.encrypted.chocomensajesac;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by KRLOS on 14/08/2017.
 */

public class view_precio  extends AppCompatActivity {
    String precio ="";
    TextView mensaje, direcion;
    Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_precio);

        this.settoolbar();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabenviar);
        mensaje = (TextView) findViewById(R.id.view);
        direcion = (TextView) findViewById(R.id.viewubi);

        Intent intent = getIntent();

         precio = intent.getStringExtra(MainActivity.EXTRA_NOMBRE);
direcion.setText(" centro comercial Alejandría\n entrada 25 primera esquina");
        mensaje.setText("Tu palabra *" + precio + "* \ntiene un precio de $" + precio(precio) + "\nChocoMensajes A&C..." +
                "\nla mejor opcion a la hora de dar un detalle\ncontactanos 3143215562");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msj="hola...\n mi palabra\n*"+ precio.replace(" ","* *") + "*  \ntiene un precio de *$" + precio(precio)+"*\nMensaje generado por\n_ChocoMensajes_ _A&C_..." ;

                EnviarWhatsApp(msj);
            }


        });




    }

    public void settoolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbarview);
        toolbar.setTitle("Precio");
        setSupportActionBar(toolbar);
    }
    public String precio(String mensaje) {
        String cad = mensaje;
        cad = cad.trim();
        int precio = cad.length();
        cad = String.valueOf(precio * 900);
        return cad;
    }
/*enviar mensaje a whatsapp selecionando contacto*/
    public  void EnviarWhatsApp(String msj){
        PackageManager pm=getPackageManager();
        try {
            Intent Int = new Intent(Intent.ACTION_SEND);
            Int.setType("text/plain");
            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            Int.setPackage("com.whatsapp");
            Int.putExtra(Intent.EXTRA_TEXT, msj);
            startActivity(Intent.createChooser(Int, "Compartir con:"));
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp aun no esta instalado!", Toast.LENGTH_SHORT)
                    .show();
        }
    }



}