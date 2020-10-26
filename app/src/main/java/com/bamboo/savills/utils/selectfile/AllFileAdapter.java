package com.bamboo.savills.utils.selectfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bamboo.savills.R;
import com.bamboo.savills.utils.FileEntity;
import com.bamboo.savills.utils.FileUtils;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者：chs on 2017-08-08 14:40
 * 邮箱：657083984@qq.com
 */

public class AllFileAdapter extends RecyclerView.Adapter<FilePickerViewHolder> {
    private List<FileEntity> mListData;
    private Context mContext;
    private FileFilter mFileFilter;
    private OnFileItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnFileItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AllFileAdapter(Context context, List<FileEntity> listData, FileFilter fileFilter) {
        mListData = listData;
        mContext = context;
        mFileFilter = fileFilter;
    }

    @Override
    public FilePickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_file_picker, parent, false);
        return new FilePickerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FilePickerViewHolder holder, int positon) {
        final FileEntity entity = mListData.get(positon);
        final File file = entity.getFile();
        if (file.isDirectory()) {
//            holder.ivType.setImageResource(R.drawable.icon_file_dir);
            holder.initDir();
        } else {
            holder.initFile();
            if (entity.getFileType() != null) {
                String title = entity.getFileType().getTitle();
                if (title.equals("IMG")) {
                    Glide.with(mContext).load(new File(entity.getPath())).into(holder.ivType);
                } else {
                    holder.ivType.setImageResource(entity.getFileType().getIconStyle());
                }
            } else {
                holder.ivType.setImageResource(R.drawable.file_picker_def);
            }
            holder.tvDetail.setText(FileUtils.getReadableFileSize(file.length()));
        }
        holder.tvName.setText(file.getName());
        holder.layoutRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.click(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    /**
     * 更新数据源
     *
     * @param mListData
     */
    public void updateListData(List<FileEntity> mListData) {
        this.mListData = mListData;
    }
}
