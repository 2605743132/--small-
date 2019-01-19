package com.bway.caoyuan.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bway.caoyuan.R;
import com.bway.caoyuan.activity.AddressActivity;
import com.bway.caoyuan.activity.CircleActivity;
import com.bway.caoyuan.activity.FootActivity;
import com.bway.caoyuan.activity.InformActivity;
import com.bway.caoyuan.activity.MoneyActivity;
import com.bway.caoyuan.bean.ResultBean;
import com.bway.caoyuan.db.DaoMaster;
import com.bway.caoyuan.db.ResultBeanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends Fragment {
    @BindView(R.id.nickname)
    TextView mNickname;
    @BindView(R.id.my_inform)
    TextView mMyInform;
    @BindView(R.id.my_Circle)
    TextView mMyCircle;
    @BindView(R.id.my_foot)
    TextView mMyFoot;
    @BindView(R.id.my_wallet)
    TextView mMyWallet;
    @BindView(R.id.my_address)
    TextView mMyAddress;
    @BindView(R.id.my_head_pic)
    CircleImageView mMyHeadPic;
    private View view;
    private Unbinder unbinder;
    private ResultBean LOGIN_USER;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ResultBeanDao userInfoDao = DaoMaster.newDevSession(getActivity(), ResultBeanDao.TABLENAME).getResultBeanDao();
        List<ResultBean> userInfos = userInfoDao.queryBuilder().where(ResultBeanDao.Properties.Status.eq(1)).list();

        if (userInfos.size() > 0) {
            LOGIN_USER = userInfos.get(0);//读取第一项
        }

        View view = inflater.inflate(R.layout.myfragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        Glide.with(getActivity()).load(LOGIN_USER.getHeadPic()).into(mMyHeadPic);
        mNickname.setText(LOGIN_USER.getNickName());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @OnClick({R.id.nickname, R.id.my_inform, R.id.my_Circle, R.id.my_foot, R.id.my_wallet, R.id.my_address, R.id.my_head_pic})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.nickname:
                break;
            case R.id.my_inform:
                Intent intent1 = new Intent(getActivity(), InformActivity.class);
                startActivity(intent1);
                break;
            case R.id.my_Circle:
                Intent intent2 = new Intent(getActivity(), CircleActivity.class);
                startActivity(intent2);
                break;
            case R.id.my_foot:
                Intent intent = new Intent(getActivity(), FootActivity.class);
                startActivity(intent);
                break;
            case R.id.my_wallet:

                Intent intent3 = new Intent(getActivity(), MoneyActivity.class);
                startActivity(intent3);
                break;
            case R.id.my_address:
                Intent intent4 = new Intent(getActivity(), AddressActivity.class);
                startActivity(intent4);
                break;
            case R.id.my_head_pic:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
