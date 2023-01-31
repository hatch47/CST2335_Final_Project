package com.example.cst2335_final_project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//
//public class MyAdapter extends ArrayAdapter<String> {
//    private ArrayList<String> webTitles;
//    private ArrayList<String> webUrls;
//    private TextView webUrlTextView;
//
//    public MyAdapter(Context context, ArrayList<String> webTitles, ArrayList<String> webUrls) {
//        super(context, android.R.layout.simple_list_item_1, webTitles);
//        this.webTitles = webTitles;
//        this.webUrls = webUrls;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = convertView;
//        if (view == null) {
//            view = LayoutInflater.from(getContext()).inflate(R.layout.my_custom_layout, parent, false);
//        }
//
//        String webTitle = webTitles.get(position);
//        String webUrl = webUrls.get(position);
//
//        TextView text1 = (TextView) view.findViewById(R.id.web_title_textview);
//        webUrlTextView = (TextView) view.findViewById(R.id.web_url_textview);
//        text1.setText(webTitle);
//        webUrlTextView.setText(webUrl);
//        webUrlTextView.setMovementMethod(LinkMovementMethod.getInstance());
//        return view;
//    }
//}



public class MyAdapter extends ArrayAdapter<String> {
    private ArrayList<String> webTitles;
    private ArrayList<String> webUrls;
    private TextView webUrlTextView;


    public MyAdapter(Context context, ArrayList<String> webTitles, ArrayList<String> webUrls) {
        super(context, android.R.layout.simple_list_item_1, webTitles);
        this.webTitles = webTitles;
        this.webUrls = webUrls;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        String webTitle = webTitles.get(position);
        String webUrl = webUrls.get(position);

        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
        TextView text2 = (TextView) view.findViewById(android.R.id.text2);
        text1.setText(webTitle);
        text2.setText(webUrl);

        return view;
    }




}
