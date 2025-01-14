package com.example.studentapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.adapters.StudentAdapter
import com.example.studentapp.utils.studentRepository

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // יצירת האדפטר עם רשימת הסטודנטים והפונקציה שתרוץ כאשר ייבחר סטודנט
        studentAdapter = StudentAdapter(studentRepository.students) { student ->
            val intent = Intent(this, studentDetailsActivity::class.java)
            intent.putExtra("STUDENT_ID", student.id) // מעביר את מזהה הסטודנט
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = studentAdapter

        findViewById<View>(R.id.addStudentButton).setOnClickListener {
            val intent = Intent(this, addStudentActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()

        studentAdapter.notifyDataSetChanged()
    }
}

