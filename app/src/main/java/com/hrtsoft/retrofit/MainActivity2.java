package com.hrtsoft.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private static final String PREF_NAME = "MyPreferences";
    private static final String EMAIL_KEY = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        // Retrieve data from the intent
        String email = getIntent().getStringExtra("email");

        // Save the email data in SharedPreferences
        saveEmailToSharedPreferences(email);

        // Now 'email' contains the value passed from the previous activity
        // You can use it as needed, for example, set it to a TextView
        TextView textViewEmail = findViewById(R.id.emailTextView);
        textViewEmail.setText(email);
    }

    private void saveEmailToSharedPreferences(String email) {
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(EMAIL_KEY, email);
        editor.apply();
    }

    private String getEmailFromSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        return preferences.getString(EMAIL_KEY, "");
    }
}