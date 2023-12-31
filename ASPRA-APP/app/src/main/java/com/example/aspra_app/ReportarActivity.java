package com.example.aspra_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aspra_app.data.DatabaseHelper;

public class ReportarActivity extends AppCompatActivity {

    EditText edtdireccion, edtdescripcion;
    Button button_reporte_enviar;
    Spinner spMotivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar);

        spMotivo = findViewById(R.id.spMotivo);

        String[] Motivo = {"Maltrato", "Abandono", "Otro"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Motivo);

        spMotivo.setAdapter(adaptador);

        edtdireccion = findViewById(R.id.etDireccion);
        edtdescripcion = findViewById(R.id.etDescripcion);

        button_reporte_enviar = findViewById(R.id.button_reporte_enviar);

        final DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

        button_reporte_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* String motivo = spMotivo.getSelectedItem().toString();
                databaseHelper.agregarReportes(edtdireccion.getText().toString(), motivo, edtdescripcion.getText().toString());*/
                Toast.makeText(getApplicationContext(), "SE AGREGÓ CORRECTAMENTE", Toast.LENGTH_SHORT).show();

                irReporteExitoso(ReportarActivity.this);
            }
        });
    }

    public void irReporteExitoso(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Reporte");
        builder.setMessage("Enviando reporte...");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(context, ReporteExitosoActivity.class);
                startActivity(intent);
            }
        });
        builder.show();
    }
}
