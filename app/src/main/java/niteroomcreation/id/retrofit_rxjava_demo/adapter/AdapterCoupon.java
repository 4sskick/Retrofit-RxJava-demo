package niteroomcreation.id.retrofit_rxjava_demo.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import niteroomcreation.id.retrofit_rxjava_demo.R;
import niteroomcreation.id.retrofit_rxjava_demo.model.ModelCoupons;

/**
 * Created by Septian Adi Wijaya on 18-Dec-17.
 */

public class AdapterCoupon extends RecyclerView.Adapter<AdapterCoupon.ItemViewHolder> {

    private List<ModelCoupons> models;
    private Context context;

    //constructor
    public AdapterCoupon(List<ModelCoupons> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coupon, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.txtStore.setText(models.get(position).getStore());
        holder.txtCoupon.setText(models.get(position).getCoupon());
        holder.txtExpiry.setText(models.get(position).getExpiryDate());
        holder.txtCouponCode.setText(models.get(position).getCouponCode());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    //sub class here
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_item_store)
        TextView txtStore;

        @BindView(R.id.txt_item_coupon)
        TextView txtCoupon;

        @BindView(R.id.txt_item_expiry)
        TextView txtExpiry;

        @BindView(R.id.txt_item_coupon_code)
        TextView txtCouponCode;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Snackbar.make(view, "" + models.get(getAdapterPosition()).getCoupon(), Snackbar.LENGTH_LONG).show();
        }
    }
}
