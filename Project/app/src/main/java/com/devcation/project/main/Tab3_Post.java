package com.devcation.project.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.devcation.project.R;
import com.devcation.project.post.PostDatabase;


public class Tab3_Post extends Fragment {
    Button btnInsert;
    EditText editTitle;
    EditText editContents;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab3_post, container, false);
        editTitle = rootView.findViewById(R.id.editTitle);
        editContents = rootView.findViewById(R.id.editContents);

        btnInsert = rootView.findViewById(R.id.button2);
        btnInsert.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                PostDatabase database = PostDatabase.getInstance(getContext());
                database.insertRecord(editTitle.getText().toString(), editContents.getText().toString());
            }
        });


        return rootView;
    }

}
