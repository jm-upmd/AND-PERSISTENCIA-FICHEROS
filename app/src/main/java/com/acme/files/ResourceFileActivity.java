package com.acme.files;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

// Utilizamos ListActivity que hereda de Activity. Esta tiene embebido un layout con una
// ListView que infla por defecto y no tenemos que construirlo nosotros.

public class ResourceFileActivity extends ListActivity {
    ArrayList<String> listaElementos = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Esto no hace falta. Solo si queremos cargar un layout diferente al de por defecto.
        //setContentView(R.layout.activity_resource_file);

        //leeQuesos();
        leeGodos();


    }

    private void leeQuesos() {


        try {

            // Hacemos referencia al fichero como un recurso raw.
            InputStream in = getResources().openRawResource(R.raw.quesos);

            InputStreamReader reader = new InputStreamReader(in, "UTF-8");
            BufferedReader buff = new BufferedReader(reader);
            String strTmp;
            while ((strTmp = buff.readLine()) != null) {

                listaElementos.add(strTmp.replace(";", " - "));
            }

            in.close();
        } catch (Throwable t) {
            Toast.makeText(this, "Error:" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        // Para el item del ListView Utiliza un layout por defecto predefinido en android.
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaElementos));
    }

    private void leeGodos() {
        try {
            //obtenemos el recurso
            InputStream in = getResources().openRawResource(R.raw.godos);

            //Generamos el documento XML
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(in);
            NodeList fileGodos = doc.getElementsByTagName("godo");

            // se recorren los nodos guardándolos en el array
            for (int i = 0; i < fileGodos.getLength(); i++) {
                listaElementos.add( fileGodos.item(i).getTextContent());
            }
            in.close();
        } catch (Throwable t) {
            Toast.makeText(this, "Error:" + t.getMessage(), Toast.LENGTH_LONG).show();
        }
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaElementos));
    }

}

