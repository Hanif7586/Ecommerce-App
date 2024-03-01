package com.hrtsoft.retrofit

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request

import com.android.volley.Response

import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.HashMap

class RegistrationActivity : AppCompatActivity() {

    private lateinit var ed_username: EditText
    private lateinit var ed_email: EditText
    private lateinit var ed_password: EditText
    private lateinit var str_name: String
    private lateinit var str_email: String
    private lateinit var str_password: String
    private val url = "https://rohanhrtsoft.000webhostapp.com/ecommerce/register.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        ed_email = findViewById(R.id.ed_email)
        ed_username = findViewById(R.id.ed_username)
        ed_password = findViewById(R.id.ed_password)
    }

    fun moveToLogin(view: View) {
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }

    fun Register(view: View) {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please Wait..")

        if (ed_username.text.toString().isEmpty()) {
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show()
        } else if (ed_email.text.toString().isEmpty()) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
        } else if (ed_password.text.toString().isEmpty()) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
        } else {
            progressDialog.show()
            str_name = ed_username.text.toString().trim()
            str_email = ed_email.text.toString().trim()
            str_password = ed_password.text.toString().trim()

            val request = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener<String> { response ->
                    progressDialog.dismiss()
                    ed_username.setText("")
                    ed_email.setText("")
                    ed_password.setText("")
                    Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
                },
                Response.ErrorListener { error ->
                    progressDialog.dismiss()
                    Toast.makeText(this, error.message.toString(), Toast.LENGTH_SHORT).show()
                }
            ) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["name"] = str_name
                    params["email"] = str_email
                    params["password"] = str_password
                    return params
                }
            }

            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(request)
        }
    }
}
