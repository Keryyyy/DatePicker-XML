package com.ycuwq.datepicker.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ycuwq.datepicker.date.CustomPicker;
import com.ycuwq.datepicker.date.DatePickerDialogFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView dateTv = findViewById(R.id.tv_date);
        //the customPicker
        CustomPicker datePicker = findViewById(R.id.datePicker);
        //listener
        datePicker.setOnDataSelectListener(new CustomPicker.CustomPickerListener() {
            @Override
            public void onNumberSelected(int number) {
                dateTv.setText(String.valueOf(number));
            }
        });

        /**
         * the picker for dialog style
         */
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
            datePickerDialogFragment.setOnDateChooseListener(new DatePickerDialogFragment.OnDateChooseListener() {
                @Override
                public void onDateChoose(int year, int month, int day) {
                    Toast.makeText(getApplicationContext(), year + "-" + month + "-" + day, Toast.LENGTH_SHORT).show();
                }
            });
            datePickerDialogFragment.show(getFragmentManager(), "DatePickerDialogFragment");
        });
    }
}
