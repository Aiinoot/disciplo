package com.example.disciplo.repository

import com.example.disciplo.models.Student

object StudentRepository {
    private val students = mutableListOf(
        Student(
            id = 1,
            name = "Antônio",
            subjects = listOf(
                "Desenvolvimento Web",
                "Práticas de Sistema",
                "Banco de Dados",
                "Desenvolvimento Mobile"
            )
        ),
        Student(
            id = 2,
            name = "João",
            subjects = listOf(
                "Desenvolvimento Web",
                "Banco de Dados"
            )
        )
    )

    private var nextId = 3

    fun addStudent(name: String, subjects: List<String>) {
        val newStudent = Student(id = nextId++, name = name, subjects = subjects)
        students.add(newStudent)
    }

    fun getAll(): List<Student> = students

    fun removeStudent(id: Int) {
        students.removeAll { it.id == id }
    }

    fun updateStudent(id: Int, name: String, subjects: List<String>) {
        val index = students.indexOfFirst { it.id == id }
        if (index != -1) {
            students[index] = students[index].copy(name = name, subjects = subjects)
        }
    }

    fun getById(id: Int): Student? = students.find { it.id == id }
}

