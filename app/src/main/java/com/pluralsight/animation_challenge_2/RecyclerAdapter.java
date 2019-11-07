package com.pluralsight.animation_challenge_2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends  RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private final Context mContext;
    private  final List<Landscape> mLandscapes;
    private final LayoutInflater mLayoutInflater;

    public RecyclerAdapter(Context mContext, List<Landscape> mLandscapes) {
        this.mContext = mContext;
        this.mLandscapes = mLandscapes;
        mLayoutInflater = LayoutInflater.from(this.mContext);
        Log.d("RecyclerAdapter", "   Test de log");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.list_item,parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Landscape current = mLandscapes.get(position);
        holder.setData(current, position);
        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        return mLandscapes.size();
    }

    public void removeItem(int position) {
        //mLandscapes.removeAt(position);
        mLandscapes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mLandscapes.size());
    }

    public void addItem(int position, Landscape landscape) {
        mLandscapes.add(position, landscape);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mLandscapes.size());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public final TextView title;
        public final ImageView imgThumb;
        public final ImageView imgDelete;
        public final ImageView imgAdd;
        public  int pos;
        public Landscape current;


        public void setData(Landscape current, int position) {
            //this.title.text = current.title;
            this.title.setText(current.title);
            //this.title = current.title;
            imgThumb.setImageResource(current.imageID);
            this.pos = position;
            this.current = current;
        }

        public void setListeners() {/*
            imgDelete.setOnClickListener((View.OnClickListener) this);
            imgAdd.setOnClickListener((View.OnClickListener) this);
            imgThumb.setOnClickListener((View.OnClickListener) this);*/

        }

// 11-07 14:02:52.909 13868-13868/? E/RecyclerView: No adapter attached; skipping layout

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Log.d("RecyclerMyViewHolder", "   Test de log");
            title = (TextView)itemView.findViewById(R.id.tvTitle);
            imgThumb = (ImageView)itemView.findViewById(R.id.img_row);
            imgDelete = (ImageView)itemView.findViewById(R.id.img_row_delete);
            imgAdd = (ImageView)itemView.findViewById(R.id.img_row_add);
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(pos);

                }
            });
            imgAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addItem(pos, current);
                }
            });
        }
    }
}
