package com.acme.files;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class FicheroExternoActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_file);
        Button btn =  findViewById(R.id.save);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escribir();
            }
        });

        btn =  findViewById(R.id.load);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leer();
            }
        });
    }

    protected void escribir() {
        EditText editor =  findViewById(R.id.editor);
        EditText nombreFichero =  findViewById(R.id.filename);
        OutputStreamWriter out;
        try {
            out = new OutputStreamWriter(openFileOutput(nombreFichero.getText().toString(), 0));
            out.write(editor.getText().toString());
            out.flush();
            out.close();
            showMessage("Se ha grabado el documento");
        } catch (Exception t) {
            showMessage("Error: " + t.getLocalizedMessage());
        }
    }
    protected void leer() {
        EditText editor =  findViewById(R.id.editor);
        EditText nombreFichero =  findViewById(R.id.filename);
        InputStreamReader in;
        try {
            in = new InputStreamReader(openFileInput(nombreFichero.getText().toString()));
            BufferedReader buff = new BufferedReader(in);
            String strTmp = null;
            StringBuffer strBuff = new StringBuffer();
            while ((strTmp = buff.readLine())!=null){
                strBuff.append(strTmp + "\n");
            }
            in.close();
            editor.setText(strBuff.toString());
            showMessage("Se ha leído el documento");
        } catch (Exception t) {
            showMessage("Error: " + t.getLocalizedMessage());
        }
    }

    protected void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG);
    }


}
