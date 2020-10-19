package com.bamboo.savills.base.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qyj on 2019/9/29.
 */

public abstract class BaseAdapterForListView<Data, Holder extends BaseViewHolder> extends BaseAdapter {
    public List<Data> datas = new ArrayList<>();
    public LayoutInflater inflater;
    public Context mContext;

    public BaseAdapterForListView(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(getLayoutId(), null);
            holder = getHolder(convertView);
            convertView.setTag(holder);
        }
        holder = (Holder) convertView.getTag();
        Data bean = datas.get(position);
        if (bean != null) {
            getView(position, holder, bean);
        }
        return convertView;
    }

    public abstract Holder getHolder(View view);

    public abstract void getView(int position, Holder holder, Data bean);

    public abstract int getLayoutId();

    public void setDatas(List<Data> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }
}
