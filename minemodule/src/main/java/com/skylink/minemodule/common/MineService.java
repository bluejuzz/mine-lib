package com.skylink.minemodule.common;


import com.skylink.android.commonlibrary.base.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author: Fangj .
 * @date: On 2018/1/10
 */

public interface MineService {
    /***
     * 退出登录
     * @param token
     * @return
     */
    @POST(ContantsUrl.MINE_LOGINOUT)
    @FormUrlEncoded
    Call<BaseResponse<String>> loginOut(
            @Field("token") String token
    );

    /***
     * 修改密码
     * @param pswd
     * @param newpswd
     * @return
     */
    @GET(ContantsUrl.MINE_MODIFYPASSWORD)
    Call<BaseResponse> modifyPassword(
            @Query("pswd") String pswd,
            @Query("newpswd") String newpswd
    );

}
