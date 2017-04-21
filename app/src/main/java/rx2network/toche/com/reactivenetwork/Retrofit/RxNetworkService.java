package rx2network.toche.com.reactivenetwork.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 3/17/2017 AD.
 */

public class RxNetworkService {

    ServiceApi serviceApi;
    Retrofit retrofit;
    public RxNetworkService() {

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.114:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceApi = retrofit.create(ServiceApi.class);
    }

    public ServiceApi getService(){
        return serviceApi = retrofit.create(ServiceApi.class);
    }
}
