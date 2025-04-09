package com.example.disciplo.navigation

sealed class Routes(val route: String) {
    object MenuActivity : Routes("menu")
    object StudentsListActivity : Routes("students_list")
    object StudentsFormActivity : Routes("students_form") {
        fun withId(id: Int) = "students_form/$id"
    }
    object SubjectListActivity : Routes("subject_list")
    object SubjectFormActivity : Routes("subject_form") {
        fun withName(name: String) = "subject_form/$name"
    }
}