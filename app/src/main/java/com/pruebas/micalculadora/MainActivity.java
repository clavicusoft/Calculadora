package com.pruebas.micalculadora;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ModeloCalculadora modeloCalculadora;
    private TextView resultadoTexto;
    private Integer resultadoCalculadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Para recuperar el estado de la app cuando ha estado inactiva
        setContentView(R.layout.activity_main); // Asocia la actividad con el layout
        modeloCalculadora = new ModeloCalculadora();
        resultadoTexto = (TextView) findViewById(R.id.resultado); // obtiene referencia al campo de texto
    }

    void botonClicado(View v){
        Button boton = (Button)findViewById(v.getId()); // Busca en el layout el botón mediante v.getId()
        String id = boton.getText().toString(); // Recupera el texto y lo convierte en String
        switch (id) {
            case "MCD":
                resultadoCalculadora = modeloCalculadora.mcd();
                break;
            case "N!":
                resultadoCalculadora = modeloCalculadora.factorial();
                break;
            case "CE":
                // Mensaje de que indica función no implementada:
                Toast.makeText(this,"Función no implementada",Toast.LENGTH_LONG).show();
                break;
            case "C":
                modeloCalculadora.limpiarTodo();
                resultadoCalculadora = 0;
                break;
            case "=":
                resultadoCalculadora = modeloCalculadora.cerrar();
                break;
            case "+":
                resultadoCalculadora = modeloCalculadora.sumar();
                break;
            case "-":
                resultadoCalculadora = modeloCalculadora.restar();
                break;
            case "*":
                resultadoCalculadora = modeloCalculadora.multiplicar();
                break;
            case "/":
                resultadoCalculadora = modeloCalculadora.dividir();
                break;
            case "0":
                resultadoCalculadora = modeloCalculadora.digito(0);
                break;
            case "1":
                resultadoCalculadora = modeloCalculadora.digito(1);
                break;
            case "2":
                resultadoCalculadora = modeloCalculadora.digito(2);
                break;
            case "3":
                resultadoCalculadora = modeloCalculadora.digito(3);
                break;
            case "4":
                resultadoCalculadora = modeloCalculadora.digito(4);
                break;
            case "5":
                resultadoCalculadora = modeloCalculadora.digito(5);
                break;
            case "6":
                resultadoCalculadora = modeloCalculadora.digito(6);
                break;
            case "7":
                resultadoCalculadora = modeloCalculadora.digito(7);
                break;
            case "8":
                resultadoCalculadora = modeloCalculadora.digito(8);
                break;
            case "9":
                resultadoCalculadora = modeloCalculadora.digito(9);
                break;
        }
        resultadoTexto.setText(resultadoCalculadora.toString());
    }
}
