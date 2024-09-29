package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.alreadyhaveaccount.setOnClickListener {
            startActivity(Intent(this@MainActivity,LoginActivity::class.java))
            finish()
        }


        binding.registerbtn.setOnClickListener {
            if (binding.emailet.text.toString() == "" || binding.passet.text.toString() == "") {
                Toast.makeText(
                    this@MainActivity,
                    "Please Enter All Info",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Firebase.auth.createUserWithEmailAndPassword(
                    binding.emailet.text.toString(),
                    binding.passet.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this@MainActivity,HomeActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                it.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(Firebase.auth.currentUser!=null){
            startActivity(Intent(this@MainActivity,HomeActivity::class.java))
            finish()
        }
    }


}