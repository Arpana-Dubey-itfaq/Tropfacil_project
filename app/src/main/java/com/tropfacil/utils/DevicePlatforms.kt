package com.tropfacil.utils

enum class DevicePlatforms(val value: Int) {
    Android(2)
}

enum class VerificationType(val value: Int) {
    RegisterAccount(0),
    ForgotPassword(1),
    ChangePassword(2),
    ChangeEmail(3),
    ChangePhoneNumber(4)
}

enum class AccountTypes(val value: Int) {
    RegularUserType(0),//regular customer
    HealthProfessionalType(1),// Health professional
    DeliveryManType(2)//Delivery Man
}

enum class ProductStatus(val value: Int) {
    Active(1),
    InActive(2),
    OutOfStock(3)
}

enum class ShopStatus(val value: String) {
    Closed("Closed"),
    Open("Open")
}

enum class DeliveryMethods(val value: Int) {
    NotApplicable(0),
    GreenDelivery(1),
    NormalDelivery(2)
}

enum class OrderProgressStatus(val value: Int) {
    PENDING(0),
    PLACED(1),
    INPROGRESS(2),
    READYFORPICKUP(3),
    OUTFORDELIVERY(4),
    COMPLETED(5), //DELIVERED
    CANCELORDER(6),
    REJECTED(7) //REFUSED
}


enum class DeliveredOrCancelledOrderStatus(val value: Int) {

    CANCELORDER(3),
    DELIVERED(4)
}

enum class OrderPaymentType(val value: Int) {
    ONLINEPAYMENT(1),
    CASHONDELIVERY(2),
    CARDONDELIVERY(3)
}

enum class OrderConfigType(val value: Int) {
    MINORDER(2),
    COMMISSIONRATE(8),
    NORMALDELIVERYFEEINPARIS(9),
    NORMALDELIVERYFEEOUTPARIS(10),
    GREENDELIVERYINPARIS(11),
    GREENDELIVERYOUTPARIS(12)

}


enum class CardProviders(val value: String) {
    MASTERCARD ("MASTERCARD"),
    AMEX ("AMEX"),
    VISA  ("VISA"),
}

enum class EPaymentStatus(val value: String) {
    CREATED ("CREATED"),
    SUCCEEDED  ("SUCCEEDED")
}

enum class InvoicePrescriptionProductStatus(val value: Int) {
    NEW(1),
    COMPLETED(2), //DELIVERED
    CANCELORDER(3)
}

