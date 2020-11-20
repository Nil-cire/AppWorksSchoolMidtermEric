package com.eric.firebaseexample

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import com.eric.firebaseexample.databinding.ActivityMainBinding
import com.google.android.gms.common.util.DataUtils
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = androidx.databinding.DataBindingUtil.setContentView(this, R.layout.activity_main)


        val sample = hashMapOf<String, Any?>(
            "Family" to "Some",
            "Gender" to "Male",
            "Uni" to "NTNU"
        )

        val db = FirebaseFirestore.getInstance()
//        db.collection("User").document("Sample").set(sample)

        val User = db.collection("User")

//        val btn = findViewById<TextView>(R.id.add)

//        binding.add.setOnClickListener {
//            User.add(sample)
//                .addOnSuccessListener {it ->
//                    it.update("id", it.id)
//                    Log.i("add success", "ya")
//                } .addOnFailureListener {e ->
//                    Log.w("Error adding document", "Error adding document", e)
//                }
//        }



    }


}