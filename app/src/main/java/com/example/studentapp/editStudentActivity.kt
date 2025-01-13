package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.utils.studentRepository

class editStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val studentId = intent.getStringExtra("STUDENT_ID")
        val student = studentRepository.getStudentById(studentId)

        if (student != null) {
            val nameInput = findViewById<EditText>(R.id.nameInput)
            val idInput = findViewById<EditText>(R.id.idInput)
            val phoneInput = findViewById<EditText>(R.id.phoneInput)
            val addressInput = findViewById<EditText>(R.id.addressInput)
            val checkBox = findViewById<CheckBox>(R.id.checkBox)
            val saveButton = findViewById<Button>(R.id.saveButton)
            val deleteButton = findViewById<Button>(R.id.deleteButton)
            val cancelButton = findViewById<Button>(R.id.cancelButton)

            // הצגת המידע הנוכחי
            nameInput.setText(student.name)
            idInput.setText(student.id)
            phoneInput.setText(student.phone)
            addressInput.setText(student.address)
            checkBox.isChecked = student.isChecked

            saveButton.setOnClickListener {
                student.name = nameInput.text.toString()
                student.id = idInput.text.toString()
                student.phone = phoneInput.text.toString()
                student.address = addressInput.text.toString()
                student.isChecked = checkBox.isChecked

                // מעבר למסך הראשי לאחר שמירת השינויים
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }

            deleteButton.setOnClickListener {
                // מחיקת הסטודנט
                studentRepository.deleteStudent(student)

                // מעבר למסך הראשי לאחר מחיקת הסטודנט
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }

            cancelButton.setOnClickListener {
                // אם המשתמש לחץ על cancel, נעבור למסך פרטי הסטודנט
                val intent = Intent(this, studentDetailsActivity::class.java)
                intent.putExtra("STUDENT_ID", student.id)
                startActivity(intent)
                finish()
            }
        }
    }
}
