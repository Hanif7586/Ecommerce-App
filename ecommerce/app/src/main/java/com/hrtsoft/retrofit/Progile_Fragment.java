package com.hrtsoft.retrofit;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Progile_Fragment extends Fragment {

   View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_progile_, container, false);

        // Retrieve the email and password values from the arguments
        Bundle args = getArguments();
        if (args != null) {
            String email = args.getString("email");
            String password = args.getString("password");

            // Save the email and password in SharedPreferences or perform any other actions
            saveData(email, password);
            // Now, you can use 'email' and 'password' as needed in your Fragment
            // For example, display them in TextViews or other UI elements
            TextView txtEmail = view.findViewById(R.id.emailTextView); // Replace with your actual TextView ID
            TextView txtPassword = view.findViewById(R.id.passwordTextView); // Replace with your actual TextView ID

            txtEmail.setText("Email: " + email);
            txtPassword.setText("Password: " + password);
        }
        retrieveData();
        return view;
    }

    private void saveData(String email, String password) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("email", email);
        editor.putString("password", password);

        editor.apply();
    }

    private void retrieveData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String savedEmail = preferences.getString("email", "");
        String savedPassword = preferences.getString("password", "");

        // Use the retrieved email and password as needed
        // For example, display them in TextViews
        TextView txtEmail =view.findViewById(R.id.emailTextView); // Replace with your actual TextView ID
        TextView txtPassword =view.findViewById(R.id.passwordTextView); // Replace with your actual TextView ID

        txtEmail.setText("Email: " + savedEmail);
        txtPassword.setText("Saved Password: " + savedPassword);
    }


}