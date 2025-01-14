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

        if (student != null) {  // אם הסטודנט נמצא, נמלא את הפרטים שלו במסך
            findViewById<TextView>(R.id.nameText).text = student.name
            findViewById<TextView>(R.id.idText).text = student.id
            findViewById<TextView>(R.id.phoneText).text = student.phone
            findViewById<TextView>(R.id.addressText).text = student.address
            findViewById<CheckBox>(R.id.detailsCheckBox).isChecked = student.isChecked

            // כפתור עריכה - אם נלחץ, נפתח את מסך העריכה של הסטודנט
            findViewById<Button>(R.id.editButton).setOnClickListener {
                val intent = Intent(this, editStudentActivity::class.java)

                intent.putExtra("STUDENT_ID", student.id)

                startActivity(intent)
            }
        }
    }
}


