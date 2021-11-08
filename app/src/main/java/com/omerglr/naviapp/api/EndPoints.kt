package com.omerglr.lovelica.api


import com.omerglr.naviapp.api.model.*
import com.omerglr.naviapp.api.requests.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*


interface EndPoints {

    @POST("auth/register")
    @Headers("Accept:application/json")
    fun sendRegisterRequest(@Body registerRequest: RegisterRequest): Observable<RegisterResponse>;


    @POST("auth/login")
    @Headers("Accept:application/json")
    fun sendLoginRequest(@Body loginRequest: LoginRequest): Observable<LoginResponse>;

    @POST("auth/forgotten")
    @Headers("Accept:application/json")
    fun sendSifremiHatirlatRequest(@Body sifremiHatirlatRequest: SifremiHatirlatRequest): Observable<SifremiHatirlatResponse>;

    @POST("home/stream?test=test&country=TR")
    @Headers("Accept:application/json")
    fun sendHomeRequest(@Body homeRequest: HomeRequest):Observable<HomeResponse>

    @Multipart
    @POST("user/uploadPhoto")
    @Headers("Accept:application/json")
    fun sendUploadPhotoRequest(
        @Part image:MultipartBody.Part):Observable<UploadPhotoResponse>




}