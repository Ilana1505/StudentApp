package com.example.studentapp.utils

import com.example.studentapp.models.Student

object studentRepository {
    val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun getStudentById(id: String?): Student? {
        return students.find { it.id == id }
    }

    fun deleteStudent(student: Student) {
        students.remove(student)
    }
}