package com.marcin.wac.thesisapp.models.body

data class ThesisBody(val title: String, val description: String, val language: String,
                      val studentSkills: String, val technologies: String, val promoterEmail: String,
                      val reviewerEmail: String)