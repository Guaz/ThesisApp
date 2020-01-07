package com.marcin.wac.thesisapp.models.responses

data class GetUserResponse(val email: String, val name: String, val surname: String,
                           val idNumber: String, val role: String, val university: String,
                           val department: String)