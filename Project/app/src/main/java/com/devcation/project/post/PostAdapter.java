package com.devcation.project.post;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devcation.project.R;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> implements OnPostItemClickListener {
    ArrayList<PostInfo> items = new ArrayList<PostInfo>();

    OnPostItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.post_item, viewGroup, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        PostInfo item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(PostInfo item) {
        items.add(item);
    }

    public void setItems(ArrayList<PostInfo> items) {
        this.items = items;
    }

    public PostInfo getItem(int position) {
        return items.get(position);
    }


    public void setOnItemClickListener(OnPostItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onPostItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onPostItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        ImageView imageView3;

        public ViewHolder(View itemView, final PostAdapter listener) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView3 = itemView.findViewById(R.id.imageView3);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();


                    if (listener != null) {
                        listener.onPostItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }

        public void setItem(PostInfo item) {
            textView.setText(item.title);
            textView2.setText(item.contents);
            imageView3.setImageURI(Uri.parse("file://" + item.getPath()));

        }

    }

}
