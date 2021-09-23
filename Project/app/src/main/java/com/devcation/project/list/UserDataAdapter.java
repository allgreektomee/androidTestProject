package com.devcation.project.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devcation.project.R;

import java.util.ArrayList;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.ViewHolder> implements OnItemClickListener {


    //2
    ArrayList<UserData> items = new ArrayList<UserData>();
    //4
    OnItemClickListener listener;

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 컨텍스트 참조
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout, parent, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 재사용 뷰홀더 객체를 파람으로 전달
        UserData itme = items.get(position);
        holder.setItem(itme);
    }

    @Override
    public int getItemCount() {
        //어레이리스트 사이즈
        return items.size();
    }

    //3 arraylist set get
    public void addItem(UserData item) {
        items.add(item);
    }

    public void setItems(ArrayList<UserData> items) {
        this.items = items;
    }

    public UserData getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, UserData item) {
        items.set(position, item);
    }

    //4
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    //1 viewHolder class 생
    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvPhoneNumber;
        TextView tvName;
        TextView tvUserName;
        TextView tvPassword;

        public ViewHolder(View itemView, final UserDataAdapter listener) {
            super(itemView);

            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
            tvName = itemView.findViewById(R.id.tvName);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvPassword = itemView.findViewById(R.id.tvPassword);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();


                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });

        }

        public void setItem(UserData item) {
            tvName.setText(item.getName());
            tvPhoneNumber.setText(item.getPhoneNumber());
            tvUserName.setText(item.getUserName());
            tvPassword.setText(item.getPassword());
        }

    }
}
