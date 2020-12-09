package com.example.lesson4_recyclerview_27;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class ActivityTwo extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private String currentDate;
    public static  String KEY2 = "key_2";
    private EditText etText;
    private Button btnResult;
    private TextView txtDate;
    private Button btnDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        init();
        Intent intent = getIntent();
        if (intent != null){
                Title title = (Title) intent.getSerializableExtra(MainActivity.KEY);
                if ( title != null) {
                    etText.setText(title.name);
                    txtDate.setText(title.date);
            }
        }
    }

    private void init() {
        etText = findViewById(R.id.etText);
        btnResult = findViewById(R.id.btnResult);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Title title = new Title(etText.getText().toString(),currentDate);
                intent.putExtra(KEY2, title);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        txtDate = findViewById(R.id.txtDate);
        btnDate = findViewById(R.id.btnDate);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        txtDate.setText(currentDate);

    }
}