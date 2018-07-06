package zhuri.com.partybuilding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import zhuri.com.partybuilding.entity.LogInEntity;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.TimeUtil;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/14
 * 描述:
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_title)
    TextView loginTitle;
    @BindView(R.id.login_admin)
    EditText loginAdmin;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_forget)
    TextView loginForget;
    @BindView(R.id.login_go)
    TextView loginGo;

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

    public void login() {
        Map map = new HashMap();
        map.put("uname", loginAdmin.getText().toString().trim());
        map.put("upass", loginPassword.getText().toString().trim());
        map.put("source", "android");
        OkHttpUtil.getInstance(this).doPost(AddressRequest.LOGIN, new OkHttpUtil.ResultCallback<BaseEntity<LogInEntity>>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(BaseEntity<LogInEntity> response) {
                if (response.isStatus()) {
                    //idaaa
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.USER_ID, response.getData().getId());
                    //token
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.TOKEN, response.getData().getToken());
                    //编号
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.CODES, response.getData().getCodes());
                    //名字
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.USER_NAME, response.getData().getName());
                    //昵称
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.USER_NICK_NAME, response.getData().getNickname());
                    //头像
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.USER_HEAD_IMG, response.getData().getAvatar());

                    //性别
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.SEX, response.getData().getSex().equals("0") ? "女" : "男");
                    //年龄
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.AGE, response.getData().getAge());
                    //生日
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.BIRTHDAY, response.getData().getBirthday());
                    //所在支部ID
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.DID, response.getData().getDid());
                    //所在支部名称
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.D_NAME, response.getData().getDname());
                    //电话
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.TEL, response.getData().getTel());
                    //邮箱
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.EMAIL, response.getData().getEmail());
                    //积分
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.INTEGRAL, response.getData().getIntegral());
                    //证件号"
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.IDCARD, response.getData().getIdcard());
                    //证件类型
                    SharedPreferencesUtils.setParam(LoginActivity.this, StaticVariables.IDTYPE, response.getData().getIdcard());
                    //入党时间
                    SharedPreferencesUtils.setParam(LoginActivity.this,StaticVariables.JOINTIME, TimeUtil.stampToDate(response.getData().getJointime(),"yyyy-mm-dd HH:mm:ss"));
                    //注册时间
                    SharedPreferencesUtils.setParam(LoginActivity.this,StaticVariables.REGTIME, TimeUtil.stampToDate(response.getData().getRegtime(),"yyyy-mm-dd HH:mm:ss"));


                    finishAllActivity();
                    startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
                }
            }
        }, map, "登陆中");

    }


    @OnClick({R.id.login_forget, R.id.login_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_forget:
                break;
            case R.id.login_go:
                login();

                break;
        }
    }
}
