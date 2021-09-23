package com.devcation.project.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devcation.project.R;
import com.devcation.project.list.OnItemClickListener;
import com.devcation.project.list.UserData;
import com.devcation.project.list.UserDataAdapter;


public class Fragment4 extends Fragment {
    UserDataAdapter adapter;
    RecyclerView recyclerView;
    EditText efName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.f1_fragment4, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        efName = rootView.findViewById(R.id.name);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);

        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);



        adapter = new UserDataAdapter();

        adapter.addItem(new UserData("asd", "000-1000-1000","asd","asd"));
        adapter.addItem(new UserData("asd", "000-1000-1000","asd","asd"));
        adapter.addItem(new UserData("asd", "000-1000-1000","asd","asd"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(UserDataAdapter.ViewHolder holder, View view, int position) {
                UserData item = adapter.getItem(position);
                Toast.makeText(holder.itemView.getContext(), "name : " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItem(new UserData("", efName.getText().toString(),"asd","asd"));
                adapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }
}
