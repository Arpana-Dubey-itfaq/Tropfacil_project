package com.tropfacil.data.repository



import com.example.example.Homeresponse
import com.tropfacil.data.home_response

import com.tropfacil.model.*
import com.tropfacil.model.exercices.ExercicesListResponse
import com.tropfacil.network.BaseResponse
import com.tropfacil.network.request.LoginRequest
import com.tropfacil.network.service.ApiService
import okhttp3.ResponseBody

class AppRepositoryImpl(private val apiService: ApiService) {

    suspend fun login(loginReq: LoginRequest): Login_resoponse =

        apiService.login(loginReq.loginName, loginReq.password)

    suspend fun HomeData(authorization: String?): home_response =

        apiService.homeData(authorization)


    suspend fun register(registerReq: RegisterRequest): Register_response =
        apiService.users(
            registerReq.nom,
            registerReq.prenom,
            registerReq.email,
            registerReq.login,
            registerReq.civilite
        )


    suspend fun forgotPassword(email: String): ForgotPasswordRes =
        apiService.forgotPassword(email)

    suspend fun updatePassword(updatePasswordRequest: UpdatePasswordRequest): BaseResponse =
        apiService.updatePassword(updatePasswordRequest)

    suspend fun updateUser(id:String,nom:String?,prenom:String?): BaseResponse =
        apiService.updateUser(id,nom,prenom)

    suspend fun sendRating(token: String, type: String?, idelement: String?,note: String?): BaseResponse =
        apiService.sendRating(token, type, idelement,note)

    suspend fun getProfilePicture(token:String?): ResponseBody =
        apiService.getProfilePicture(token)

    suspend fun getExercices(token:String?): ExercicesListResponse =
        apiService.getExercices(token)

    suspend fun changeEmail(id: String, email: String, pwd:String): BaseResponse =
        apiService.changeEmail(id,email,pwd)

}

