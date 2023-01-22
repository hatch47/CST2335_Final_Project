package com.example.cst2335_final_project;

import android.content.Context;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class DetailFragment extends Fragment {

    private Bundle dataFromActivity;
    private long id;
    private AppCompatActivity parentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dataFromActivity = getArguments();
//        String name = dataFromActivity.getString(MainActivity.NAME_SELECTED);
//        String mass = dataFromActivity.getString(MainActivity.MASS_SELECTED);
//        String height = dataFromActivity.getString(MainActivity.HEIGHT_SELECTED);
        String url = dataFromActivity.getString(MainActivity.WEBURL_SELECTED);

        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_detail, container, false);

        //show the URL
        TextView urlView = (TextView) result.findViewById(R.id.url_value);
//        urlView.setMovementMethod(LinkMovementMethod.getInstance());
        urlView.setText(url);



        return result;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //context will either be FragmentExample for a tablet, or EmptyActivity for phone
        parentActivity = (AppCompatActivity) context;
    }

}
