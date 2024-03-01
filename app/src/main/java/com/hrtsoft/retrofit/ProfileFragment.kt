package com.hrtsoft.retrofit

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment


class ProfileFragment : Fragment() {
     lateinit var emailTextView: TextView
     lateinit var sharedPreferences: SharedPreferences

    lateinit var shared: SharedPreferences
    lateinit var saveButton:Button

    lateinit var editTextName: EditText
    lateinit var editTextNumber: EditText
    lateinit var displayText:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)


        editTextName =view.findViewById(R.id.editTextName)
        editTextNumber =view.findViewById(R.id.editTextEmail)
        displayText =view.findViewById(R.id.displayText)
        saveButton=view.findViewById(R.id.saveButton)

        shared = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)


        saveButton.setOnClickListener {
            saveDataH()
        }

        // Display stored data if available
        displayStoredDataH()

        // Find the TextView by its ID
        emailTextView = view.findViewById(R.id.EmailshowID)

        // Initialize SharedPreferences
        sharedPreferences =
            requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        // Retrieve the saved email from SharedPreferences
        val savedEmail = sharedPreferences.getString("saved_email", null)

        // If a saved email is available, display it
        if (!savedEmail.isNullOrBlank()) {
            emailTextView.text = savedEmail
        } else {
            // If no saved email is available, retrieve and display the email from arguments
            val email = arguments?.getString("email")
            emailTextView.text = email

            // Save the email value to SharedPreferences
            saveEmailToSharedPreferences(email)
        }

        return view
    }

    private fun saveEmailToSharedPreferences(email: String?) {
        // Check if email is not blank or null
        if (!email.isNullOrBlank()) {
            // Use an editor to modify SharedPreferences
            val editor = sharedPreferences.edit()

            // Save the email value to SharedPreferences
            editor.putString("saved_email", email)

            // Apply the changes
            editor.apply()
        }
    }

    private fun saveDataH() {
        val name = editTextName.text.toString()
        val email: String = editTextNumber.getText().toString()
        val editor = shared.edit()
        editor.putString("name", name)
        editor.putString("number", email)
        editor.apply()
        editTextName.text.clear()
        editTextNumber.getText().clear()
        displayStoredDataH()
    }
    private fun displayStoredDataH() {
        val storedName = shared.getString("name", "")
        val storedEmail = shared.getString("number", "")
        if (!storedName!!.isEmpty() || !storedEmail!!.isEmpty()) {
            displayText.text = " $storedName\n$storedEmail"
        } else {
            displayText.text = ""
        }
    }

}