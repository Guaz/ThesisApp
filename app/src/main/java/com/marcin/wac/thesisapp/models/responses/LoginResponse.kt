package com.marcin.wac.thesisapp.models.responses

data class LoginResponse(val email: String,
                         val token: String,
                         val authenticated: Boolean)