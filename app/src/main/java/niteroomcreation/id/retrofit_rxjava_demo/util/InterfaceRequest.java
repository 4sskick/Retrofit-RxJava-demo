package niteroomcreation.id.retrofit_rxjava_demo.util;

import io.reactivex.Observable;
import niteroomcreation.id.retrofit_rxjava_demo.model.ModelStoreCoupons;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Septian Adi Wijaya on 18-Dec-17.
 */

public interface InterfaceRequest {
    @GET("coupons/")
    Observable<ModelStoreCoupons> getCoupons(@Query("status") String status);

    @GET("storeOffers")
    Observable<ModelStoreCoupons> getStoreInfo();
}
