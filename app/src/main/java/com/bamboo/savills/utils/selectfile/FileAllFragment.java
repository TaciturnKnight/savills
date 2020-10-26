package com.bamboo.savills.utils.selectfile;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bamboo.savills.R;
import com.bamboo.savills.utils.FileEntity;
import com.bamboo.savills.utils.FileUtils;
import com.bamboo.savills.utils.OnUpdateDataListener;
import com.bamboo.savills.utils.PickerManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者：chs on 2017-08-24 11:05
 * 邮箱：657083984@qq.com
 * 全部文件
 */

public class FileAllFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TextView mEmptyView;
    private LinearLayout tv_back;
    private String mPath;
    private String rootPath;
    private List<FileEntity> mListFiles;
    private FileSelectFilter mFilter;
    //筛选类型条件
    private String[] mFileTypes = new String[]{"jpg", "jpeg", "png", "mp4"};
    private AllFileAdapter mAllFileAdapter;
    private OnUpdateDataListener mOnUpdateDataListener;

    public void setOnUpdateDataListener(OnUpdateDataListener onUpdateDataListener) {
        mOnUpdateDataListener = onUpdateDataListener;
    }

    public static FileAllFragment newInstance() {
        return new FileAllFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file_all, null);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initView(View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rl_all_file);
        mRecyclerView.setLayoutManager(layoutManager);
        mEmptyView = (TextView) view.findViewById(R.id.empty_view);
        tv_back = view.findViewById(R.id.tv_back);
    }

    private void initData() {
        getData();
    }

    private void getData() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(getContext(), R.string.not_available, Toast.LENGTH_SHORT).show();
            return;
        }
        mPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFilter = new FileSelectFilter(mFileTypes);
        mListFiles = getFileList(mPath);
        mAllFileAdapter = new AllFileAdapter(getContext(), mListFiles, mFilter);
        mRecyclerView.setAdapter(mAllFileAdapter);
    }

    private void initEvent() {
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        mAllFileAdapter.setOnItemClickListener(new OnFileItemClickListener() {
            @Override
            public void click(int position) {
                FileEntity entity = mListFiles.get(position);
                //如果是文件夹点击进入文件夹
                if (entity.getFile().isDirectory()) {
                    getIntoChildFolder(position);
                } else {
                    File file = entity.getFile();
                    ArrayList<FileEntity> files = PickerManager.getInstance().files;
                    if (files.contains(entity)) {
                        files.remove(entity);
                        if (mOnUpdateDataListener != null) {
                            mOnUpdateDataListener.update(-file.length());
                        }
                        mAllFileAdapter.notifyDataSetChanged();
                    } else {
                        if (PickerManager.getInstance().files.size() < PickerManager.getInstance().maxCount) {
                            files.add(entity);
                            getActivity().setResult(Activity.RESULT_OK);
                            getActivity().finish();
//                            if (mOnUpdateDataListener != null) {
//                                mOnUpdateDataListener.update(file.length());
//                            }
//                            entity.setSelected(!entity.isSelected());
//                            mAllFileAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), getString(R.string.file_select_max, PickerManager.getInstance().maxCount), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    //进入子文件夹
    private void getIntoChildFolder(int position) {
        mPath = mListFiles.get(position).getFile().getAbsolutePath();
        //更新数据源
        mListFiles = getFileList(mPath);
        mAllFileAdapter.updateListData(mListFiles);
        mAllFileAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(0);
    }

    /**
     * 根据地址获取当前地址下的所有目录和文件，并且排序
     *
     * @param path
     * @return List<File>
     */
    private List<FileEntity> getFileList(String path) {
        List<FileEntity> fileListByDirPath = FileUtils.getFileListByDirPath(path, mFilter);
        if (fileListByDirPath.size() > 0) {
            mEmptyView.setVisibility(View.GONE);
        } else {
            mEmptyView.setVisibility(View.VISIBLE);
        }
        return fileListByDirPath;
    }

    public void back() {
        String tempPath = new File(mPath).getParent();
        if (tempPath == null || mPath.equals(rootPath)) {
            getActivity().finish();
        } else {
            mPath = tempPath;
            mListFiles = getFileList(mPath);
            mAllFileAdapter.updateListData(mListFiles);
            mAllFileAdapter.notifyDataSetChanged();
        }
    }
}
