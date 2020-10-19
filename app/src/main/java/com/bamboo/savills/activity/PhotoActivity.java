package com.bamboo.savills.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bamboo.savills.R;
import com.bamboo.savills.base.view.BaseActivity;


import butterknife.InjectView;

public class PhotoActivity  extends BaseActivity {
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @InjectView(R.id.iv_back)
    ImageView ivBack;

    @InjectView(R.id.iv_send)
    ImageView ivSend;

    @Override
    public void onClickNext(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }

    }

    @Override
    public void initView() {
        tvTitle.setText("Photo");
        ivSend.setVisibility(View.GONE);

    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_photo;
    }
    //    private CustomHelper customHelper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        tvTitle.setText("Photo");
////        ivSend.setVisibility(View.GONE);
//        View contentView = LayoutInflater.from(this).inflate(R.layout.common_layout, null);
//        setContentView(contentView);
//        customHelper = CustomHelper.of(contentView);
////        ivBack.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                finish();
////            }
////        });
//    }
//
//    public void onClick(View view) {
//        customHelper.onClick(view, getTakePhoto());
//    }
//
//    @Override
//    public void takeCancel() {
//        super.takeCancel();
//    }
//
//    @Override
//    public void takeFail(TResult result, String msg) {
//        super.takeFail(result, msg);
//    }
//
//    @Override
//    public void takeSuccess(TResult result) {
//        super.takeSuccess(result);
//        showImg(result.getImages());
//    }
//
//    private void showImg(ArrayList<TImage> images) {
//        Intent intent = new Intent(this, ResultActivity.class);
//        intent.putExtra("images", images);
//        startActivity(intent);
//    }


}
