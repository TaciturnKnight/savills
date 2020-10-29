package com.bamboo.savills.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bamboo.savills.R;
import com.bamboo.savills.adapter.EditPicColorAdapter;
import com.bamboo.savills.adapter.EditpicTextPageAdapter;
import com.bamboo.savills.base.view.ToastUtil;
import com.bamboo.savills.utils.EditPicHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.material.tabs.TabLayout;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import cn.forward.androids.utils.ImageUtils;
import cn.forward.androids.utils.LogUtil;
import cn.forward.androids.utils.StatusBarUtil;
import cn.forward.androids.utils.Util;
import cn.hzw.doodle.DoodleBitmap;
import cn.hzw.doodle.DoodleColor;
import cn.hzw.doodle.DoodleOnTouchGestureListener;
import cn.hzw.doodle.DoodleParams;
import cn.hzw.doodle.DoodlePath;
import cn.hzw.doodle.DoodlePen;
import cn.hzw.doodle.DoodleShape;
import cn.hzw.doodle.DoodleText;
import cn.hzw.doodle.DoodleTouchDetector;
import cn.hzw.doodle.DoodleView;
import cn.hzw.doodle.IDoodleListener;
import cn.hzw.doodle.core.IDoodle;
import cn.hzw.doodle.core.IDoodleColor;
import cn.hzw.doodle.core.IDoodleItem;
import cn.hzw.doodle.core.IDoodleItemListener;
import cn.hzw.doodle.core.IDoodlePen;
import cn.hzw.doodle.core.IDoodleSelectableItem;
import cn.hzw.doodle.core.IDoodleShape;
import cn.hzw.doodle.core.IDoodleTouchDetector;
import cn.hzw.doodle.dialog.DialogController;
import cn.hzw.doodle.imagepicker.ImageSelectorView;

/**
 * 涂鸦界面，根据DoodleView的接口，提供页面交互
 * （这边代码和ui比较粗糙，主要目的是告诉大家DoodleView的接口具体能实现什么功能，实际需求中的ui和交互需另提别论）
 * Created by huangziwei(154330138@qq.com) on 2016/9/3.
 */
public abstract class DoodleActivity extends Activity {

    public static final String TAG = "Doodle";
    public final static int DEFAULT_MOSAIC_SIZE = 20; // 默认马赛克大小
    public final static int DEFAULT_COPY_SIZE = 20; // 默认仿制大小
    public final static int DEFAULT_TEXT_SIZE = 18; // 默认文字大小
    public final static int DEFAULT_BITMAP_SIZE = 80; // 默认贴图大小

    public static final int RESULT_ERROR = -111; // 出现错误
    public TabLayout tabLayout;
    public ViewPager viewPager;


    /**
     * 启动涂鸦界面
     *
     * @param activity
     * @param params      涂鸦参数
     * @param requestCode startActivityForResult的请求码
     * @see DoodleParams
     */
    public static void startActivityForResult(Activity activity, DoodleParams params, int requestCode) {
        Intent intent = new Intent(activity, DoodleActivity.class);
        intent.putExtra(DoodleActivity.KEY_PARAMS, params);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 启动涂鸦界面
     *
     * @param activity
     * @param imagePath   　图片路径
     * @param savePath    　保存路径
     * @param isDir       　保存路径是否为目录
     * @param requestCode 　startActivityForResult的请求码
     */
    @Deprecated
    public static void startActivityForResult(Activity activity, String imagePath, String savePath, boolean isDir, int requestCode) {
        DoodleParams params = new DoodleParams();
        params.mImagePath = imagePath;
        params.mSavePath = savePath;
        params.mSavePathIsDir = isDir;
        startActivityForResult(activity, params, requestCode);
    }

    /**
     * {@link DoodleActivity#startActivityForResult(Activity, String, String, boolean, int)}
     */
    @Deprecated
    public static void startActivityForResult(Activity activity, String imagePath, int requestCode) {
        DoodleParams params = new DoodleParams();
        params.mImagePath = imagePath;
        startActivityForResult(activity, params, requestCode);
    }

    public static final String KEY_PARAMS = "key_doodle_params";
    public static final String KEY_IMAGE_PATH = "key_image_path";

    public String mImagePath;

    public FrameLayout mFrameLayout;
    public IDoodle mDoodle;
    public DoodleView mDoodleView;

    public TextView mPaintSizeView;

    public View mSettingsPanel;
    public View mBtnColor, mColorContainer;
    public SeekBar mEditSizeSeekBar;
    public View mPenContainer, mSizeContainer;
    public View mBtnUndo;
    public View mRedoBtn;

    public AlphaAnimation mViewShowAnimation, mViewHideAnimation; // view隐藏和显示时用到的渐变动画

    public DoodleParams mDoodleParams;

    // 触摸屏幕超过一定时间才判断为需要隐藏设置面板
    public Runnable mHideDelayRunnable;
    // 触摸屏幕超过一定时间才判断为需要显示设置面板
    public Runnable mShowDelayRunnable;

    public DoodleOnTouchGestureListener mTouchGestureListener;
    public Map<IDoodlePen, Float> mPenSizeMap = new HashMap<>(); //保存每个画笔对应的最新大小

    public int mMosaicLevel = -1;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_PARAMS, mDoodleParams);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        mDoodleParams = savedInstanceState.getParcelable(KEY_PARAMS);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarTranslucent(this, true, false);
        if (mDoodleParams == null) {
            mDoodleParams = EditPicHelper.getInstance().editParams;
        }
        if (mDoodleParams == null) {
            LogUtil.e("TAG", "mDoodleParams is null!");
            this.finish();
            return;
        }

        mImagePath = mDoodleParams.mImagePath;
        if (mImagePath == null) {
            LogUtil.e("TAG", "mImagePath is null!");
            this.finish();
            return;
        }

        LogUtil.d("TAG", mImagePath);
        if (mDoodleParams.mIsFullScreen) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        Bitmap bitmap = EditPicHelper.getInstance().bitmap;
        if (bitmap == null) {
            LogUtil.e("TAG", "bitmap is null!");
            this.finish();
            return;
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.doodle_layout);
        mFrameLayout = (FrameLayout) findViewById(R.id.doodle_container);

        /*
        Whether or not to optimize drawing, it is suggested to open, which can optimize the drawing speed and performance.
        Note: When item is selected for editing after opening, it will be drawn at the top level, and not at the corresponding level until editing is completed.
        是否优化绘制，建议开启，可优化绘制速度和性能.
        注意：开启后item被选中编辑时时会绘制在最上面一层，直到结束编辑后才绘制在相应层级
         */
        mDoodle = mDoodleView = new DoodleViewWrapper(this, bitmap, mDoodleParams.mOptimizeDrawing, new IDoodleListener() {
            @Override
            public void onSaved(IDoodle doodle, Bitmap bitmap, Runnable callback) { // 保存图片为jpg格式
                File doodleFile = null;
                File file = null;
                String savePath = mDoodleParams.mSavePath;
                boolean isDir = mDoodleParams.mSavePathIsDir;
                if (TextUtils.isEmpty(savePath)) {
                    File dcimFile = new File(Environment.getExternalStorageDirectory(), "DCIM");
                    doodleFile = new File(dcimFile, "Doodle");
                    //　保存的路径
                    file = new File(doodleFile, System.currentTimeMillis() + ".png");
                } else {
                    if (isDir) {
                        doodleFile = new File(savePath);
                        //　保存的路径
                        file = new File(doodleFile, System.currentTimeMillis() + ".png");
                    } else {
                        file = new File(savePath);
                        doodleFile = file.getParentFile();
                    }
                }
                doodleFile.mkdirs();

                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    ImageUtils.addImage(getContentResolver(), file.getAbsolutePath());
                    Intent intent = new Intent();
                    intent.putExtra(KEY_IMAGE_PATH, file.getAbsolutePath());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                    onError(DoodleView.ERROR_SAVE, e.getMessage());
                } finally {
                    Util.closeQuietly(outputStream);
                    callback.run();
                }
            }

            public void onError(int i, String msg) {
                setResult(RESULT_ERROR);
                finish();
            }

            @Override
            public void onReady(IDoodle doodle) {
                mEditSizeSeekBar.setMax(Math.min(mDoodleView.getWidth(), mDoodleView.getHeight()));

                float size = mDoodleParams.mPaintUnitSize > 0 ? mDoodleParams.mPaintUnitSize * mDoodle.getUnitSize() : 0;
                if (size <= 0) {
                    size = mDoodleParams.mPaintPixelSize > 0 ? mDoodleParams.mPaintPixelSize : mDoodle.getSize();
                }
                // 设置初始值
                mDoodle.setSize(size);
                // 选择画笔
                mDoodle.setPen(DoodlePen.BRUSH);
                mDoodle.setShape(DoodleShape.HAND_WRITE);
                mDoodle.setColor(new DoodleColor(mDoodleParams.mPaintColor));
                if (mDoodleParams.mZoomerScale <= 0) {
                    findViewById(R.id.btn_zoomer).setVisibility(View.GONE);
                }
                mDoodle.setZoomerScale(mDoodleParams.mZoomerScale);
                mTouchGestureListener.setSupportScaleItem(mDoodleParams.mSupportScaleItem);

                // 每个画笔的初始值
                mPenSizeMap.put(DoodlePen.BRUSH, mDoodle.getSize());
                mPenSizeMap.put(DoodlePen.MOSAIC, DEFAULT_MOSAIC_SIZE * mDoodle.getUnitSize());
                mPenSizeMap.put(DoodlePen.COPY, DEFAULT_COPY_SIZE * mDoodle.getUnitSize());
                mPenSizeMap.put(DoodlePen.ERASER, mDoodle.getSize());
                mPenSizeMap.put(DoodlePen.TEXT, DEFAULT_TEXT_SIZE * mDoodle.getUnitSize());
                mPenSizeMap.put(DoodlePen.BITMAP, DEFAULT_BITMAP_SIZE * mDoodle.getUnitSize());
            }
        });

        mTouchGestureListener = new DoodleOnTouchGestureListener(mDoodleView, new DoodleOnTouchGestureListener.ISelectionListener() {
            // save states before being selected
            IDoodlePen mLastPen = null;
            IDoodleColor mLastColor = null;
            Float mSize = null;

            IDoodleItemListener mIDoodleItemListener = new IDoodleItemListener() {
                @Override
                public void onPropertyChanged(int property) {
                    if (mTouchGestureListener.getSelectedItem() == null) {
                        return;
                    }
                }
            };

            @Override
            public void onSelectedItem(IDoodle doodle, IDoodleSelectableItem selectableItem, boolean selected) {
                if (selected) {
                    if (mLastPen == null) {
                        mLastPen = mDoodle.getPen();
                    }
                    if (mLastColor == null) {
                        mLastColor = mDoodle.getColor();
                    }
                    if (mSize == null) {
                        mSize = mDoodle.getSize();
                    }
                    mDoodleView.setEditMode(true);
                    mDoodle.setPen(selectableItem.getPen());
                    mDoodle.setColor(selectableItem.getColor());
                    mDoodle.setSize(selectableItem.getSize());
                    mEditSizeSeekBar.setProgress((int) selectableItem.getSize());
                    mSizeContainer.setVisibility(View.VISIBLE);
                    selectableItem.addItemListener(mIDoodleItemListener);
                } else {
                    selectableItem.removeItemListener(mIDoodleItemListener);

                    if (mTouchGestureListener.getSelectedItem() == null) { // nothing is selected. 当前没有选中任何一个item
                        if (mLastPen != null) {
                            mDoodle.setPen(mLastPen);
                            mLastPen = null;
                        }
                        if (mLastColor != null) {
                            mDoodle.setColor(mLastColor);
                            mLastColor = null;
                        }
                        if (mSize != null) {
                            mDoodle.setSize(mSize);
                            mSize = null;
                        }
                    }
                }
            }

            @Override
            public void onCreateSelectableItem(IDoodle doodle, float x, float y) {
                if (mDoodle.getPen() == DoodlePen.TEXT) {
                    createDoodleText(null, x, y);
                } else if (mDoodle.getPen() == DoodlePen.BITMAP) {
                    createDoodleBitmap(null, x, y);
                }
            }
        }) {
            @Override
            public void setSupportScaleItem(boolean supportScaleItem) {
                super.setSupportScaleItem(supportScaleItem);
            }
        };

        IDoodleTouchDetector detector = new DoodleTouchDetector(getApplicationContext(), mTouchGestureListener);
        mDoodleView.setDefaultTouchDetector(detector);

        mDoodle.setIsDrawableOutside(mDoodleParams.mIsDrawableOutside);
        mFrameLayout.addView(mDoodleView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mDoodle.setDoodleMinScale(mDoodleParams.mMinScale);
        mDoodle.setDoodleMaxScale(mDoodleParams.mMaxScale);

        initView();
    }

    public boolean canChangeColor(IDoodlePen pen) {
        return pen != DoodlePen.ERASER
                && pen != DoodlePen.BITMAP
                && pen != DoodlePen.COPY
                && pen != DoodlePen.MOSAIC;
    }

    // 添加文字
    public void createDoodleText(final DoodleText doodleText, final float x, final float y) {
        if (isFinishing()) {
            return;
        }
        DialogController.showInputTextDialog(this, doodleText == null ? null : doodleText.getText(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = (v.getTag() + "").trim();
                if (TextUtils.isEmpty(text)) {
                    return;
                }
                if (doodleText == null) {
                    addText(text);
//                    IDoodleSelectableItem item = new DoodleText(mDoodle, text, mDoodle.getSize(), mDoodle.getColor().copy(), x, y);
//                    mDoodle.addItem(item);
//                    mTouchGestureListener.setSelectedItem(item);
                } else {
                    doodleText.setText(text);
                }
                mDoodle.refresh();
            }
        }, null).findViewById(cn.hzw.doodle.R.id.doodle_selectable_edit).requestFocus();
        if (doodleText == null) {
            mSettingsPanel.removeCallbacks(mHideDelayRunnable);
        }
//        EditPicHelper.getInstance().addText(doodleText.getText());
    }

    public void setBitmapBGColor(Bitmap bitmap, int color) {
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                bitmap.setPixel(i, j, color);//将bitmap的每个像素点都设置成相应的颜色
            }
        }
    }

    public static void showSoftInput(final View view) {
        if (view == null || view.getContext() == null) return;
        final InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        view.requestFocus();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
            }
        }, 200L);
    }

    // 添加贴图
    public void createDoodleBitmap(final DoodleBitmap doodleBitmap, final float x, final float y) {
        DialogController.showSelectImageDialog(this, new ImageSelectorView.ImageSelectorListener() {
            @Override
            public void onCancel() {
            }

            @Override
            public void onEnter(List<String> pathList) {
                Bitmap bitmap = ImageUtils.createBitmapFromPath(pathList.get(0), mDoodleView.getWidth() / 4, mDoodleView.getHeight() / 4);

                if (doodleBitmap == null) {
                    IDoodleSelectableItem item = new DoodleBitmap(mDoodle, bitmap, mDoodle.getSize(), x, y);
                    mDoodle.addItem(item);
                    mTouchGestureListener.setSelectedItem(item);
                } else {
                    doodleBitmap.setBitmap(bitmap);
                }
                mDoodle.refresh();
            }
        });
    }

    //++++++++++++++++++以下为一些初始化操作和点击监听+++++++++++++++++++++++++++++++++++++++++
    public RecyclerView colorRecyclerView;
    public EditPicColorAdapter colorAdapter;

    //
    public void initView() {
        TextView revoke = findViewById(R.id.editpic_revoke);
        revoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //撤回
                mDoodle.undo();
            }
        });
        tabLayout = findViewById(R.id.editpic_tablayout);
        viewPager = findViewById(R.id.editpic_viewpager);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.addTab(tabLayout.newTab().setText("Residential"));
//        tabLayout.addTab(tabLayout.newTab().setText("Office"));
//        tabLayout.addTab(tabLayout.newTab().setText("Industrial"));
//        tabLayout.addTab(tabLayout.newTab().setText("House"));
        EditpicTextPageAdapter textPageAdapter = new EditpicTextPageAdapter(this, new EditpicTextPageAdapter.OnTextClickListener() {
            @Override
            public void onTextClick(String text) {
                //添加文字
                addText(text);
            }
        });
        viewPager.setAdapter(textPageAdapter);
        List<List<String>> textDatas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<String> texts = new ArrayList<>();
            texts.add("L/D");
            texts.add("BR");
            texts.add("Bath");
            texts.add("Kit");
            texts.add("SerR");
            texts.add("BaL");
            texts.add("Serlav");
            texts.add("Lav");
            texts.add("RF");
            texts.add("FR");
            texts.add("VR");
            texts.add("No Insp");
            textDatas.add(texts);
        }
        textPageAdapter.setDatas(textDatas);
        tabLayout.getTabAt(0).setText("Residential");
        tabLayout.getTabAt(1).setText("Office");
        tabLayout.getTabAt(2).setText("Industrial");
        tabLayout.getTabAt(3).setText("House");
        colorRecyclerView = findViewById(R.id.editpic_color_recycler);
        colorRecyclerView.setLayoutManager(new GridLayoutManager(this, EditPicHelper.getInstance().colors.size(), LinearLayoutManager.VERTICAL, false));
        colorAdapter = new EditPicColorAdapter();
        colorRecyclerView.setAdapter(colorAdapter);
        colorAdapter.setNewData(EditPicHelper.getInstance().colors);
        colorAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                onSelectColor(EditPicHelper.getInstance().colors.get(position));
            }
        });
        mBtnUndo = findViewById(R.id.btn_undo);
        mBtnUndo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!(DoodleParams.getDialogInterceptor() != null
                        && DoodleParams.getDialogInterceptor().onShow(DoodleActivity.this, mDoodle, DoodleParams.DialogType.CLEAR_ALL))) {
                    DialogController.showEnterCancelDialog(DoodleActivity.this,
                            getString(R.string.doodle_clear_screen), getString(R.string.doodle_cant_undo_after_clearing),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mDoodle.clear();
                                }
                            }, null
                    );
                }
                return true;
            }
        });

        mSettingsPanel = findViewById(R.id.doodle_panel);


        mPaintSizeView = (TextView) findViewById(R.id.paint_size_text);
        mPenContainer = findViewById(R.id.pen_container);
        mSizeContainer = findViewById(R.id.size_container);
        mRedoBtn = findViewById(R.id.btn_redo);

        mBtnColor = DoodleActivity.this.findViewById(R.id.btn_set_color);
        mColorContainer = DoodleActivity.this.findViewById(R.id.btn_set_color_container);
        mEditSizeSeekBar = (SeekBar) findViewById(R.id.doodle_seekbar_size);
        mEditSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress <= 0) {
                    mEditSizeSeekBar.setProgress(1);
                    return;
                }
                if ((int) mDoodle.getSize() == progress) {
                    return;
                }
                onSizeChange(progress);
                if (mTouchGestureListener.getSelectedItem() != null) {
                    mTouchGestureListener.getSelectedItem().setSize(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

//        mDoodleView.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // 隐藏设置面板
//                if (!mBtnHidePanel.isSelected()  // 设置面板没有被隐藏
//                        && mDoodleParams.mChangePanelVisibilityDelay > 0) {
//                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
//                        case MotionEvent.ACTION_DOWN:
//                            mSettingsPanel.removeCallbacks(mHideDelayRunnable);
//                            mSettingsPanel.removeCallbacks(mShowDelayRunnable);
//                            //触摸屏幕超过一定时间才判断为需要隐藏设置面板
//                            mSettingsPanel.postDelayed(mHideDelayRunnable, mDoodleParams.mChangePanelVisibilityDelay);
//                            break;
//                        case MotionEvent.ACTION_CANCEL:
//                        case MotionEvent.ACTION_UP:
//                            mSettingsPanel.removeCallbacks(mHideDelayRunnable);
//                            mSettingsPanel.removeCallbacks(mShowDelayRunnable);
//                            //离开屏幕超过一定时间才判断为需要显示设置面板
//                            mSettingsPanel.postDelayed(mShowDelayRunnable, mDoodleParams.mChangePanelVisibilityDelay);
//                            break;
//                    }
//                }
//
//                return false;
//            }
//        });

        // 长按标题栏显示原图
        mViewShowAnimation = new AlphaAnimation(0, 1);
        mViewShowAnimation.setDuration(150);
        mViewHideAnimation = new AlphaAnimation(1, 0);
        mViewHideAnimation.setDuration(150);
        mHideDelayRunnable = new Runnable() {
            public void run() {
                hideView(mSettingsPanel);
            }

        };
        mShowDelayRunnable = new Runnable() {
            public void run() {
                showView(mSettingsPanel);
            }
        };
    }

    public void addText(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        IDoodleSelectableItem item = new DoodleText(mDoodle, text, 100, mDoodle.getColor().copy(), EditPicHelper.getInstance().bitmap.getWidth() / 2, EditPicHelper.getInstance().bitmap.getHeight() / 2);
        mDoodle.addItem(item);
        mTouchGestureListener.setSelectedItem(item);
        mDoodle.refresh();
    }

    public ValueAnimator mRotateAnimator;

    public abstract void onSelectColor(String color);

    public abstract void onSizeChange(int size);

    public void onClick(final View v) {
        if (v.getId() == R.id.btn_pen_hand) {
            mDoodle.setPen(DoodlePen.BRUSH);
        } else if (v.getId() == R.id.btn_pen_text) {
            mDoodle.setPen(DoodlePen.TEXT);
        } else if (v.getId() == R.id.doodle_btn_brush_edit) {
            mDoodleView.setEditMode(!mDoodleView.isEditMode());
        } else if (v.getId() == R.id.btn_undo) {
            mDoodle.undo();
        } else if (v.getId() == R.id.btn_zoomer) {
            mDoodleView.enableZoomer(!mDoodleView.isEnableZoomer());
        } else if (v.getId() == R.id.btn_set_color_container) {
//            DoodleColor color = null;
//            if (mDoodle.getColor() instanceof DoodleColor) {
//                color = (DoodleColor) mDoodle.getColor();
//            }
//            if (color == null) {
//                return;
//            }
//            if (!(DoodleParams.getDialogInterceptor() != null
//                    && DoodleParams.getDialogInterceptor().onShow(DoodleActivity.this, mDoodle, DoodleParams.DialogType.COLOR_PICKER))) {
//                boolean fullScreen = (getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
//                int themeId;
//                if (fullScreen) {
//                    themeId = android.R.style.Theme_Translucent_NoTitleBar_Fullscreen;
//                } else {
//                    themeId = android.R.style.Theme_Translucent_NoTitleBar;
//                }
//                new ColorPickerDialog(DoodleActivity.this,
//                        new ColorPickerDialog.OnColorChangedListener() {
//                            public void colorChanged(int color, int size) {
//                                mDoodle.setColor(new DoodleColor(color));
//                                mDoodle.setSize(size);
//                            }
//
//                            @Override
//                            public void colorChanged(Drawable color, int size) {
//                                Bitmap bitmap = ImageUtils.getBitmapFromDrawable(color);
//                                mDoodle.setColor(new DoodleColor(bitmap));
//                                mDoodle.setSize(size);
//                            }
//                        }, themeId).show(mDoodleView, mBtnColor.getBackground(), Math.min(mDoodleView.getWidth(), mDoodleView.getHeight()));
//            }
        } else if (v.getId() == R.id.doodle_btn_finish) {
            mDoodle.save();
        } else if (v.getId() == R.id.doodle_btn_back) {
            if (mDoodle.getAllItem() == null || mDoodle.getItemCount() == 0) {
                finish();
                return;
            }
            if (!(DoodleParams.getDialogInterceptor() != null
                    && DoodleParams.getDialogInterceptor().onShow(DoodleActivity.this, mDoodle, DoodleParams.DialogType.SAVE))) {
                DialogController.showMsgDialog(DoodleActivity.this, getString(R.string.doodle_saving_picture), null, getString(R.string.doodle_cancel),
                        getString(R.string.doodle_save), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDoodle.save();
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        });
            }
        } else if (v.getId() == R.id.doodle_btn_rotate) {
            // 旋转图片
            if (mRotateAnimator == null) {
                mRotateAnimator = new ValueAnimator();
                mRotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();
                        mDoodle.setDoodleRotation(value);
                    }
                });
                mRotateAnimator.setDuration(250);
            }
            if (mRotateAnimator.isRunning()) {
                return;
            }
            mRotateAnimator.setIntValues(mDoodle.getDoodleRotation(), mDoodle.getDoodleRotation() + 90);
            mRotateAnimator.start();
        } else if (v.getId() == R.id.doodle_selectable_edit) {
            if (mTouchGestureListener.getSelectedItem() instanceof DoodleText) {
                createDoodleText((DoodleText) mTouchGestureListener.getSelectedItem(), -1, -1);
            } else if (mTouchGestureListener.getSelectedItem() instanceof DoodleBitmap) {
                createDoodleBitmap((DoodleBitmap) mTouchGestureListener.getSelectedItem(), -1, -1);
            }
        } else if (v.getId() == R.id.btn_redo) {
            if (!mDoodle.redo(1)) {
                mRedoBtn.setVisibility(View.GONE);
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDoodleView.isEditMode()) {
                mDoodleView.setEditMode(false);
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() { // 返回键监听
        findViewById(R.id.doodle_btn_back).performClick();
    }

    public void showView(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            return;
        }

        view.clearAnimation();
        view.startAnimation(mViewShowAnimation);
        view.setVisibility(View.VISIBLE);
    }

    public void hideView(View view) {
        if (view.getVisibility() != View.VISIBLE) {
            return;
        }
        view.clearAnimation();
        view.startAnimation(mViewHideAnimation);
        view.setVisibility(View.GONE);
    }

    /**
     * 包裹DoodleView，监听相应的设置接口，以改变UI状态
     */
    public class DoodleViewWrapper extends DoodleView {

        public DoodleViewWrapper(Context context, Bitmap bitmap, boolean optimizeDrawing, IDoodleListener listener) {
            super(context, bitmap, optimizeDrawing, listener);
        }

        public Map<IDoodlePen, Integer> mBtnPenIds = new HashMap<>();

        {
            mBtnPenIds.put(DoodlePen.BRUSH, R.id.btn_pen_hand);
            mBtnPenIds.put(DoodlePen.TEXT, R.id.btn_pen_text);
        }

        @Override
        public void setPen(IDoodlePen pen) {
            IDoodlePen oldPen = getPen();
            super.setPen(pen);
            if (pen == DoodlePen.BITMAP || pen == DoodlePen.TEXT) {
                if (pen == DoodlePen.BITMAP) {
                    mColorContainer.setVisibility(GONE);
                } else {
                    mColorContainer.setVisibility(VISIBLE);
                }
            } else if (pen == DoodlePen.MOSAIC) {
                mColorContainer.setVisibility(GONE);
            } else {
                if (pen == DoodlePen.COPY || pen == DoodlePen.ERASER) {
                    mColorContainer.setVisibility(GONE);
                } else {
                    mColorContainer.setVisibility(VISIBLE);
                }
            }
            setSingleSelected(mBtnPenIds.values(), mBtnPenIds.get(pen));

            if (mTouchGestureListener.getSelectedItem() == null) {
                mPenSizeMap.put(oldPen, getSize()); // save
                Float size = mPenSizeMap.get(pen); // restore
                if (size != null) {
                    mDoodle.setSize(size);
                }
                if (isEditMode()) {
                    mColorContainer.setVisibility(GONE);
                }
            } else {
                return;
            }

        }

        public Map<IDoodleShape, Integer> mBtnShapeIds = new HashMap<>();

        @Override
        public void setShape(IDoodleShape shape) {
            super.setShape(shape);
        }

        TextView mPaintSizeView = (TextView) DoodleActivity.this.findViewById(R.id.paint_size_text);

        @Override
        public void setSize(float paintSize) {
            super.setSize(paintSize);
//            mEditSizeSeekBar.setProgress((int) paintSize);
            mPaintSizeView.setText("" + (int) paintSize);

            if (mTouchGestureListener.getSelectedItem() != null) {
                mTouchGestureListener.getSelectedItem().setSize(getSize());
            }
        }

        @Override
        public void setColor(IDoodleColor color) {
            IDoodlePen pen = getPen();
            super.setColor(color);

            DoodleColor doodleColor = null;
            if (color instanceof DoodleColor) {
                doodleColor = (DoodleColor) color;
            }
            if (doodleColor != null
                    && canChangeColor(pen)) {
                if (doodleColor.getType() == DoodleColor.Type.COLOR) {
                    mBtnColor.setBackgroundColor(doodleColor.getColor());
                } else if (doodleColor.getType() == DoodleColor.Type.BITMAP) {
                    mBtnColor.setBackgroundDrawable(new BitmapDrawable(doodleColor.getBitmap()));
                }

                if (mTouchGestureListener.getSelectedItem() != null) {
                    mTouchGestureListener.getSelectedItem().setColor(getColor().copy());
                }
            }

        }

        @Override
        public void enableZoomer(boolean enable) {
            super.enableZoomer(enable);
            DoodleActivity.this.findViewById(R.id.btn_zoomer).setSelected(enable);
            if (enable) {
                Toast.makeText(DoodleActivity.this, "x" + mDoodleParams.mZoomerScale, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public boolean undo() {
            mTouchGestureListener.setSelectedItem(null);
            boolean res = super.undo();
            if (getRedoItemCount() > 0) {
                mRedoBtn.setVisibility(VISIBLE);
            } else {
                mRedoBtn.setVisibility(GONE);
            }
            return res;
        }

        @Override
        public void clear() {
            super.clear();
            mTouchGestureListener.setSelectedItem(null);
            mRedoBtn.setVisibility(GONE);
        }

        @Override
        public void addItem(IDoodleItem item) {
            super.addItem(item);
            if (getRedoItemCount() > 0) {
                mRedoBtn.setVisibility(VISIBLE);
            } else {
                mRedoBtn.setVisibility(GONE);
            }
        }

        View mBtnEditMode = DoodleActivity.this.findViewById(R.id.doodle_btn_brush_edit);
        Boolean mLastIsDrawableOutside = null;

        @Override
        public void setEditMode(boolean editMode) {
            if (editMode == isEditMode()) {
                return;
            }

            super.setEditMode(editMode);
            mBtnEditMode.setSelected(editMode);
            if (editMode) {
                Toast.makeText(DoodleActivity.this, R.string.doodle_edit_mode, Toast.LENGTH_SHORT).show();
                mLastIsDrawableOutside = mDoodle.isDrawableOutside(); // save
                mDoodle.setIsDrawableOutside(true);
                mPenContainer.setVisibility(GONE);
                mSizeContainer.setVisibility(GONE);
                mColorContainer.setVisibility(GONE);
                mBtnUndo.setVisibility(GONE);
            } else {
                if (mLastIsDrawableOutside != null) { // restore
                    mDoodle.setIsDrawableOutside(mLastIsDrawableOutside);
                }
                mTouchGestureListener.center(); // center picture
                if (mTouchGestureListener.getSelectedItem() == null) { // restore
                    setPen(getPen());
                }
                mTouchGestureListener.setSelectedItem(null);
                mPenContainer.setVisibility(VISIBLE);
                mSizeContainer.setVisibility(VISIBLE);
                mBtnUndo.setVisibility(VISIBLE);
            }
        }

        public void setSingleSelected(Collection<Integer> ids, int selectedId) {
            for (int id : ids) {
                if (id == selectedId) {
                    DoodleActivity.this.findViewById(id).setSelected(true);
                } else {
                    DoodleActivity.this.findViewById(id).setSelected(false);
                }
            }
        }
    }

}
