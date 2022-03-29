package com.tropfacil.data.repository




import com.example.example.Homeresponse
import com.tropfacil.model.*
import com.tropfacil.model.exercices.ExercicesListResponse
import com.tropfacil.network.BaseResponse
import com.tropfacil.data.home_response

import com.tropfacil.network.request.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody

class AppRepository(private val apiServiceImpl: AppRepositoryImpl) {

    fun register(registerReq: RegisterRequest): Flow<Register_response> = flow {
        emit(apiServiceImpl.register(registerReq))
    }.flowOn(Dispatchers.IO)

    fun forgotPassword(email: String): Flow<ForgotPasswordRes> = flow {
        emit(apiServiceImpl.forgotPassword(email))
    }.flowOn(Dispatchers.IO)

    /* fun login(loginReq: LoginRequest): Flow<LoginRequest> = flow {
        emit(apiServiceImpl.login(loginReq))
    }.flowOn(Dispatchers.IO)
*/
    fun login(loginReq: LoginRequest): Flow<Login_resoponse> = flow {
        emit(apiServiceImpl.login(loginReq))
    }.flowOn(Dispatchers.IO)


        fun HomeData(identifier: String?): Flow<home_response> = flow {
            emit(apiServiceImpl.HomeData(identifier))
        }.flowOn(Dispatchers.IO)

        fun updatePassword(updatePasswordRequest: UpdatePasswordRequest): Flow<BaseResponse> =
            flow {
                emit(apiServiceImpl.updatePassword(updatePasswordRequest))
            }.flowOn(Dispatchers.IO)

        fun updateUser(id: String, nom: String?, prenom: String?): Flow<BaseResponse> = flow {
            emit(apiServiceImpl.updateUser(id, nom, prenom))
        }.flowOn(Dispatchers.IO)


    fun SendRating(token: String, type: String?, idelement: String?,note: String?): Flow<BaseResponse> = flow {
        emit(apiServiceImpl.sendRating(token, type, idelement,note))
    }.flowOn(Dispatchers.IO)

    fun getProfilePicture(token:String?): Flow<ResponseBody> = flow {
        emit(apiServiceImpl.getProfilePicture(token))
    }.flowOn(Dispatchers.IO)

   fun getExercices(token:String?): Flow<ExercicesListResponse> = flow {
        emit(apiServiceImpl.getExercices(token))
    }.flowOn(Dispatchers.IO)

    fun updateEmail(id: String, email: String, pwd:String): Flow<BaseResponse> = flow {
        emit(apiServiceImpl.changeEmail(id,email,pwd))
    }.flowOn(Dispatchers.IO)

}