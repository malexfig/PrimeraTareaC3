package com.curso.android.primeratareac3;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText nombre, telefono, email, descripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarViews();
        iniciarCalendario();
    }

    private void iniciarViews() {

        nombre = (TextInputEditText) findViewById(R.id.txt_nombre);
        telefono = (TextInputEditText) findViewById(R.id.txt_telefono);
        email = (TextInputEditText) findViewById(R.id.txt_email);
        descripcion = (TextInputEditText) findViewById(R.id.txt_descontacto);


        /*Floating Action Button*/
        FloatingActionButton btnConfirmar = (FloatingActionButton) findViewById(R.id.fabConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent activity2 = new Intent(MainActivity.this, Main2Activity.class);
                activity2.putExtra("nombre", nombre.getText().toString());
                activity2.putExtra("fecha", fecha.getText().toString());
                activity2.putExtra("telefono", telefono.getText().toString());
                activity2.putExtra("email", email.getText().toString());
                activity2.putExtra("descripcion", descripcion.getText().toString());
                startActivity(activity2);

                /*Snackbar.make(v, getResources().getString(R.string.app_name), Snackbar.LENGTH_SHORT)
                        .setAction("", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.i("SNACKBAR", "Se presionó el snackbar");
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .show();*/
            }
        });
    }

    /*************************
     * CALENDARIO
     ***************************/
    private int mYear, mMonth, mDay;

    private TextInputEditText fecha;
    private TextView tvDespliegaFecha;

    private Button btnPickDate;
    private static final int DATE_DIALOG_ID = 0;


    private void iniciarCalendario() {
        tvDespliegaFecha = (TextView) findViewById(R.id.txt_fecha);
        //Para guardar el texto del text view
        fecha = (TextInputEditText) findViewById(R.id.txt_fecha);

        btnPickDate = (Button) findViewById(R.id.btnfecha);

        btnPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();

    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year,
                              int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };

    private void updateDisplay() {
        String dia = String.valueOf(mDay);
        String mes = String.valueOf(mMonth + 1);

        if (contarDigitosFecha(mDay) == 1)
            dia = "0" + dia;
        if (contarDigitosFecha(mMonth + 1) == 1)
            mes = "0" + mes;


        tvDespliegaFecha.setText(
                new StringBuilder()
                        .append(dia).append("-")
                        .append(mes).append("-")
                        .append(mYear).append(" "));

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }


    public int contarDigitosFecha(int n) {
        int c = 0;
        while (n > 0) {
            n = n / 10;
            c += 1;
        }

        return c;
    }
}
