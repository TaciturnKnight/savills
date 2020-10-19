package com.bamboo.savills.base.view;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by qyj on 2019/9/29.
 */

public abstract class BaseAdapterForRecyclerView<Data, Holder extends BaseViewHolder> extends RecyclerView.Adapter<Holder> implements View.OnClickListener {
    public List<Data> datas = new ArrayList<>();
    public Context mContext;
    public LayoutInflater inflater;
//    private OnItemClickListener mItemClickListener;

    public BaseAdapterForRecyclerView(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }
//    public void setItemClickListener(OnItemClickListener itemClickListener) {
//        mItemClickListener = itemClickListener;
//    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(getLayoutId(), parent,false);
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        view.setLayoutParams(params);
        Holder holder = getHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    public abstract Holder getHolder(View view);


    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Data bean = datas.get(position);
        if (bean != null) {
            getView(position, holder, bean);
        }
        holder.itemView.setTag(position);
    }

    public abstract void getView(int position, Holder holder, Data bean);

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public abstract int getLayoutId();

    public void setDatas(List<Data> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

//    @Override
//    public void onClick(View v) {
//       if( mItemClickListener!=null){
//            mItemClickListener.onItemClick((Integer) v.getTag());
//        }
//
//    }
}
