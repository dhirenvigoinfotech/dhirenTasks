package com.example.vigoinfotechassignment.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.vigoinfotechassignment.R;

public class TabFragment1 extends Fragment {

    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        textView = view.findViewById(R.id.textView);

        // Retrieve data from the bundle and display it
        Bundle bundle = getArguments();
        if (bundle != null) {
            String data1 = bundle.getString("data1", "");
            String displayText = "Data 1: " + data1;
            textView.setText(displayText);
        }

        return view;
    }
}
