package com.bway.caoyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bway.caoyuan.MainActivity;
import com.bway.caoyuan.R;
import com.bway.caoyuan.ShowActivity;
import com.bway.caoyuan.WDApplication;
import com.bway.caoyuan.bean.ResultBean;
import com.bway.caoyuan.db.DaoMaster;
import com.bway.caoyuan.db.ResultBeanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LodingActivity extends AppCompatActivity {


    //    int count = 3;
    private ResultBean LOGIN_USER;
    private int time = 10;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                if (time > 0) {
                    //时间--
                    time--;


                        //给时间赋值
                        ResultBeanDao userInfoDao = DaoMaster.newDevSession(LodingActivity.this, ResultBeanDao.TABLENAME).getResultBeanDao();
                        List<ResultBean> userInfos = userInfoDao.queryBuilder().where(ResultBeanDao.Properties.Status.eq(1)).list();

                        if (userInfos.size() > 0) {

                            LOGIN_USER = userInfos.get(0);//读取第一项

                        }

                        if (LOGIN_USER != null) {
                            Intent intent2 = new Intent(LodingActivity.this, ShowActivity.class);
                            startActivity(intent2);
                            finish();
                            return;

                        } else if (LOGIN_USER == null){
                            Intent intent1 = new Intent(LodingActivity.this, MainActivity.class);
                            startActivity(intent1);
                            finish();
                            return;
                        }

                    }


                }
            }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_loding);

        handler.sendEmptyMessageDelayed(0, 4000);

    }


}
