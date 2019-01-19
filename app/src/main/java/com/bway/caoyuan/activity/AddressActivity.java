package com.bway.caoyuan.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bway.caoyuan.R;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.bean.ResultBean;
import com.bway.caoyuan.bean.SerializableBean;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.db.DaoMaster;
import com.bway.caoyuan.db.ResultBeanDao;
import com.bway.caoyuan.exception.ApiException;
import com.bway.caoyuan.presenter.SerializablePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends AppCompatActivity {


 SerializablePresenter serializablePresenter;
    private ResultBean LOGIN_USER;
    //  省份
    List<String> provinceBeanList = new ArrayList<>();
    //  城市
    List<String> cities;
    List<List<String>> cityList = new ArrayList<>();
    //  区/县
    List<String> district;
    List<List<String>> districts;
    //区和县连起来
    List<List<List<String>>> districtList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);


    }






}
