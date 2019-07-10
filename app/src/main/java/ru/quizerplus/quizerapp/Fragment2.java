package ru.quizerplus.quizerapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import ru.quizerplus.quizerapp.adapters.OfferAdapter;
import ru.quizerplus.quizerapp.model.Offer;

public class Fragment2 extends Fragment implements View.OnClickListener{
    FragmentClickListener mListener;

    public static final String NAME = "name";

    public static final String INFO = "info";

    @Override
    public void onClick(View v) {
        mListener.onButtonClick(v);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        Button btnClear = view.findViewById(R.id.button_clear);
        Button btnBack = view.findViewById(R.id.button_back);

        btnClear.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvInfo = view.findViewById(R.id.tv_info);
        TextView tvNoData = view.findViewById(R.id.tv_no_data);

        Bundle bundle = getArguments();
        String name = "";
        String info = "";

        if (bundle != null) {
            name = bundle.getString(NAME);
            info = bundle.getString(INFO);
        }

        tvName.setText(name);
        tvInfo.setText(info);


        Realm.init(getContext());
        RealmConfiguration config = Realm.getDefaultConfiguration();
        Realm realm = Realm.getInstance(config);
        RealmResults realmResults = realm.where(Offer.class).findAll();

        if (realmResults.size() != 0) {
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new OfferAdapter(realmResults));
            tvNoData.setVisibility(View.INVISIBLE);
            tvInfo.setVisibility(View.VISIBLE);
        } else {
            tvNoData.setVisibility(View.VISIBLE);
            tvInfo.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (FragmentClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement FragmentClickListener");
        }
    }
}
