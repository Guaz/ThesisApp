package com.marcin.wac.thesisapp.models.body

data class RegisterBody(val email: String, val password: String, val name: String, val surname: String,
                        val idNumber: String, val role: String, val university: String, val department: String)