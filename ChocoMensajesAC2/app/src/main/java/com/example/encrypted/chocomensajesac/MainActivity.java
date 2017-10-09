package com.example.encrypted.chocomensajesac;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.Contacts.People;
import android.provider.ContactsContract.CommonDataKinds.Phone;

public class MainActivity extends AppCompatActivity {
    String name = "ChocoMensajesAC";
    String number = "3143215562";
    public final static String EXTRA_NOMBRE = "TUPAQUETE.petmotion.NOMBRE";
String cad ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.settoolbar();
        final TextView mensaje = (TextView) findViewById(R.id.view);
        final EditText mensajeEdit = (EditText) findViewById(R.id.campo_mensaje);
        bd bd= new bd(this);
        bd.open();

try {
    cad= bd.consultar();

    if(cad.equalsIgnoreCase("")){
        bd.insert(0);
        addContact(name, number);
        Context context = getApplicationContext();
        CharSequence text = "ChocoMensajesAC se ha guardado como contacto en su telefono...";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}catch (Exception e){

    Log.e("Encrypte.debug", e.toString());

}

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String precio = mensajeEdit.getText().toString();
                if (TextUtils.isEmpty(precio)) {
                    Context context = getApplicationContext();
                    CharSequence text = "Favor Escribe Tu Mensaje!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {

                    Context context = getApplicationContext();
                    Intent intent = new Intent(context,view_precio.class);

                    intent.putExtra(EXTRA_NOMBRE,precio);
                    startActivity(intent);

                }
            }


        });

    }

    public void settoolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ChocoMensajes A&C");
        setSupportActionBar(toolbar);
    }




    private void addContact(String name, String phone) {
        ContentValues values = new ContentValues();
        values.put(People.NUMBER, phone);
        values.put(People.TYPE, Phone.TYPE_CUSTOM);
        values.put(People.LABEL, name);
        values.put(People.NAME, name);
        Uri dataUri = getContentResolver().insert(People.CONTENT_URI, values);
        Uri updateUri = Uri.withAppendedPath(dataUri, People.Phones.CONTENT_DIRECTORY);
        values.clear();
        values.put(People.Phones.TYPE, People.TYPE_MOBILE);
        values.put(People.NUMBER, phone);
        updateUri = getContentResolver().insert(updateUri, values);
    }


}