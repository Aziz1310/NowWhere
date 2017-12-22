package com.maher.nowhere.SearchActivity.calander;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maher.nowhere.R;


import java.util.Collections;
import java.util.List;

public class CalanderAdapter extends RecyclerView.Adapter<CalanderAdapter.MyViewHolder> {

    private List<CalanderItem> mDataList = Collections.emptyList();
    private final LayoutInflater inflater;
    private RelativeLayout selected;
    private Context mcontext;

    public CalanderAdapter(Context context, List<CalanderItem> data) {
         mcontext = context;
        inflater = LayoutInflater.from(context);
        this.mDataList = data;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_calander, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        CalanderItem current = mDataList.get(position);
        if(position==0){
            selected=holder.rl_bg;
            selected.setBackgroundColor(mcontext.getResources().getColor(R.color.colorAccent));
        }
       // holder.day.setText(current.getDay());
        holder.month.setText(current.getMonth());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
                System.out.println(v.getId());
                selected.setBackgroundColor(mcontext.getResources().getColor(R.color.colorBlackTransparent));
                selected=v.findViewById(R.id.rl_bg);
                selected.setBackgroundColor(mcontext.getResources().getColor(R.color.colorAccent));

                //  context.startActivity(new Intent(context,ListEventsActivity.class));
	        }
       });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
         TextView day;
         TextView month;
         RelativeLayout rl_bg;
        // ImageView imgIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
           // day = itemView.findViewById(R.id.tv_day);
            month=itemView.findViewById(R.id.tv_month);
            rl_bg=itemView.findViewById(R.id.rl_bg);
            // imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);

        }
    }
}
