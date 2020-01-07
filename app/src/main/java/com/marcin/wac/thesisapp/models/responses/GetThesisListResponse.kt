package com.marcin.wac.thesisapp.models.responses

import com.marcin.wac.thesisapp.models.ThesisModel

data class GetThesisListResponse(val thesisList: List<ThesisModel>)