package rx2network.toche.com.reactivenetwork.Retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by admin on 3/17/2017 AD.
 */

public interface ServiceApi {

    @FormUrlEncoded
    @POST("REstApi/service/test")
    Call<Response> tesetApi(@Field("id") String id,@Field("name") String name,@Field("age") String age);

}
