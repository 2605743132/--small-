package com.bway.caoyuan.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bway.caoyuan.MainActivity;
import com.bway.caoyuan.R;
import com.bway.caoyuan.bean.ResultBean;
import com.bway.caoyuan.db.DaoMaster;
import com.bway.caoyuan.db.ResultBeanDao;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InformActivity extends AppCompatActivity {

    @BindView(R.id.inform_pic)
    SimpleDraweeView mInformPic;
    @BindView(R.id.inform_nam)
    TextView mInformNam;
    @BindView(R.id.inform_pwd)
    TextView mInformPwd;
    @BindView(R.id.tuiout)
    Button mTuiout;
    private ResultBean LOGIN_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        ButterKnife.bind(this);
        ResultBeanDao userInfoDao = DaoMaster.newDevSession(this, ResultBeanDao.TABLENAME).getResultBeanDao();
        List<ResultBean> userInfos = userInfoDao.queryBuilder().where(ResultBeanDao.Properties.Status.eq(1)).list();

        if (userInfos.size() > 0) {
            LOGIN_USER = userInfos.get(0);//读取第一项
        }
        mInformPic.setImageURI(Uri.parse(LOGIN_USER.getHeadPic()));

        mInformNam.setText(LOGIN_USER.getNickName());

        mInformPwd.setText(LOGIN_USER.getSessionId());


    }

    @OnClick({R.id.inform_pic, R.id.inform_nam, R.id.inform_pwd, R.id.tuiout})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.inform_pic:
                break;
            case R.id.inform_nam:
                break;
            case R.id.inform_pwd:
                break;
            case R.id.tuiout:
                ResultBeanDao resultBeanDao = DaoMaster.newDevSession(this,ResultBeanDao.TABLENAME).getResultBeanDao();
                resultBeanDao.delete(LOGIN_USER);

                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                break;
        }
    }
}
