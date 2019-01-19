package com.bway.caoyuan;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bway.caoyuan.base.WDActivity;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.bean.ResultBean;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.db.DaoMaster;
import com.bway.caoyuan.db.ResultBeanDao;
import com.bway.caoyuan.exception.ApiException;
import com.bway.caoyuan.presenter.LoginPresenter;
import com.bway.caoyuan.util.LogUtils;
import com.bway.caoyuan.util.MD5Utils;
import com.bway.caoyuan.util.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends WDActivity {

    @BindView(R.id.phone1)
    ImageView mPhone1;
    @BindView(R.id.log)
    ImageView mLog;
    @BindView(R.id.show)
    ImageView mShow;
    @BindView(R.id.login_phone)
    EditText mLoginPhone;
    @BindView(R.id.login_pwd)
    EditText mLoginPwd;
    @BindView(R.id.check)
    CheckBox mCheck;
    @BindView(R.id.register_jump)
    TextView mRegisterJump;
    @BindView(R.id.login_bt)
    Button mLoginBt;
    private LoginPresenter requestPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;

    }

    @Override
    protected void destoryData() {

        requestPresenter.unBind();


    }

    @Override
    protected void initView() {

//        if (LOGIN_USER.getStatus()==1){
//            Intent intent = new Intent(this,ShowActivity.class);
//            startActivity(intent);
//            finish();
//        }

        mShow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    mLoginPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    mLoginPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());


                }
                return true;
            }
        });

        requestPresenter = new LoginPresenter(new LoginCall());
        boolean remPas = WDApplication.getShare().getBoolean("remPas", true);
        if (remPas) {
            mCheck.setChecked(true);
            mLoginPhone.setText(WDApplication.getShare().getString("mobile", ""));
            mLoginPwd.setText(WDApplication.getShare().getString("pas", ""));
        }


       }



    @OnClick({R.id.register_jump, R.id.login_bt, R.id.login_pwd, R.id.check})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.register_jump:
                intent(ReginActivity.class);

                break;
            case R.id.login_bt:
                String m = mLoginPhone.getText().toString();
                String p = mLoginPwd.getText().toString();
                if (TextUtils.isEmpty(m)) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(p)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_LONG).show();
                    return;
                }
                if (mCheck.isChecked()) {
                    WDApplication.getShare().edit().putString("mobile", m)
                            .putString("pas", p).commit();
                }
                mLoadDialog.show();
                requestPresenter.reqeust(m, MD5Utils.md5(p));

                break;
            case R.id.login_pwd:
                break;

        }
    }

    @OnClick(R.id.check)
    public void remPas() {
        WDApplication.getShare().edit()
                .putBoolean("remPas", mCheck.isChecked()).commit();

    }


    public  class LoginCall implements DataCall<Result<ResultBean>> {

        @Override
        public void success(Result<ResultBean> result) {
            mLoadDialog.cancel();
            if (result.getStatus().equals("0000")) {
                result.getResult().setStatus(1);//设置登录状态，保存到数据库
                ResultBeanDao resultBeanDao = DaoMaster.newDevSession(getBaseContext(),ResultBeanDao.TABLENAME).getResultBeanDao();
                resultBeanDao.insertOrReplace(result.getResult());


                intent( ShowActivity.class);

                Toast.makeText(MainActivity.this, result.getMessage() + "", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                UIUtils.showToastSafe(result.getStatus() + "  " + result.getMessage());
            }

        }


        @Override
        public void fail(ApiException e) {
            mLoadDialog.cancel();
            UIUtils.showToastSafe(e.getCode() + " " + e.getDisplayMessage());
        }
    }
}
