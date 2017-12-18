package niteroomcreation.id.retrofit_rxjava_demo.model;

/**
 * Created by Septian Adi Wijaya on 18-Dec-17.
 */

public class ModelCoupons {
    private String store;
    private String coupon;
    private String expiryDate;
    private String couponCode;

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
