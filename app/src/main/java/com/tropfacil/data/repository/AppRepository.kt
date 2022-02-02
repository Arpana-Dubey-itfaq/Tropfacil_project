package com.tropfacil.data.repository




import com.tropfacil.network.BaseResponse
import com.tropfacil.network.auth.account.RefreshTokenResponse
import com.tropfacil.network.request.LogoutRequest
import com.tropfacil.network.request.RegenerateTokenRequest
import com.tropfacil.network.service.SafeApiCall
import okhttp3.RequestBody

interface AppRepository {
    //Registration calls
   // suspend fun fullUserRegistrationAsync(registerRequest: RegisterRequest): SafeApiCall<AllRegisterResponse>
   // suspend fun verifyCode(verifyCodeRequest: VerifyCodeRequest): SafeApiCall<BaseResponse>

    //Login Calls
   // suspend fun loginUser(loginRequest: LoginRequest): SafeApiCall<LoginResponse>
   // suspend fun setFCMToken(fcmToken: String, deviceType: Int): SafeApiCall<BaseResponse>
    suspend fun reGenerateTokensAsync(regenerateTokenRequest: RegenerateTokenRequest): SafeApiCall<RefreshTokenResponse>
    suspend fun logoutAsync(logoutRequest: LogoutRequest): SafeApiCall<BaseResponse>


    //Forget Password calls
   // suspend fun forgetPassword(forgetPasswordRequest: ForgetPasswordRequest): SafeApiCall<BaseResponse>
 //   suspend fun resetPasswordAsync(resetPasswordRequest: ResetPasswordRequest): SafeApiCall<BaseResponse>

    //User related calls, get, update profile, change, upload pic
    //suspend fun getUserProfileAsync(): SafeApiCall<UserProfileResponse>
   // suspend fun editUserProfileAsync(editUserProfileRequest: EditUserProfileRequest): SafeApiCall<UpdateProfileResponse>
   // suspend fun uploadProfilePicAsync(files: RequestBody): SafeApiCall<UploadImageResponse>
    //suspend fun changePasswordAsync(changePasswordRequest: ChangePasswordRequest): SafeApiCall<BaseResponse>

   /// suspend fun uploadPrescriptionAsync(files: RequestBody): SafeApiCall<UploadImageResponse>
    //suspend fun uploadDocumentAsync(files:  RequestBody): SafeApiCall<UploadImageResponse>

    //resend verification code calls
 /*   suspend fun resendVerificationCodeAsync(resendVerificationCodeRequest: ResendVerificationCodeRequest): SafeApiCall<BaseResponse>
    suspend fun nonAccountResendVerificationCodeAsync(resendVerificationCodeRequest: NonAccountResendVerificationCodeRequest): SafeApiCall<BaseResponse>

    //regular user home page calls
    suspend fun catalogueForRegularCustomerAsync(): SafeApiCall<CatalogueResponse>

    //Address related calls
    suspend fun getDeliveryAddressBookAsync(addressId: Int): SafeApiCall<AddressListResponse>
    suspend fun getSelectedDeliveryAddressAsync(addressId: Int): SafeApiCall<SelectedAddressResponse>
    suspend fun editDeliveryAddressAsync(updateAddressRequest: UpdateAddressRequest): SafeApiCall<UpdateEditOrDeleteAddressResponse>
    suspend fun deleteDeliveryAddressAsync(deleteAddressRequest: DeleteAddressRequest): SafeApiCall<UpdateEditOrDeleteAddressResponse>
    suspend fun addAddressAsync(addAddressRequest: AddAddressRequest): SafeApiCall<BaseResponse>

    suspend fun addToCartAsync(addToCartRequest: AddToCartRequest): SafeApiCall<AddToCartResponse>
    suspend fun syncCartAsync(addToCartRequest: AddToCartRequest): SafeApiCall<SyncCartResponse>

    suspend fun getUserOrderDetailsAsync(orderId: Int): SafeApiCall<OrderDetailsResponse>
    suspend fun getUserOrderHistoryAsync(): SafeApiCall<OrderListResponse>

    suspend fun getProductDetailsAsync(productId: String): SafeApiCall<ProductDetailsResponse>

    suspend fun checkoutAsync(checkoutRequest: CheckoutRequest): SafeApiCall<NewCheckoutViaDirectCardResponse>

    suspend fun addUserHelpRequestAsync(userHelpRequest: UserHelpRequest): SafeApiCall<UserHelpRequestResponse>
    suspend fun addPrescriptionAsync(addPrescriptionRequest: AddPrescriptionRequest): SafeApiCall<AddPrescriptionResponse>
    suspend fun addShopReviewAsync(addReviewRequest: AddReviewRequest): SafeApiCall<BaseResponse>

    suspend fun getPopulateProductAndPharmacyAsync(
        _productCategoryId: Int,
        _isActive: Int,
        _searchKey: String,
        sortBy: Int,
        isProductFeature: Boolean
    ): SafeApiCall<PharmacyAndProductResponse>

    suspend fun getAllConfigurationsAsync(): SafeApiCall<AllConfigurationsResponse>

    suspend fun getShopListForAutocompleteAsync(searchKey: String): SafeApiCall<PharmacyAutoCompleteListResponse>
    suspend fun getPopulateProductCategoriesAsync(
        shopId: String,
        categoryId: Int,
        isActive: Int
    ): SafeApiCall<AllCategoriesResponse>

    suspend fun categoriesAutoCompleteListAsync(searchKey: String): SafeApiCall<CategoriesAutoCompleteListResponse>

    suspend fun getShopAddressDetailsForMapAsync(
        searchKey: String,
        latitude: Double,
        longitude: Double
    ): SafeApiCall<SearchPharmacyListResponse>


    suspend fun getShopCategoriesAsync(shopId: String): SafeApiCall<ShopCategoriesResponse>
    suspend fun getShopDetailsAsync(shopId: String): SafeApiCall<ShopDetailsResponse>
    suspend fun getShopProductsAsync(getProductRequest: GetProductRequest): SafeApiCall<ShopProductsResponse>

    suspend fun getInvoicePrescriptionCountAsync(userId: String): SafeApiCall<InvoicePrescriptionCountResponse>
    suspend fun getInvoicePrescriptionListAsync(userId: String): SafeApiCall<InvoicePrescriptionListResponse>
    suspend fun getPrescriptionProductListAsync(
        prescriptionRecordId: Int,
        shopId: String,
        userId: String
    ): SafeApiCall<GetPrescriptionProductListResponse>

    suspend fun cancelInvoiceAsync(cancelInvoiceRequest: CancelInvoiceRequest): SafeApiCall<BaseResponse>
    suspend fun getPrescriptionRecordIdAsync(
        shopId: String,
        userId: String
    ): SafeApiCall<GetPrescriptionRecordIdResponse>

    *//**
     * Health professional
     *//*
    suspend fun getAllPatientsAsync(
        pageNo: Int,
        searchKey: String
    ): SafeApiCall<GetAllPatientsResponse>

    suspend fun addPatientAsync(addPatientRequest: AddPatientRequest): SafeApiCall<AddPatientsResponse>

    suspend fun viewPatientAsync(patientRecordId: Int): SafeApiCall<ViewPatientsResponse>

    suspend fun patientDetailAsync(
        patientRecordId: Int,
        patientDetailType: Int
    ): SafeApiCall<GetPatientAddressResponse>

    suspend fun updatePatientProfileAsync(updatePatientProfileRequest: UpdatePatientProfileRequest): SafeApiCall<BaseResponse>

    suspend fun updatePatientAddressAsync(updatePatientAddressRequest: UpdatePatientAddressRequest): SafeApiCall<BaseResponse>

    suspend fun getAllShopAsync(
        searchKey: String,
        pageNo: Int,
        sortBy: Int
    ): SafeApiCall<GetAllPharmaciesResponse>

    suspend fun syncCartForHealthProfessionalAsync(hpSyncCartRequest: HPSyncCartRequest): SafeApiCall<HPSyncCartListResponse>

    suspend fun emptySyncCartForHealthProfessionalAsync(hpSyncCartRequest: HPSyncCartRequest): SafeApiCall<HPEmptySyncCartListResponse>


    *//**
     * Delivery man user apis
     *//*
    suspend fun updateLocationAsync(updateLocationRequest: UpdateLocationRequest): SafeApiCall<BaseResponse>

    suspend fun getNewOrderListAsync(
        deliveryUserId: String,
        pageNo: Int
    ): SafeApiCall<GetNewOrderListResponse>

    suspend fun getOrderDetailAsync(orderId: Int): SafeApiCall<DMOrderDetailsResponse>

    suspend fun acceptOrderAsync(acceptOrderRequest: AcceptOrderRequest): SafeApiCall<AcceptOrderResponse>

    suspend fun updateOrderStatusAsync(updateOrderStatusRequest: UpdateOrderStatusRequest): SafeApiCall<MarkAsDeliveredOrCancelOrderResponse>
    suspend fun updateReceiveOrderAsync(updateReceiveOrderRequest: UpdateReceiveOrderRequest): SafeApiCall<BaseResponse>

    suspend fun getDeliveredOrCancelledOrdersListAsync(
        deliveryUserId: String,
        deliveryStatus: Int,
        pageNo: Int
    ): SafeApiCall<GetNewOrderListResponse>

    suspend fun getAcceptedOrderListAsync(
        deliveryUserId: String
    ): SafeApiCall<AcceptedOrderDetailsResponse>

    suspend fun checkAcceptedOrderAsync(
        deliveryUserId: String
    ): SafeApiCall<CheckAcceptedOrderResponse>

    *//**
     * Mango pay api
     *//*

    suspend fun addCardAsync(addCardRequest: AddCardRequest): SafeApiCall<AddNewCardResponse>

    suspend fun getAvailableCardsAsync(userId: String): SafeApiCall<GetAvailableCardsListResponse>

    suspend fun deactivateCardAsync(deactivateCardRequest: DeactivateCardRequest): SafeApiCall<DeleteCardResponse>

    suspend fun checkoutViaDirectCardPaymentAsync(
        cardId: String,
        checkoutRequest: CheckoutRequest
    ): SafeApiCall<NewCheckoutViaDirectCardResponse>

    suspend fun getPaymentStatusAsync(transactionId: String): SafeApiCall<GetPaymentStatusResponse>
*/
}