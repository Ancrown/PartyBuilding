package zhuri.com.partybuilding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;

/**
 * Created by Administrator on 2018/5/17.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_in)
    TextView loginIn;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.aty_login;
    }



    @OnClick(R.id.login_in)
    public void onViewClicked() {
        Map map = new HashMap<>();
        startActivity(new Intent(this, NavigationActivity.class));
//        OkHttpUtil.getInstance(this).doPost(AddressRequest.URL, new OkHttpUtil.ResultCallback<BaseEntity<String>>() {
//            @Override
//            public void onError(Request request, Exception e) {
//
//            }
//
//            @Override
//            public void onResponse(BaseEntity<String> response) {
//
//            }
//        }, map, "正在登陆");
    }
}
