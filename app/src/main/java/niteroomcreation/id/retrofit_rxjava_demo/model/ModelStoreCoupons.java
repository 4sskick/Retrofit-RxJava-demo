package niteroomcreation.id.retrofit_rxjava_demo.model;

import java.util.List;

/**
 * Created by Septian Adi Wijaya on 18-Dec-17.
 */

public class ModelStoreCoupons {
    private String store;
    private String totalCoupons;
    private String maxCashback;
    private List<ModelCoupons> coupons;

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getTotalCoupons() {
        return totalCoupons;
    }

    public void setTotalCoupons(String totalCoupons) {
        this.totalCoupons = totalCoupons;
    }

    public String getMaxCashback() {
        return maxCashback;
    }

    public void setMaxCashback(String maxCashback) {
        this.maxCashback = maxCashback;
    }

    public List<ModelCoupons> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<ModelCoupons> coupons) {
        this.coupons = coupons;
    }
}
