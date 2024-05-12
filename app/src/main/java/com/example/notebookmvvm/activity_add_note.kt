package com.example.notebookmvvm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notebookmvvm.Models.Note
import com.example.notebookmvvm.databinding.ActivityAddNoteBinding
import java.text.SimpleDateFormat
import java.util.Date

class activity_add_note : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding


    private lateinit var note : Note
    private lateinit var old_note : Note
    var isUpdate = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)




        try {
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.edtTitle.setText(old_note.title)
            binding.edtMessage.setText(old_note.note)
            isUpdate = true

        }catch (e : Exception){
            e.printStackTrace()
        }
        binding.checkBtn.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val note_desc  = binding.edtMessage.text.toString()

            if (title.isNotEmpty() || note_desc.isNotEmpty()){
                val formatter = SimpleDateFormat()

                if (isUpdate){
                    note = Note(
                        old_note.id , title , note_desc , formatter.format(Date())
                    )
                }else{
                    note = Note(
                        null , title ,note_desc , formatter.format(Date())
                    )

                }

                val intent = Intent()
                intent.putExtra("note" , note)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
            else{
                Toast.makeText(this, "plesse enter some data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }
}