package com.bamboo.savills.utils.selectfile;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboo.savills.R;

import androidx.recyclerview.widget.RecyclerView;


/**
 * 作者：chs on 2017-08-24 17:13
 * 邮箱：657083984@qq.com
 */

public class FilePickerViewHolder extends RecyclerView.ViewHolder {
    protected FrameLayout layoutRoot;
    protected ImageView ivType;
    protected TextView tvName;
    protected TextView tvDetail;
    protected RelativeLayout dirLayout, fileLayout;

    public FilePickerViewHolder(View itemView) {
        super(itemView);
//        ivType = (ImageView) itemView.findViewById(R.id.iv_type);
        layoutRoot = itemView.findViewById(R.id.layout_item_root);
        dirLayout = itemView.findViewById(R.id.item_filepicker_dirrl);
        fileLayout = itemView.findViewById(R.id.item_filepicker_filerl);
//        tvName = (TextView) itemView.findViewById(R.id.tv_name);
//        tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
    }

    public void initDir() {
        tvName = itemView.findViewById(R.id.item_filetypelist_name);
        dirLayout.setVisibility(View.VISIBLE);
        fileLayout.setVisibility(View.GONE);
    }

    public void initFile() {
        ivType = itemView.findViewById(R.id.item_filelist_filepic);
        tvName = itemView.findViewById(R.id.item_filelist_filename);
        tvDetail = itemView.findViewById(R.id.item_filelist_time);
        fileLayout.setVisibility(View.VISIBLE);
        dirLayout.setVisibility(View.GONE);
    }
}
