package com.marcin.wac.thesisapp.models.responses

import com.marcin.wac.thesisapp.models.UserModel

data class GetUserListResponse(val userList: List<UserModel>)