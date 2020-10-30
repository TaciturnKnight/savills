package com.bamboo.savills.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.view.View;
import android.widget.ImageView;

import com.bamboo.savills.Module.FileBean;
import com.bamboo.savills.R;
import com.bamboo.savills.activity.LoadFileByWebViewActivity;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by tong on 2020/10/30.
 */
public class FileListAdapter extends BaseQuickAdapter<FileBean, FileListAdapter.FilelistHolder> {
    public FileListAdapter() {
        super(R.layout.item_filelist);
    }

    @Override
    protected void convert(@NotNull FilelistHolder filelistHolder, FileBean fileBean) {
        filelistHolder.pic.setImageBitmap(fileBean.getBitmap());
        filelistHolder.videoTag.setVisibility(fileBean.isPic() ? View.GONE : View.VISIBLE);
        filelistHolder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoadFileByWebViewActivity.class);
                intent.putExtra("url", fileBean.getFilePath());
                intent.putExtra("ispic", fileBean.isPic());
                getContext().startActivity(intent);
            }
        });
        filelistHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext()).setMessage("确认删除该文件?").setNegativeButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        remove(fileBean);
                    }
                }).setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
            }
        });
    }

    //获取视频首帧
    public static Bitmap getVideoThumb(String path) {
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(path);
        return media.getFrameAtTime();
    }

    public class FilelistHolder extends BaseViewHolder {
        ImageView pic, videoTag, delete;

        public FilelistHolder(@NotNull View view) {
            super(view);
            pic = view.findViewById(R.id.show_img);
            videoTag = view.findViewById(R.id.show_videotag);
            delete = view.findViewById(R.id.show_delfile);
        }
    }
}
