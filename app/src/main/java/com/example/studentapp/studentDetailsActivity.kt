package com.example.studentapp

import android.os.Bundle
import android.content.Intent
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.utils.studentRepository

class studentDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val studentId = intent.getStringExtra("STUDENT_ID")
        val student = studentRepository.getStudentById(studentId)

        student?.let {
            // הצגת פרטי הסטודנט
            findViewById<TextView>(R.id.nameText).text = it.name
            findViewById<TextView>(R.id.idText).text = it.id
            findViewById<TextView>(R.id.phoneText).text = it.phone
            findViewById<TextView>(R.id.addressText).text = it.address
            findViewById<CheckBox>(R.id.detailsCheckBox).isChecked = it.isChecked

            // הגדרת כפתור לעריכת הסטודנט
            findViewById<Button>(R.id.editButton).setOnClickListener {
                val intent = Intent(this, editStudentActivity::class.java).apply {
                    putExtra("STUDENT_ID", it.id)
                }
                startActivity(intent)
            }
        }
    }
}
