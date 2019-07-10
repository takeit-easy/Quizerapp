package ru.quizerplus.quizerapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment1 extends Fragment implements View.OnClickListener{
    FragmentClickListener mListener;

    @Override
    public void onClick(View v) {
        mListener.onButtonClick(v);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        Button btnDownload = view.findViewById(R.id.button_download);
        Button btnShow = view.findViewById(R.id.button_show);

        btnDownload.setOnClickListener(this);
        btnShow.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (FragmentClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnButtonClickListener");
        }
    }
}