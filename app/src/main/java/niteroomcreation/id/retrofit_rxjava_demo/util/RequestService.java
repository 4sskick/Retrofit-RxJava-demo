package niteroomcreation.id.retrofit_rxjava_demo.util;

import niteroomcreation.id.retrofit_rxjava_demo.GlobalConstant;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Septian Adi Wijaya on 18-Dec-17.
 */

public class RequestService {

    private static Retrofit retrofit = null;

    public Retrofit createService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(GlobalConstant.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
