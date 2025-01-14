package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.models.Student
import com.example.studentapp.utils.studentRepository

class addStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val idInput = findViewById<EditText>(R.id.idInput)
        val phoneInput = findViewById<EditText>(R.id.phoneInput)
        val addressInput = findViewById<EditText>(R.id.addressInput)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val cancelButton = findViewById<Button>(R.id.deleteButton)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val id = idInput.text.toString().trim()
            val phone = phoneInput.text.toString().trim()
            val address = addressInput.text.toString().trim()
            val isChecked = checkBox.isChecked

            if (name.isNotEmpty() && id.isNotEmpty() && phone.isNotEmpty() && address.isNotEmpty()) {
                // הוספת הסטודנט החדש לרשימה
                studentRepository.addStudent(Student(id, name, phone, address, isChecked))
                // הצגת הודעת הצלחה
                Toast.makeText(this, "Student added successfully!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                // הצגת הודעה אם לא מולאו כל השדות
                Toast.makeText(this, "All fields must be filled!", Toast.LENGTH_SHORT).show()
            }
        }

        cancelButton.setOnClickListener {
            // אם המשתמש לוחץ על ביטול, נעבור למסך הראשי
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}
