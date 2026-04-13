package com.example.userapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.userapp.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    private lateinit var viewModel: NoteViewModel

    private var noteid = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]


        noteid =intent.getIntExtra("id",-1)

        if (noteid!=-1){

            binding.nameET.setText(intent.getStringExtra("name"))
            binding.addressET.setText(intent.getStringExtra("address"))
            binding.phoneET.setText(intent.getStringExtra("phone"))


        }

        binding.button.setOnClickListener {
            val name = binding.nameET.text.toString()
            val address = binding.addressET.text.toString()
            val phone =binding.phoneET.text.toString()
//




            if (noteid== -1){
                //insert
                viewModel.insertFromViewModel(
                    Note(name = name, address = address, phone = phone)
                )

            }else{
                //Update
                viewModel.updateFromViewModel(
                    Note(id = noteid, name = name, address = address, phone = phone)
                )
            }
            Toast.makeText(this@AddActivity, "data saved successfully", Toast.LENGTH_SHORT).show()
            finish()


        }


    }
}