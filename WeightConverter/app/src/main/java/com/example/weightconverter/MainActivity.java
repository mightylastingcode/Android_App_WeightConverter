//===============================================================================================
//    The MIT License (MIT)
//    Copyright ©2020 Michael Chi Li
//
//    Permission is hereby granted, free of charge, to any person obtaining a copy of
//    this software and associated documentation files (the “Software”),to deal in the Software
//    without restriction, including without limitation the rights to use, copy, modify, merge,
//    publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
//    to whom the Software is furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in all copies
//    or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
//    INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
//    PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
//    FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
//    OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
//    DEALINGS IN THE SOFTWARE.
//===============================================================================================
//    Project Name: Weight Converter
//    Author: Michael C Li
//    Created Date: 10-28-2020
//    Version: First Beta Version
//
//    The app converts the weight input from oz to gram.
//
//    SDK: Android 4.2 (Jelly Bean) API 17
//    Tool: Android Studio 4.0.1
//    Language: Java
//
//    Tested device: Motorola G Power (2010)  OS Android 10.0 (Android Q)
//
//    Step 1: Press the CLEAR button to clear the input box.
//    Step 2: Enter a decimal value (in oz) of the weight (i.e. 2.5)
//    Step 3: Press the CONVERT button to get the decimal value (in gram).  The input box will
//    show "2.5 oz = 70.87 gram"
//
//    Note: If you enter a bad input (i.e. abc), nothing will happen because the converter cannot
//    convert an invalid input.
//===============================================================================================

package com.example.weightconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.weightconverter.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // the name used to refer to widgets in the layout
    private EditText userInput;
    private Button convertButton;
    private Button clearButton;

    private static final float convertRatio = 28.3495f;  // oz to lb

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: in.");

        userInput = (EditText) findViewById(R.id.editText);
        convertButton = (Button) findViewById(R.id.convert);
        clearButton = (Button) findViewById(R.id.clear);

        View.OnClickListener ourOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userText = userInput.getText().toString();
                Log.d(TAG, "onClick convert button: in.");
                Log.d(TAG, "onClick convert button: User Input Text is " + userText);
                try {
                    float val_oz = Float.valueOf(userText);
                    Log.d(TAG, "onClick convert button: User Input weigh is " + val_oz + " oz.");
                    float val_gram = val_oz * convertRatio;
                    val_gram = Math.round(val_gram * 100.0f) / 100.0f;  // 2 decimal points round off
                    Log.d(TAG, "onClick convert button: User Input weigh is " + val_gram + " gram.");
                    userInput.setText(String.valueOf(val_oz) + " oz = " + String.valueOf(val_gram) + " gram");
                } catch (NumberFormatException e) {
                    Log.d(TAG, "onClick convert button: User Input num is not a float number.");
//                    System.out.println("Exception thrown  :" + e);
                }
                Log.d(TAG, "onClick convert button: in.");
            }
        };

        View.OnClickListener clearOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick clear button: in.");
                userInput.setText("");
                Log.d(TAG, "onClick clear button: out.");
            }
        };
        convertButton.setOnClickListener(ourOnClickListener);
        clearButton.setOnClickListener(clearOnClickListener);
        Log.d(TAG, "onCreate: out.");
    }
}