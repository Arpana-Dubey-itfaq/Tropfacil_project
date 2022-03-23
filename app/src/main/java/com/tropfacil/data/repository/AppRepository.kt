package com.tropfacil.data.repository




import com.example.example.Homeresponse
import com.tropfacil.model.*
import com.tropfacil.network.BaseResponse

import com.tropfacil.network.request.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody

class AppRepository(private val apiServiceImpl: AppRepositoryImpl) {

    fun register(registerReq: RegisterRequest): Flow<RegisterRes> = flow {
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

    fun HomeData(identifier: String?): Flow<Homeresponse> = flow {
        emit(apiServiceImpl.HomeData(identifier))
    }.flowOn(Dispatchers.IO)

    fun updatePassword(updatePasswordRequest: UpdatePasswordRequest): Flow<BaseResponse> = flow {
        emit(apiServiceImpl.updatePassword(updatePasswordRequest))
    }.flowOn(Dispatchers.IO)

    fun updateUser(id:String,nom:String?,prenom:String?): Flow<BaseResponse> = flow {
        emit(apiServiceImpl.updateUser(id,nom,prenom))
    }.flowOn(Dispatchers.IO)

    fun getProfilePicture(token:String?): Flow<ResponseBody> = flow {
        emit(apiServiceImpl.getProfilePicture(token))
    }.flowOn(Dispatchers.IO)

    /*  fun verifyCode(identifier: String, code: String): Flow<RegisterRes> = flow {
          emit(apiServiceImpl.verifyCode(identifier, code))
      }.flowOn(Dispatchers.IO)

      fun resendCode(email: String): Flow<ResendCodeRes> = flow {
          emit(apiServiceImpl.resendCode(email))
      }.flowOn(Dispatchers.IO)

      fun forgotPassword(email: String): Flow<ForgotPasswordRes> = flow {
          emit(apiServiceImpl.forgotPassword(email))
      }.flowOn(Dispatchers.IO)


      fun tokenRefresher(tokenRefresherRequest: TokenRefresherRequest): Flow<TokenRefreshResponse> =
          flow {
              emit(apiServiceImpl.tokenRefresher(tokenRefresherRequest))
          }.flowOn(Dispatchers.IO)

      fun getUserProfile(userId: Int): Flow<GetUserProfileResponse> = flow {
          emit(apiServiceImpl.getUserProfile(userId))
      }.flowOn(Dispatchers.IO)

      fun updateUserProfile(updateProfileRequest: UpdateProfileRequest): Flow<BaseResponse> = flow {
          emit(apiServiceImpl.updateUserProfile(updateProfileRequest))
      }.flowOn(Dispatchers.IO)



      fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Flow<BaseResponse> = flow {
          emit(apiServiceImpl.resetPassword(resetPasswordRequest))
      }.flowOn(Dispatchers.IO)


      fun getBanners(bannerType: Int, bannerTypePlatform: Int): Flow<BannersResponse> = flow {
          emit(
              apiServiceImpl.getBanners(
                  bannerType = bannerType,
                  bannerTypePlatform = bannerTypePlatform
              )
          )
      }.flowOn(Dispatchers.IO)


      fun getProductsBrand(): Flow<ProductsBrandResponse> = flow {
          emit(apiServiceImpl.getProductsBrand())
      }.flowOn(Dispatchers.IO)

      fun getBrands(): Flow<BrandsResponse> = flow {
          emit(apiServiceImpl.getBrands())
      }.flowOn(Dispatchers.IO)

      fun getCategory(): Flow<CategoryResponse> = flow {
          emit(apiServiceImpl.getCategory())
      }.flowOn(Dispatchers.IO)

      fun getFoodHalls(): Flow<FoodHallResponse> = flow {
          emit(apiServiceImpl.getFoodHalls())
      }.flowOn(Dispatchers.IO)

      fun getRestaurantProfile(brandId: Int): Flow<RestaurantProfileResponse> = flow {
          emit(apiServiceImpl.getRestaurantProfile(brandId = 5))
      }.flowOn(Dispatchers.IO)

      fun getProductDetails(brandId: Int): Flow<ProductDetailsResponse> = flow {
          emit(apiServiceImpl.getProductDetails(brandId = brandId))
      }.flowOn(Dispatchers.IO)

      //cart and payment related
      fun getProductsFromBasket(identifier: String): Flow<GetProductsFromBasketResponse> = flow {
          emit(apiServiceImpl.getProductsFromBasket(identifier))
      }.flowOn(Dispatchers.IO)

      fun getSpecificProduct(orderDetailId: Int): Flow<GetSpecificProductResponse> = flow {
          emit(apiServiceImpl.getSpecificProduct(orderDetailId))
      }.flowOn(Dispatchers.IO)

      fun addProductToCart(addToCartRequest: AddToCartRequest): Flow<AddAndUpdateToCartResponse> =
          flow {
              emit(apiServiceImpl.addProductToCart(addToCartRequest))
          }.flowOn(Dispatchers.IO)

      fun filterAPI(filterAPIRequest: FilterAPIRequest): Flow<ProductsBrandResponse> =
          flow {
              emit(apiServiceImpl.filterAPI(filterAPIRequest))
          }.flowOn(Dispatchers.IO)

      fun updateProduct(addToCartRequest: AddToCartRequest): Flow<AddAndUpdateToCartResponse> = flow {
          emit(apiServiceImpl.updateProduct(addToCartRequest))
      }.flowOn(Dispatchers.IO)

      fun updateProductQty(orderDetailId: Int, qty: Int): Flow<BaseResponse> = flow {
          emit(apiServiceImpl.updateProductQty(orderDetailId, qty))
      }.flowOn(Dispatchers.IO)

      fun deleteProduct(orderDetailId: Int): Flow<BaseResponse> = flow {
          emit(apiServiceImpl.deleteProduct(orderDetailId))
      }.flowOn(Dispatchers.IO)

      fun checkPromoCodeValid(identifier: String, promoCode: String): Flow<PromoCodeResponse> = flow {
          emit(apiServiceImpl.checkPromoCodeValid(identifier, promoCode))
      }.flowOn(Dispatchers.IO)

      fun syncGuestItems(identifier: String): Flow<BaseResponse> = flow {
          emit(apiServiceImpl.syncGuestItems(identifier))
      }.flowOn(Dispatchers.IO)

      fun postOrder(orderRequest: OrderRequest): Flow<OrderResponse> = flow {
          emit(apiServiceImpl.postOrder(orderRequest))
      }.flowOn(Dispatchers.IO)

      fun updateOrderStatus(orderId: Int, orderStatus: Int): Flow<BaseResponse> = flow {
          emit(apiServiceImpl.updateOrderStatus(orderId, orderStatus))
      }.flowOn(Dispatchers.IO)

      fun grubTechOrder(orderId: Int): Flow<BaseResponse> = flow {
          emit(apiServiceImpl.grubTechOrder(orderId))
      }.flowOn(Dispatchers.IO)

      fun getOrderList(): Flow<OrderListResponse> = flow {
          emit(apiServiceImpl.getOrderList())
      }.flowOn(Dispatchers.IO)

      fun getOrderCompletedDetails(orderId: Int): Flow<OrderCompletedResponse> = flow {
          emit(apiServiceImpl.getOrderCompletedDetails(orderId))
      }.flowOn(Dispatchers.IO)


      fun facebookLogin(facebookLoginRequest: FacebookLoginRequest): Flow<LoginRes> = flow {
          emit(apiServiceImpl.facebookLogin(facebookLoginRequest))
      }.flowOn(Dispatchers.IO)

      fun googleLogin(googleLoginRequest: FacebookLoginRequest): Flow<LoginRes> = flow {
          emit(apiServiceImpl.googleLogin(googleLoginRequest))
      }.flowOn(Dispatchers.IO)

      fun searchByKeyword(keyword: MutableMap<String, String>): Flow<SearchResponse> = flow {
          emit(apiServiceImpl.searchByKeyword(keyword))
      }.flowOn(Dispatchers.IO)

      fun reOrder(orderId: Int,identifier: String): Flow<BaseResponse> = flow {
          emit(apiServiceImpl.reOrder(orderId,identifier))
      }.flowOn(Dispatchers.IO)

      fun payWithCard(payWithCardRequest: PayWithCardRequest): Flow<PayWithCardResponse> = flow {
          emit(apiServiceImpl.payWithCard(payWithCardRequest))
      }.flowOn(Dispatchers.IO)*/
}