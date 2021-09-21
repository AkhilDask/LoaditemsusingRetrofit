package com.example.retrofit2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> implements Filterable {


    private List<Posts> postsList;
    private List<Posts> postsListAll;
    private PostsAdapterListener listener;
    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, body;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvTitle);
            body = view.findViewById(R.id.tvBody);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPostSelected(postsListAll.get(getAdapterPosition()));
                }
            });

        }

    }

    public PostsAdapter(Context context, List<Posts> postsList, PostsAdapterListener listener) {


        this.postsList = postsList;
        this.context = context;
        this.listener = listener;

        this.postsListAll = postsList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Posts posts = postsListAll.get(position);

        holder.title.setText(postsListAll.get(position).getTitle());
        holder.body.setText(postsListAll.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return postsListAll.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String searchtext =charSequence.toString().toLowerCase();

                if (searchtext.isEmpty()) {
                    postsListAll = postsList;
                } else {
                    List<Posts> filteredList = new ArrayList<>();
                    for (Posts post : postsList) {
                        if (post.getTitle().contains(searchtext)) {
                            filteredList.add(post);
                        }
                    }
                    postsListAll = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = postsListAll;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                postsListAll = (ArrayList<Posts>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface PostsAdapterListener {
        void onPostSelectd(Posts post);

        void onPostSelected(Posts posts);
    }


}





