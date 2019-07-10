package ru.quizerplus.quizerapp.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import ru.quizerplus.quizerapp.model.Model;

public interface ServerApi {

    @FormUrlEncoded
    @POST("test_android.php")
    Call<Model> postData(@Field("user_login") String login,
                         @Field("user_pass") String pass);

}
