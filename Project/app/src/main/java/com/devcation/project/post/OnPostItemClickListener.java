package com.devcation.project.post;

import android.view.View;

import com.devcation.project.search.MovieAdapter;


public interface OnPostItemClickListener {
    public void onPostItemClick(PostAdapter.ViewHolder holder, View view, int position);
}
