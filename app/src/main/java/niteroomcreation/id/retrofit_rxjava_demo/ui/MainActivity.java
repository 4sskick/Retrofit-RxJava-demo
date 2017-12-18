package niteroomcreation.id.retrofit_rxjava_demo.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import niteroomcreation.id.retrofit_rxjava_demo.R;
import niteroomcreation.id.retrofit_rxjava_demo.adapter.AdapterCoupon;
import niteroomcreation.id.retrofit_rxjava_demo.model.ModelStoreCoupons;
import niteroomcreation.id.retrofit_rxjava_demo.util.InterfaceRequest;
import niteroomcreation.id.retrofit_rxjava_demo.util.RequestService;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.txt_store_name)
    TextView txtStoreName;

    @BindView(R.id.txt_coupon_count)
    TextView txtCouponCount;

    @BindView(R.id.txt_max_cashback)
    TextView txtMaxCashback;

    @BindView(R.id.list_coupon)
    RecyclerView listCoupon;

    @BindView(R.id.btn_show_coupons_topstore)
    Button btnShowCouponTopstore;

    @BindView(R.id.btn_show_coupons)
    Button btnShowCoupon;

    //variables
    Retrofit retrofit;
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        retrofit = new RequestService().createService();

        //set action component
        btnShowCoupon.setOnClickListener(this);
        btnShowCouponTopstore.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show_coupons:
                Log.e("tagCheck", "onClick: btn_show_coupons");
                //single api call using retrofit and rxjava
                retrofit.create(InterfaceRequest.class).getCoupons("topcoupons")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResults, this::handleErrors);
                break;

            case R.id.btn_show_coupons_topstore:
                Log.e("tagCheck", "onClick: btn_show_coupons_topstore");
                //two Retrofit service calls execute parallel using RxJava
                //--------------------------------------------------------
                //first it creates an observable which emits retrofit service class
                //to leave current main thread, we need to use subscribeOn which subscribes the observable on computation thread
                //flatMap is used to apply function on the item emitted by previous observable
                //function makes two rest service calls using the give retrofit object for defined api interface
                //these two calls run parallel that is why subscribeOn is used on each of them
                //since these two api call return same object, they are joined using concatArray operator
                //finally consumer observes on android main thread
                Observable.just(retrofit.create(InterfaceRequest.class))
                        .subscribeOn(Schedulers.computation())
                        .flatMap(interfaceRequest -> {

                            Log.e("tagCheck", "onClick: request data");

                            Observable<ModelStoreCoupons> couponsObservable =
                                    interfaceRequest.getCoupons("topcoupons")
                                            .subscribeOn(Schedulers.io());

                            Observable<ModelStoreCoupons> storeInforObservable =
                                    interfaceRequest.getStoreInfo()
                                            .subscribeOn(Schedulers.io());

                            return Observable.concatArray(couponsObservable, storeInforObservable);
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResults, this::handleErrors);
        }
    }

    @Override
    protected void onDestroy() {
        //dispose subscriptions
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
        super.onDestroy();
    }

    /**
     * custom method
     */
    private void handleResults(ModelStoreCoupons model) {
        if (model.getCoupons() != null) {
            //set adapter here
            AdapterCoupon adapter = new AdapterCoupon(model.getCoupons(), this);
            listCoupon.setLayoutManager(new LinearLayoutManager(this));
            listCoupon.setAdapter(adapter);
            ViewCompat.setNestedScrollingEnabled(listCoupon, false);
        } else {
            txtStoreName.setText(model.getStore());
            txtCouponCount.setText(model.getTotalCoupons());
            txtMaxCashback.setText(model.getMaxCashback());
        }
    }

    private void handleErrors(Throwable t) {
        Log.e("tagCheck", "handleErrors: " + t.toString());
        Snackbar.make(getWindow().getCurrentFocus(), "Error in getting coupons", Snackbar.LENGTH_LONG).show();
    }
}
