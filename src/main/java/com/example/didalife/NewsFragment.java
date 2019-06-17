package com.example.didalife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {
    private List<News> newsList;
    private NewsAdapter adapter;
    private Handler handler;
    private ListView lv;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_news, container, false);
        //显示标题栏
        toolbar=view.findViewById(R.id.toolbar2);
        toolbar.setLogo(R.mipmap.dida);
        toolbar.setTitle("DiDa Life");
        toolbar.setSubtitle("您的生活小助手");
        NewsFragment.this.setSupportActionBar(toolbar);
        newsList = new ArrayList<>();
        lv = (ListView)view.findViewById(R.id.news_lv);
        getNews();
        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 1){
                    adapter = new NewsAdapter(getActivity(),newsList);
                    lv.setAdapter(adapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            News news = newsList.get(position);
                            Intent intent = new Intent(NewsFragment.this.getActivity(),NewsDisplayActvivity.class);
                            intent.putExtra("news_url",news.getNewsUrl());
                            startActivity(intent);
                        }
                    });
                }
            }
        };               return view;
    }

    private void getNews() {
        Log.e("Jsoup","Test");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                   
                    for(int i = 1;i<=20;i++) {

                        Document doc = Jsoup.connect("https://voice.hupu.com/nba/" + Integer.toString(i)).get();
                        Elements titleLinks = doc.select("div.list-hd");

                        Elements timeLinks = doc.select("div.otherInfo");
                        Log.e("title",Integer.toString(titleLinks.size()));
                        for(int j = 0;j < titleLinks.size();j++){
                            String title = titleLinks.get(j).select("a").text();
                            String uri = titleLinks.get(j).select("a").attr("href");
                            //   String desc = descLinks.get(j).select("span").text();
                            String time = timeLinks.get(j).select("span.other-left").select("a").text();
                            News news = new News(title,uri,null,time);
                            newsList.add(news);
                        }
                    }
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }



    private void setSupportActionBar(Toolbar toolbar) {
    }
}
