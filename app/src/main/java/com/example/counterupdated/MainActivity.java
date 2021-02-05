package com.example.counterupdated;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Declare 3 buttons
    private Button resetButton;
    private Button minusButton;
    private Button plusButton;

    // Declare Radio Group Buttons
    private RadioGroup radioGroup;

    // Declare boolean for 3 buttons
    private boolean resetBtn = false;
    private boolean minusBtn = false;
    private boolean plusBtn = false;

    // Object counter class Counter
    private Counter counter;

    // Counter parameters
    private int minVal = 10;
    private int maxVal = 1000;
    private int staVal = 100;
    private int steVal = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // events listener for buttons
        resetButton = (Button)findViewById(R.id.resetBtn);
        resetButton.setOnClickListener(this);
        minusButton = (Button)findViewById(R.id.minusBtn);
        minusButton.setOnClickListener(this);
        plusButton = (Button)findViewById(R.id.plusBtn);
        plusButton.setOnClickListener(this);

        // events listener for radio buttons group
        // check which option is selected to convert value
        // automatic change to lambda
        radioGroup = (RadioGroup)findViewById(R.id.radioBtnGroup);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.decBtn) {
                Log.i("my_counter", "choose decimal");

                counter.setDecimal();
                updateUI();
            } else if (checkedId == R.id.binBtn) {
                Log.i("my_counter", "choose binary");

                counter.setBinary();
                updateUI();
            } else {
                Log.i("my_counter", "choose hexadecimal");

                counter.setHexadecimal();
                updateUI();
            }
        });

        // Object counter class Counter
        counter = new Counter(minVal, maxVal, staVal, steVal);

        /*
        If want to call default counter (-100, 100, 0, 1)
        counter = new Counter();
         */

        // Call UI function
        updateUI();

    }

    @Override
    public void onClick(View view) {

        // Counting buttons
        switch (view.getId()) {
            case R.id.resetBtn:
                Log.i("my_counter", "resetBtnClick()");

                // When button is clicked, run updateUI()
                resetBtn = true;
                updateUI();
                break;

            case R.id.minusBtn:
                Log.i("my_counter", "minusBtnClick()");

                minusBtn = true;
                updateUI();
                break;

            case R.id.plusBtn:
                Log.i("my_counter", "plusBtnClick()");

                plusBtn = true;
                updateUI();
                break;
        }
    }

    // When buttons are clicked, reset gives start value
    // minus gives decreasing by step value
    // plus increasing by step value
    // All activities above are written inside class Counter
    // then call them inside updateUI

    private void updateUI() {
        TextView tv = findViewById(R.id.textView);

        // Assign result to return value method getValue class Counter
        String result = counter.getValue();

        // Default counter: int result = 0;

        if (resetBtn) {
            result = counter.getResetValue();
            resetBtn = false;
        }
        if (minusBtn) {
            result = counter.getMinusValue();
            minusBtn = false;
        }
        if (plusBtn) {
            result = counter.getPlusValue();
            plusBtn = false;
        }

        tv.setText(result);
    }
}