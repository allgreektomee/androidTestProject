package com.devcation.project.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devcation.project.R;
import com.devcation.project.post.OnPostItemClickListener;
import com.devcation.project.post.PostAdapter;
import com.devcation.project.post.PostDatabase;
import com.devcation.project.post.PostInfo;


import java.util.ArrayList;


public class Tab4_Like extends Fragment {
    PostAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab4_like, container, false);

        PostDatabase database = PostDatabase.getInstance(getContext());
        ArrayList<PostInfo> result = database.selectAll();


        recyclerView = rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PostAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setItems(result);

        adapter.setOnItemClickListener(new OnPostItemClickListener() {

            @Override
            public void onPostItemClick(PostAdapter.ViewHolder holder, View view, int position) {
                PostInfo item = adapter.getItem(position);
                Toast.makeText(holder.itemView.getContext(), "name : " + item.getTitle(), Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }

}
