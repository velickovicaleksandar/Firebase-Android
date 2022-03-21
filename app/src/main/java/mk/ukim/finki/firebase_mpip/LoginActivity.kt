package mk.ukim.finki.firebase_mpip

import android.R.attr
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.android.gms.tasks.OnCompleteListener

import android.R.attr.password
import android.R.attr.password


class LoginActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email: EditText = findViewById(R.id.loginEmail)
        val password: EditText = findViewById(R.id.loginPassword)
        val loginButton: Button = findViewById(R.id.loginBtn);
        val registerButton: Button = findViewById(R.id.loginRegister);
        loginButton.setOnClickListener {
            val emailVal = email.text.toString();
            val pwVal = password.text.toString();
            if (emailVal.isNullOrEmpty() || pwVal.isNullOrEmpty()){
                Toast.makeText(this, "You must enter your email and password", Toast.LENGTH_LONG)
                    .show()}
            else
            {login(emailVal, pwVal)}
        }
        registerButton.setOnClickListener {
            val emailVal = email.text.toString();
            val pwVal = password.text.toString();
            if (emailVal.isNullOrEmpty() || pwVal.isNullOrEmpty())
            {  Toast.makeText(this, "You must enter your email and password", Toast.LENGTH_LONG)
                    .show()
                    }
            else
            {    register(emailVal, pwVal)}
        }
    }

    private fun register(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, attr.password.toString())
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(
                        this, "Registration successful",
                        Toast.LENGTH_SHORT
                    ).show();


                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        this, "Registration failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }

                // ...
            }
    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, attr.password.toString())
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    navigateOut()
                    val user = mAuth.currentUser
                    Toast.makeText(
                        this, "You are logged in successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }

                // ...
            }
    }


    override fun onStart() {
        super.onStart()
        if (checkIfLoggedIn()){
            navigateOut()}
    }

    private fun checkIfLoggedIn(): Boolean {
        return mAuth.currentUser != null
    }

    private fun navigateOut() {
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}