package com.devcation.project.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.devcation.project.R;

import com.devcation.project.feed.list.UserData;
import com.devcation.project.search.OnItemClickListener;

import com.devcation.project.search.Movie;
import com.devcation.project.search.MovieAdapter;
import com.devcation.project.search.MovieList;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class Tab2_Search extends Fragment {

    static RequestQueue requestQueue;

    RecyclerView recyclerView;
    MovieAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab2_search, container, false);

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(rootView.getContext());
        }

        recyclerView = rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);
        makeRequest();


        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(MovieAdapter.ViewHolder holder, View view, int position) {
                Movie item = adapter.getItem(position);
                Toast.makeText(holder.itemView.getContext(), "name : " + item.movieNm, Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

    public void makeRequest() {
        String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=072b596b4a06cbb74e5f13a6bcdba521&targetDt=20120101";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 -> " + response);

                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();

                return params;
            }
        };

        request.setShouldCache(false);
        requestQueue.add(request);
        println("요청 보냄.");
    }

    public void println(String data) {
        Log.d("MainActivity", data);
    }

    public void processResponse(String response) {
        Gson gson = new Gson();
        MovieList movieList = gson.fromJson(response, MovieList.class);

        println("영화정보의 수 : " + movieList.boxOfficeResult.dailyBoxOfficeList.size());

        for (int i = 0; i < movieList.boxOfficeResult.dailyBoxOfficeList.size(); i++) {
            Movie movie = movieList.boxOfficeResult.dailyBoxOfficeList.get(i);

            adapter.addItem(movie);
        }

        adapter.notifyDataSetChanged();
    }

}
