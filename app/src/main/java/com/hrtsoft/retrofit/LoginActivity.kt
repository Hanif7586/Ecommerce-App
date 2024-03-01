package com.hrtsoft.retrofit

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.HashMap

class LoginActivity : AppCompatActivity() {

    private lateinit var ed_email: EditText
    private lateinit var ed_password: EditText
    private lateinit var str_email: String
    private lateinit var str_password: String
    private val url = "https://rohanhrtsoft.000webhostapp.com/ecommerce/login.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ed_email = findViewById(R.id.ed_email)
        ed_password = findViewById(R.id.ed_password)

        // Check the login state
        if (isUserLoggedIn()) {
            // User is already logged in, navigate to ProfileActivity
            navigateToProfile()
        }
    }

    // Method to check if the user is logged in (e.g., based on a saved token or flag)
    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences: SharedPreferences = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    // Method to navigate to the ProfileActivity
    private fun navigateToProfile() {
        val profileIntent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(profileIntent)
        finish() // Optional: Finish LoginActivity to prevent going back to it
    }

    fun Login(view: View) {
        if (ed_email.text.toString().isEmpty()) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
        } else if (ed_password.text.toString().isEmpty()) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
        } else {
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Please Wait..")
            progressDialog.show()

            str_email = ed_email.text.toString().trim()
            str_password = ed_password.text.toString().trim()

            val request = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener { response ->
                    progressDialog.dismiss()

                    if (response.equals("logged in successfully", ignoreCase = true)) {
                        // Clear input fields
                        ed_email.setText("")
                        ed_password.setText("")

                        // Save the login state
                        saveLoginState(true)

                        // Create an Intent to start the MainActivity
                        val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)

                        // Pass the email and password as extras to the MainActivity
                        mainIntent.putExtra("email", str_email)
                        mainIntent.putExtra("password", str_password)

                        // Start the MainActivity
                        startActivity(mainIntent)

                        // Show a success toast message
                        Toast.makeText(this@LoginActivity, response, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@LoginActivity, response, Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener { error ->
                    progressDialog.dismiss()
                    Toast.makeText(this@LoginActivity, error.message.toString(), Toast.LENGTH_SHORT).show()
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["email"] = str_email
                    params["password"] = str_password
                    return params
                }
            }

            val requestQueue: RequestQueue = Volley.newRequestQueue(this)
            requestQueue.add(request)
        }
    }

    // Method to save the login state
    private fun saveLoginState(isLoggedIn: Boolean) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
    }

    fun moveToRegistration(view: View) {
        startActivity(Intent(applicationContext, RegistrationActivity::class.java))
        finish()
    }
}
