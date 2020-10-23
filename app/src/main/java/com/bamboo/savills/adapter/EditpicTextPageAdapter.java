package com.bamboo.savills.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bamboo.savills.R;
import com.bamboo.savills.view.AutoSizeRecyclerview;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

/**
 * Created by tong on 2020/10/23.
 */
public class EditpicTextPageAdapter extends PagerAdapter {
    private List<List<String>> datas;
    private Context mContext;
    private OnTextClickListener listener;

    public EditpicTextPageAdapter(Context mContext, OnTextClickListener listener) {
        this.mContext = mContext;
        this.listener = listener;
    }

    public interface OnTextClickListener {
        public void onTextClick(String text);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.page_editpictext, null);
        AutoSizeRecyclerview recyclerView = view.findViewById(R.id.page_editpictext_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 6, RecyclerView.VERTICAL, false));
        EditpicTextAdapter adapter = new EditpicTextAdapter();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int index) {
                if (listener != null) {
                    listener.onTextClick(datas.get(position).get(index));
                }
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setNewData(datas.get(position));
        container.addView(view);
        return view;
    }

    public void setDatas(List<List<String>> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (datas == null)
            return 0;
        return datas.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
