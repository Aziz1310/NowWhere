package com.maher.nowhere.sideMenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.maher.nowhere.R;

import java.util.Collections;
import java.util.List;

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {

    private List<NavigationDrawerItem> mDataList = Collections.emptyList();
    private final LayoutInflater inflater;

    public NavigationDrawerAdapter(Context context, List<NavigationDrawerItem> data) {
        Context context1 = context;
        inflater = LayoutInflater.from(context);
        this.mDataList = data;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        NavigationDrawerItem current = mDataList.get(position);
        holder.title.setText(current.getTitle());

        //   holder.imgIcon.setImageResource(current.getImageId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
                System.out.println(v.getId());
              //  context.startActivity(new Intent(context,ListEventsActivity.class));
	        }
       });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView messageNumber;
        // ImageView imgIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            messageNumber=itemView.findViewById(R.id.tvMessageNumber);
            // imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);

        }
    }
}
