package com.bway.caoyuan.path;


import android.content.Intent;

import com.bway.caoyuan.bean.BannrBean;
import com.bway.caoyuan.bean.CarBean;
import com.bway.caoyuan.bean.CircleBean;
import com.bway.caoyuan.bean.FootBean;
import com.bway.caoyuan.bean.HomeList;
import com.bway.caoyuan.bean.InformBean;
import com.bway.caoyuan.bean.ordel.MyOrdelBean;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.bean.ResultBean;
import com.bway.caoyuan.bean.SearchBean;
import com.bway.caoyuan.bean.SerializableBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Util {

    @FormUrlEncoded
    @POST("user/v1/register")
    Observable<Result> register(@Field("phone") String m, @Field("pwd") String p);

    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<Result<ResultBean>> login(@Field("phone") String m, @Field("pwd") String p);

    /**
     * banner
     */
    @GET("commodity/v1/bannerShow")
    Observable<Result<List<BannrBean>>> bannerShow();

    /**
     * 首页商品列表
     */
    @GET("commodity/v1/commodityList")
    Observable<Result<HomeList>> commodityList();

    /**
     *商品搜索
     *
     */
    @GET("commodity/v1/findCommodityByKeyword")
    Observable<Result<List<SearchBean>>> Search(@Query("keyword") String keyword ,@Query("page") int page ,@Query("count") int count) ;
/*
* 我的足迹
*
* */
@GET("commodity/verify/v1/browseList")
Observable<Result<List<FootBean>>> getmyfoot(

        @Header("userId") long userId,
        @Header("sessionId")String sessionId,
        @Query("page") int page, @Query("count") int count);

/*
*
* 圈子
*
* */
@GET("circle/v1/findCircleList")
Observable<Result<List<CircleBean>>> findCircleList(
        @Header("userId") long userId,
        @Header("sessionId")String sessionId,
        @Query("page")int page,
        @Query("count")int count);
/*
* 点赞
* */

    @FormUrlEncoded
    @POST("circle/verify/v1/addCircleGreat")
    Observable<Result<List<CircleBean>>> addcall(
            @Header("userId") long userId,
            @Header("sessionId")String sessionId,
            @Path("uid") int uid,
            @Field("page") int page,
            @Field("count") int count);

    /*
    * 购物车
    *
    * */

    @GET("order/verify/v1/findShoppingCart")
    Observable<Result<List<CarBean>>> getcar(@Header("userId") long userId,
                                             @Header("sessionId") String sessionId);


    //个人信息

//修改昵称

    @PUT("verify/v1/modifyUserNick")
    Observable<Result<List<InformBean>>> gettext(@Header("userId") long userId,
                                             @Header("sessionId") String sessionId,
                                             @Body String us
    );

    @PUT("user/verify/v1/modifyUserPwd")
    Observable<Result<List<InformBean>>> getpwd(@Header("userId") long userId,
                                                @Header("sessionId") String sessionId,
                                                 @Body String[] oldPwd
                                                );
    @FormUrlEncoded
    @POST("user/verify/v1/modifyHeadPic")
    Observable<Result<List<InformBean>>> gettou(@Header("userId") long userId,
                                                 @Header("sessionId") String sessionId,
                                                 @Field("image") Intent image
    );
    //发布圈子

//
//    /**
//     * 同步购物车数据
//     */
//    @PUT("order/verify/v1/syncShoppingCart")
//    Observable<Result> syncShoppingCart(
//            @Header("userId") String userId,
//            @Header("sessionId")String sessionId,
//       [[[[[[[[[[[[[[[[[[
//  \    @Body String data);
//}
@GET("user/verify/v1/receiveAddressList")
    Observable<Result<List<SerializableBean>>> getSerializable(
        @Header("userId") long userId,
        @Header("sessionId") String sessionId
);
//我的订单
    @GET("order/verify/v1/findOrderListByStatus")
    Observable<Result<List<MyOrdelBean>>> getmyordel(
            @Header("userId") long userId,
            @Header("sessionId") String sessionId,
            @Query("status") int status,
            @Query("page") int page,
            @Query("count") int count

    );
    /**
     * 发布圈子
     */
    @POST("circle/verify/v1/releaseCircle")
    Observable<Result> releaseCircle(@Header("userId") long userId,
                                     @Header("sessionId")String sessionId,
                                     @Body MultipartBody body);


    /**
     * 圈子点赞
     */
    @FormUrlEncoded
    @POST("circle/verify/v1/addCircleGreat")
    Observable<Result> addCircleGreat(
            @Header("userId") long userId,
            @Header("sessionId")String sessionId,
            @Field("circleId") long circleId);
}