package ru.quizerplus.quizerapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import io.realm.RealmResults;
import ru.quizerplus.quizerapp.R;
import ru.quizerplus.quizerapp.model.Offer;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {

    private RealmResults results;

    public OfferAdapter(RealmResults results){
        this.results = results;
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new OfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int i) {
        Offer offer = (Offer) results.get(i);
        if (offer == null) {
            return;
        }
        String idStr = offer.getId();
        if (idStr == null) {
            holder.id.setText("");
        } else {
            holder.id.setText(idStr);
        }
        String nameStr = offer.getName();
        if (nameStr == null) {
            holder.name.setText("");
        } else {
            holder.name.setText(nameStr);
        }
        String desStr = offer.getDes();
        if (desStr == null) {
            holder.des.setText("");
        } else {
            holder.des.setText(desStr);
        }
        String urlStr = offer.getUrl();
        if (urlStr == null) {
            holder.url.setText("");
        } else {
            holder.url.setText(urlStr);
        }
        Boolean b = offer.getBrowser();
        if (b == null) {
            holder.browser.setChecked(false);
        } else {
            holder.browser.setChecked(b);
        }
        Boolean enabl = offer.getEnabled();
        if (enabl == null) {
            holder.enabled.setChecked(false);
        } else {
            holder.enabled.setChecked(enabl);
        }
    }

    @Override
    public int getItemCount() {
        if (results == null) {
            return 0;
        } else {
            return results.size();
        }
    }

    public class OfferViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView name;
        TextView des;
        TextView url;
        CheckBox browser;
        CheckBox enabled;

        OfferViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.item_tv_id);
            name  = itemView.findViewById(R.id.item_tv_name);
            des  = itemView.findViewById(R.id.item_tv_des);
            url  = itemView.findViewById(R.id.item_tv_url);
            browser  = itemView.findViewById(R.id.item_cb_browser);
            enabled  = itemView.findViewById(R.id.item_cb_enabled);
        }
    }
}
