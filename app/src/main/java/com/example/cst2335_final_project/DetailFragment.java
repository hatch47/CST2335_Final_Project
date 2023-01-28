package com.example.cst2335_final_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class DetailFragment extends Fragment {

    private Bundle dataFromActivity;
    private long id;
    private AppCompatActivity parentActivity;

    //for fav list
    private TextView urlValue;
    private ImageButton favButton;
    private ListView favListView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> favUrls;
    private ImageButton backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dataFromActivity = getArguments();

        String url = dataFromActivity.getString(MainActivity.WEBURL_SELECTED);
        String title = dataFromActivity.getString(MainActivity.WEBTITLE_SELECTED);


        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_detail, container, false);

        //show the URL
        TextView urlView = (TextView) result.findViewById(R.id.url_value);
        TextView titleView = (TextView) result.findViewById(R.id.title_value);
//        urlView.setMovementMethod(LinkMovementMethod.getInstance());
        urlView.setText(url);
        titleView.setText(title);

        // for fav button


//        return view;
        return result;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //context will either be FragmentExample for a tablet, or EmptyActivity for phone
        parentActivity = (AppCompatActivity) context;
    }

}
