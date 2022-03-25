package com.tropfacil.network.service


import com.example.example.Homeresponse

import com.tropfacil.BuildConfig
import com.tropfacil.data.home_response
import com.tropfacil.model.*
import kotlinx.coroutines.Deferred
import com.tropfacil.model.ForgotPasswordRes
import com.tropfacil.model.Login_resoponse
import com.tropfacil.model.RegisterRes
import com.tropfacil.model.UpdatePasswordRequest
import com.tropfacil.network.BaseResponse
import okhttp3.ResponseBody
import retrofit2.http.*


interface ApiService {


    @Headers("Accept: application/json")
    @GET("get-catalogue")
    suspend fun listUsers(
        @Query("token") token: String,
    ): List<Homeresponse>

    @POST("utilisateur/login")
    suspend fun login(
        @Query("login") login: String?,
        @Query("pwd") token: String?,
    ): Login_resoponse

    @POST("boutique/utilisateur")
    suspend fun users(
        @Query("nom") nom: String?, @Query("prenom") prenom: String?,
        @Query("email") email: String?, @Query("civilite") civilite: String?,
        @Query("login") login: String?,

        ): Register_response

    @POST("utilisateur/envoyer-code-unique")
    suspend fun forgotPassword(
        @Query("login")
        email: String,
    ): ForgotPasswordRes

    @POST("update-password")
    suspend fun updatePassword(
        @Body updatePasswordRequest: UpdatePasswordRequest,
    ): BaseResponse

    @POST("catalogue/get-catalogue")
    suspend fun homeData(
        @Query("token") authorization: String?,
    ): home_response

    @POST("catalogue/get-modules")
    suspend fun courseData(
        @Query("token") authorization: String?,
    ): Homeresponse


    @POST("utilisateur/update-carte-de-visite")
    suspend fun changeEmail(
        @Query("nom") nom: String?, @Query("prenom") prenom: String?,
        @Query("login") login: String?, @Query("civilite") civilite: String?,
    ): BaseResponse

    @POST("utilisateur/update")
    suspend fun updateUser(
        @Query("id") id: String?, @Query("nom") nom: String?,
        @Query("prenom") prenom: String?,
    ): BaseResponse
    @POST("catalogue/set-note-cours")
    suspend fun sendRating(
        @Query("token") nom: String?, @Query("type") prenom: String?,
        @Query("idelement") login: String?, @Query("note") civilite: String?,
    ): BaseResponse
    @Streaming
    @GET("utilisateur/get-photo-profil")
    suspend fun getProfilePicture(
        @Query("token") token: String?,
    ): ResponseBody
    /*
   *//*   @POST("Authentication/SignIn/Customer")
    suspend fun login(@Body loginReq: LoginReq): LoginRes
*//*
    @POST("Otp/User/Verify/{identifier}/{code}")
    suspend fun verifyCode(
        @Path("identifier") identifier: String,
        @Path("code") code: String
    ): RegisterRes

    @GET("Otp/User/Regenerate/{email}")
    suspend fun resendCode(
        @Path("email")
        email: String
    ): ResendCodeRes

    @GET("Authentication/ForgotPassword/{email}")
    suspend fun forgotPassword(
        @Path("email")
        email: String
    ): ForgotPasswordRes

    @POST("Authentication/SignIn/TokenRefresher")
    suspend fun tokenRefresher(@Body tokenRefresherRequest: TokenRefresherRequest): TokenRefreshResponse

    @GET("Users/{userId}")
    suspend fun getUserProfile(
        @Path("userId")
        userId: Int
    ): GetUserProfileResponse

    @PUT("Users")
    suspend fun updateUserProfile(@Body updateProfileRequest: UpdateProfileRequest): BaseResponse

    @POST("Users/ChangePassword")
    suspend fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): BaseResponse

    @POST("Authentication/ForgotPassword")
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): BaseResponse

    @GET("Banners/{bannerType}/{bannerTypePlatform}")
    suspend fun getBanners(
        @Path("bannerType") bannerType: Int,
        @Path("bannerTypePlatform")
        bannerTypePlatform: Int
    ): BannersResponse

    @GET("Products/Brand")
    suspend fun getProductsBrand(): ProductsBrandResponse

    @GET("Brands")
    suspend fun getBrands(): BrandsResponse

    @GET("Category")
    suspend fun getCategory(): CategoryResponse

    @GET("FoodHalls")
    suspend fun getFoodHalls(): FoodHallResponse

    @GET("Products/{brandId}/Category")
    suspend fun getRestaurantProfile(
        @Path("brandId")
        bannerTypePlatform: Int
    ): RestaurantProfileResponse

    @GET("Products/{productId}")
    suspend fun getProductDetails(
        @Path("productId")
        bannerTypePlatform: Int
    ): ProductDetailsResponse

    @GET("Orders/Cart/{identifier}")
    suspend fun getProductsFromBasket(
        @Path("identifier")
        identifier: String
    ): GetProductsFromBasketResponse

    @GET("Orders/Cart/{orderDetailId}/Product")
    suspend fun getSpecificProduct(
        @Path("orderDetailId")
        orderDetailId: Int
    ): GetSpecificProductResponse

    @POST("Orders/Cart")
    suspend fun addProductToCart(@Body addToCartRequest: AddToCartRequest): AddAndUpdateToCartResponse

    @POST("Products/AdvancedSearch")
    suspend fun filterAPI(@Body filterAPIRequest: FilterAPIRequest): ProductsBrandResponse

    @PUT("Orders/Cart")
    suspend fun updateProduct(@Body addToCartRequest: AddToCartRequest): AddAndUpdateToCartResponse

    @PUT("Orders/Cart/{orderDetailId}/{qty}")
    suspend fun updateProductQty(
        @Path("orderDetailId")
        orderDetailId: Int, @Path("qty")
        qty: Int
    ): BaseResponse

    @DELETE("Orders/Cart/{orderDetailId}")
    suspend fun deleteProduct(
        @Path("orderDetailId")
        orderDetailId: Int
    ): BaseResponse

    @GET("Orders/Promo/{identifier}/{promoCode}/Check")
    suspend fun checkPromoCodeValid(
        @Path("identifier")
        identifier: String, @Path("promoCode")
        promoCode: String
    ): PromoCodeResponse


    *//*@POST("Orders/Pay/WithCard")
    suspend fun payWithCard(@Body payWithCardRequest: PayWithCardRequest): BaseResponse*//*

    @POST("Orders/Cart/{identifier}/Sync")
    suspend fun syncGuestItems(@Path("identifier") identifier: String): BaseResponse

    @POST("Orders")
    suspend fun postOrder(@Body orderRequest: OrderRequest): OrderResponse

    @PUT("Orders/{orderId}/{orderStatus}/Status")
    suspend fun putOrder(
        @Path("orderId") orderId: Int,
        @Path("orderStatus") orderStatus: Int
    ): BaseResponse

    @POST("GrubTech/{orderId}")
    suspend fun grubTechOrder(
        @Path("orderId") orderId: Int
    ): BaseResponse

    @GET("Orders")
    suspend fun getOrderList(): OrderListResponse


    @GET("Orders/{orderId}")
    suspend fun getOrderCompletedDetails(@Path("orderId") orderId: Int): OrderCompletedResponse

    @GET("Products/SearchByKeyword")
    suspend fun searchByKeyword(
        @QueryMap keyword: MutableMap<String, String>
    ): SearchResponse


    @POST("Authentication/SignIn/Customer/Facebook")
    suspend fun facebookLogin(@Body facebookLoginRequest: FacebookLoginRequest): LoginRes

    @POST("Authentication/SignIn/Customer/Google")
    suspend fun googleLogin(@Body googleLoginRequest: FacebookLoginRequest): LoginRes

    @POST("Orders/{orderId}/{identifier}/ReOrder")
    suspend fun reOrder(
        @Path("orderId") orderId: Int,
        @Path("identifier") identifier: String
    ): BaseResponse

    @POST("Orders/Pay/WithCard")
    suspend fun payWithCard(@Body payWithCardRequest: PayWithCardRequest): PayWithCardResponse

    companion object {

        fun create(): ApiService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()


            httpClient.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logger).build()

            return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(ApiService::class.java)

        }
    }*/

    /* companion object {

         fun create(): ApiService {
             val logger = HttpLoggingInterceptor()
             logger.level = HttpLoggingInterceptor.Level.BODY
             val httpClient = OkHttpClient.Builder()


             httpClient.readTimeout(60, TimeUnit.SECONDS)
                 .connectTimeout(60, TimeUnit.SECONDS)
                 .addInterceptor(logger).build()

             return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(httpClient.build())
                 .addConverterFactory(GsonConverterFactory.create()).build()
                 .create(ApiService::class.java)

         }
     }*/
}