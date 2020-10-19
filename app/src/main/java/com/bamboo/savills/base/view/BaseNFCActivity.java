package com.bamboo.savills.base.view;


import com.bamboo.savills.base.utils.NfcUtils;

public abstract class BaseNFCActivity extends BaseActivity {
    public NfcUtils utils;

    @Override
    protected void onResume() {
        super.onResume();
        utils = new NfcUtils(BaseNFCActivity.this);
        if (NfcUtils.mNfcAdapter != null) {
            NfcUtils.mNfcAdapter.enableForegroundDispatch(this, NfcUtils.mPendingIntent, NfcUtils.mIntentFilter, NfcUtils.mTechList);
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        if (NfcUtils.mNfcAdapter != null) {
            NfcUtils.mNfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NfcUtils.mNfcAdapter = null;
    }


}
