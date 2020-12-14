package com.bamboo.savills.base.view;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboo.savills.R;
import com.bamboo.savills.base.utils.StringUtil;

public class LoginFailDialog extends CustomDialog {
    private Context mContext;
    private TextView tvReason;
    private RelativeLayout rlOut;
    private LinearLayout llIn;
    public LoginFailDialog(Context context) {
        super(context, false);
        this.mContext = context;
    }
    public void setReason(String reason){
        if (tvReason!= null && StringUtil.isNotEmpty(reason)){
            tvReason.setText(reason);
        }
    }

    @Override
    protected void onCreateView(WindowManager windowManager) {
        setDialogWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setDialogHeight(WindowManager.LayoutParams.MATCH_PARENT);
        setContentView(R.layout.dialog_login_fail);
        setCanceledOnTouchOutside(false);
        tvReason = findViewById(R.id.tv_dialog_login_fail_reason);
        rlOut = findViewById(R.id.rl_dialog_login_fail_out);
        llIn = findViewById(R.id.ll_dialog_login_fail_in);
        rlOut.setOnClickListener(this);
        llIn.setOnClickListener(this);
    }

    @Override
    public void onClickNext(View v) {
        switch (v.getId()){
            case R.id.rl_dialog_login_fail_out:
                dismiss();
                break;
        }

    }
}
