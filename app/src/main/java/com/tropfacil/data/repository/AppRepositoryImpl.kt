package com.tropfacil.data.repository



import com.example.example.Homeresponse

import com.tropfacil.model.*
import com.tropfacil.network.request.LoginRequest
import com.tropfacil.network.service.ApiService

class AppRepositoryImpl(private val apiService: ApiService) {

    suspend fun login(loginReq: LoginRequest): Login_resoponse =

        apiService.login(loginReq.token, loginReq.loginName, loginReq.password)

    suspend fun HomeData(header: String?,authorization: String?): Homeresponse =

        apiService.homeData(header,authorization)


    suspend fun register(registerReq: RegisterRequest): RegisterRes =
        apiService.users(
            registerReq.nom,
            registerReq.prenom,
            registerReq.email,
            registerReq.login,
            registerReq.civilite,
            registerReq.token
        )


    suspend fun forgotPassword(email: String): ForgotPasswordRes =
        apiService.forgotPassword("v6yRZ5gsSPY0dS9imbUUySYuTdPGn5Wo",email)
}
/*  suspend fun verifyCode(identifier: String, code: String): RegisterRes =
    apiService.verifyCode(identifier, code)

suspend fun resendCode(email: String): ResendCodeRes =
    apiService.resendCode(email)

suspend fun forgotPassword(email: String): ForgotPasswordRes =
    apiService.forgotPassword(email)


suspend fun tokenRefresher(tokenRefresherRequest: TokenRefresherRequest): TokenRefreshResponse =
    apiService.tokenRefresher(tokenRefresherRequest)

suspend fun getUserProfile(userId: Int): GetUserProfileResponse =
    apiService.getUserProfile(userId)

suspend fun updateUserProfile(updateProfileRequest: UpdateProfileRequest): BaseResponse =
    apiService.updateUserProfile(updateProfileRequest)

suspend fun changePassword(changePasswordRequest: ChangePasswordRequest): BaseResponse =
    apiService.changePassword(changePasswordRequest)

suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): BaseResponse =
    apiService.resetPassword(resetPasswordRequest)

suspend fun getBanners(bannerType: Int, bannerTypePlatform: Int): BannersResponse =
    apiService.getBanners(bannerType = bannerType, bannerTypePlatform = bannerTypePlatform)

suspend fun getProductsBrand(): ProductsBrandResponse = apiService.getProductsBrand()
suspend fun getBrands(): BrandsResponse = apiService.getBrands()
suspend fun getCategory(): CategoryResponse = apiService.getCategory()
suspend fun getFoodHalls(): FoodHallResponse = apiService.getFoodHalls()

suspend fun getRestaurantProfile(brandId: Int): RestaurantProfileResponse =
    apiService.getRestaurantProfile(brandId)

suspend fun getProductDetails(brandId: Int): ProductDetailsResponse =
    apiService.getProductDetails(brandId)

//Cart and payment related
suspend fun getProductsFromBasket(identifier: String): GetProductsFromBasketResponse =
    apiService.getProductsFromBasket(identifier)

suspend fun getSpecificProduct(orderDetailId: Int): GetSpecificProductResponse =
    apiService.getSpecificProduct(orderDetailId)

suspend fun addProductToCart(addToCartRequest: AddToCartRequest): AddAndUpdateToCartResponse =
    apiService.addProductToCart(addToCartRequest)

suspend fun filterAPI(filterAPIRequest: FilterAPIRequest): ProductsBrandResponse =
    apiService.filterAPI(filterAPIRequest)

suspend fun updateProduct(addToCartRequest: AddToCartRequest): AddAndUpdateToCartResponse =
    apiService.updateProduct(addToCartRequest)

suspend fun updateProductQty(orderDetailId: Int, qty: Int): BaseResponse =
    apiService.updateProductQty(orderDetailId, qty)

suspend fun deleteProduct(orderDetailId: Int): BaseResponse =
    apiService.deleteProduct(orderDetailId)

suspend fun checkPromoCodeValid(identifier: String, promoCode: String): PromoCodeResponse =
    apiService.checkPromoCodeValid(identifier, promoCode)

suspend fun postOrder(orderRequest: OrderRequest): OrderResponse =
    apiService.postOrder(orderRequest)

suspend fun updateOrderStatus(orderId: Int, orderStatus: Int): BaseResponse =
    apiService.putOrder(orderId, orderStatus)

suspend fun grubTechOrder(orderId: Int): BaseResponse =
    apiService.grubTechOrder(orderId)

suspend fun syncGuestItems(identifier: String): BaseResponse =
    apiService.syncGuestItems(identifier)

suspend fun getOrderList(): OrderListResponse = apiService.getOrderList()

suspend fun getOrderCompletedDetails(orderId: Int): OrderCompletedResponse =
    apiService.getOrderCompletedDetails(orderId)

suspend fun searchByKeyword(keyword: MutableMap<String, String>): SearchResponse =
    apiService.searchByKeyword(keyword)

suspend fun facebookLogin(facebookLoginRequest: FacebookLoginRequest): LoginRes =
    apiService.facebookLogin(facebookLoginRequest)

suspend fun googleLogin(googleLoginRequest: FacebookLoginRequest): LoginRes =
    apiService.googleLogin(googleLoginRequest)

suspend fun reOrder(orderId: Int, identifier: String): BaseResponse =
    apiService.reOrder(orderId, identifier)

suspend fun payWithCard(payWithCardRequest: PayWithCardRequest): PayWithCardResponse =
    apiService.payWithCard(payWithCardRequest)
*/
