package com.example.vu.android;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import io.sentry.core.Sentry;

public class MainActivity extends AppCompatActivity {
    TextView total;
    EditText numerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DIVIDE BY ZERO
        Button div_by_zero_button = (Button)findViewById(R.id.div_zero);
        div_by_zero_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                int t = 5 / 0 ;
            }
        });

        // NEGATIVE INDEX
        Button negative_index_button = (Button)findViewById(R.id.negative_index);
        negative_index_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                int[] a = new int[-5];
            }
        });

        // HANDLED EXCEPTION
        Button handled_exception_button = (Button)findViewById(R.id.handled_exception);
        handled_exception_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                try {
                    Integer.parseInt ("str");
                } catch (Exception e) {
                    Sentry.captureException(e);
                }

            }
        });

        // ANR
        final Button anr_button = (Button)findViewById(R.id.anr);
        anr_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){

                while(true) {
                    // Wait 5 seconds for ANR....
                }
            }
        });

        // NATIVE CRASH
        findViewById(R.id.ndk_crash).setOnClickListener(view -> NativeSample.crash());

        // HANDLED NATIVE CRASH
        findViewById(R.id.ndk_handled_crash).setOnClickListener(view -> NativeSample.handledCrash());
    }
}
