package com.bway.caoyuan;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bway.caoyuan.base.WDActivity;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.exception.ApiException;
import com.bway.caoyuan.presenter.RegisterPresenter;
import com.bway.caoyuan.util.MD5Utils;
import com.bway.caoyuan.util.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReginActivity extends WDActivity {

    @BindView(R.id.te_jump)
    TextView mTeJump;
    @BindView(R.id.register_phone)
    EditText mRegisterPhone;
    @BindView(R.id.register_code)
    EditText mRegisterCode;
    @BindView(R.id.register_pwd)
    EditText mRegisterPwd;
    @BindView(R.id.bt_register)
    Button mBtRegister;
    private RegisterPresenter requestPresenter;

    @Override
    protected void initView() {
        requestPresenter = new RegisterPresenter(new RegisterCall());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_regin;
    }

    @Override
    protected void destoryData() {
        requestPresenter.unBind();

    }


    @OnClick({R.id.te_jump, R.id.register_phone, R.id.register_code, R.id.register_pwd, R.id.bt_register})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.te_jump:
                finish();
                break;

            case R.id.bt_register:
                String m = mRegisterPhone.getText().toString();
                String p = mRegisterPwd.getText().toString();
                if (TextUtils.isEmpty(m)){
                    UIUtils.showToastSafe("请输入正确的手机号");
                    return;
                }
                if (TextUtils.isEmpty(p)){
                    UIUtils.showToastSafe("请输入密码");
                    return;
                }
                mLoadDialog.show();
                requestPresenter.reqeust(m, MD5Utils.md5(p));
                break;
        }
    }

    private class RegisterCall implements DataCall<Result> {
        @Override
        public void success(Result result) {
            mLoadDialog.cancel();
            if (result.getStatus().equals("0000")){
                UIUtils.showToastSafe("注册成功，请登录");
                finish();
            }else{
                UIUtils.showToastSafe(result.getStatus()+"傻逼"+result.getMessage());
            }
        }

        @Override
        public void fail(ApiException e) {
            mLoadDialog.cancel();
            UIUtils.showToastSafe(e.getCode()+" 23"+e.getDisplayMessage());
        }
    }
}
