package com.kaanduzbastilar.kotlininstagram.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kaanduzbastilar.kotlininstagram.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private lateinit var auth: FirebaseAuth
private var email : String? = null
private var password : String? = null


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        val currentUser = auth.currentUser

        if (currentUser != null){
            val intent = Intent(this@MainActivity, FeedActivity::class.java)
            startActivity(intent)
        }

    }

    fun signInClicked(view : View){

        email = binding.emailText.text.toString()
        password = binding.passwordText.text.toString()

        if (email.equals("") || password.equals("")){ //password.isNotEmpty() kullanılabilir
            Toast.makeText(this@MainActivity,"Enter Email and Password!",Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email!!, password!!).addOnSuccessListener {
                //success
                val intent = Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()


            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }

    fun signUpClicked(view : View){

        email = binding.emailText.text.toString()
        password = binding.passwordText.text.toString()

        if (email.equals("") || password.equals("")){ //password.isNotEmpty() kullanılabilir
            Toast.makeText(this@MainActivity,"Enter Email and Password!",Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(email!!, password!!).addOnSuccessListener {
                //success
                val intent = Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()


            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

    }

}