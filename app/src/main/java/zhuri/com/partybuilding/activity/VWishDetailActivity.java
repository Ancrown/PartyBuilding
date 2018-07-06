package zhuri.com.partybuilding.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.activities.ActivitiesThreeActivity;
import zhuri.com.partybuilding.adapter.VWishAdapter;
import zhuri.com.partybuilding.base.BaseActivity;
import zhuri.com.partybuilding.bean.VWishBean;
import zhuri.com.partybuilding.dialog.RuleDialog;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.activities.ActivitiesCVDetailsEntity;
import zhuri.com.partybuilding.entity.activities.VWishDetailEntity;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.AppUtils;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.SizeUtils;
import zhuri.com.partybuilding.util.SpaceItemDecoration;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.TimeUtil;
import zhuri.com.partybuilding.util.ToolUtils;
import zhuri.com.partybuilding.util.htmlutils.HtmlFormat;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;
import zhuri.com.partybuilding.view.NoScrollWebView;
import zhuri.com.partybuilding.view.gradualchange.TranslucentActionBar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/26
 * 描述:
 */

public class VWishDetailActivity extends BaseActivity implements TranslucentActionBar.ActionBarClickListener {
    @BindView(R.id.vwish_detail_title)
    TextView vwishDetailTitle;
    @BindView(R.id.vwish_detail_sendtime)
    TextView vwishDetailSendtime;
    @BindView(R.id.vwish_detail_webview)
    NoScrollWebView vwishDetailWebview;
    @BindView(R.id.vwish_detail_time)
    TextView vwishDetailTime;
    @BindView(R.id.vwish_detail_address)
    TextView vwishDetailAddress;
    @BindView(R.id.vwish_detail_signendtime)
    TextView vwishDetailSignendtime;
    @BindView(R.id.vwish_detail_peoplenum)
    TextView vwishDetailPeoplenum;
    @BindView(R.id.vwish_detail_signup)
    TextView vwishDetailSignup;
    private boolean isJoin;

    @BindView(R.id.vwish_detail_rule)
    TextView vwishDetailRule;
    @BindView(R.id.vwish_detail_more)
    TextView vwishDetailMore;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.zhanwei)
    View zhanwei;

    private String text;

    private String type;
    private String id;


    private VWishAdapter adapter;
    private List<VWishBean> itemList;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();

        id = intent.getStringExtra("id");


        getTitleView().setData("微心愿", 0, R.drawable.back, null, 0, null, this);


        //listview 效果
        LinearLayoutManager lmr = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        //设置为垂直布局，这也是默认的
        lmr.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recycler.setLayoutManager(lmr);


        recycler.addItemDecoration(new SpaceItemDecoration(0, SizeUtils.dip2px(1)));
        adapter = new VWishAdapter(this);

        recycler.setAdapter(adapter);
        itemList = new ArrayList<>();
        //   getdata();
        getEntity();
    }

    public void getdata() {
        itemList.add(new VWishBean(
                "0",
                "微心愿|“点亮微心愿，争做圆梦人”",
                null,
                null,
                (int) (Math.random() * 2) + "",
                "2018-06-09",
                "2018-10-10",
                (int) (Math.random() * 100) + "",
                "0",
                (int) (Math.random() * 100) + "",
                "南湖社区"));
        itemList.add(new VWishBean(
                "1",
                "【基层党建】扬州市邗江区杨寿镇点亮“微心愿” 服务大民生",
                null,
                null,
                (int) (Math.random() * 2) + "",
                "2018-06-22",
                "2018-10-01",
                (int) (Math.random() * 100) + "",
                "0",
                (int) (Math.random() * 100) + "",
                "扬州市邗江区杨寿镇"));

        itemList.add(new VWishBean(
                "2",
                "秦都党建创新路 全心全意为人民",
                null,
                null,
                (int) (Math.random() * 2) + "",
                "2018-06-25",
                "2018-10-01",
                (int) (Math.random() * 100) + "",
                "0",
                (int) (Math.random() * 100) + "",
                "秦都区委"));

//        for (int i = 0; i < 3; i++) {
//            itemList.add(new VWishBean(
//                    i + "",
//                    "微心愿|“点亮微心愿，争做圆梦人”",
//                    i % 2 + "",
//                    "微心愿”是在微博上新近兴起的一个网络热词，是指借助微博平台发表自己的个人心愿，用户可以通过微博发布个人愿望，也可以认领其他人发布的愿望，发起人与认领人可以借助微博平台相互沟通，最终完成用户的微心愿。",
//                    i % 2 + "",
//                    "2018-6-9", "2018-10-10",
//                    "90",
//                    i % 2 + "", "5", "XXXXXXXXXXXX"));
//            Log.e("eeeeee", "TYPE    " + itemList.get(i).getType());
//        }
        adapter.setDataList(itemList);
    }

    private void getEntity() {
        Map map = new HashMap();
        map.put("uid", SharedPreferencesUtils.getParam(this, StaticVariables.USER_ID, ""));
        map.put("token", SharedPreferencesUtils.getParam(this, StaticVariables.TOKEN, ""));
        map.put("id", id);
        OkHttpUtil.getInstance(this).doPost(AddressRequest.ACTIVITIES_W_DETAILS, new OkHttpUtil.ResultCallback<BaseEntity<VWishDetailEntity>>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(BaseEntity<VWishDetailEntity> response) {
                if (response.isStatus()) {

                    vwishDetailTitle.setText(response.getData().getMain().getTitle());
                    vwishDetailSendtime.setText("发布时间：" + TimeUtil.stampToDate(response.getData().getMain().getAddtime(), "yyyy-mm-dd HH:mm:ss"));
                    text = response.getData().getMain().getContent();
                    vwishDetailWebview.loadDataWithBaseURL(null, HtmlFormat.getNewContent(text), "text/html", "utf-8", null);


                    vwishDetailTime.setText("活动时间：" + TimeUtil.stampToDate(response.getData().getMain().getStime(), "yyyy-mm-dd") + "~"
                            + TimeUtil.stampToDate((response.getData().getMain().getEtime()), "yyyy-mm-dd"));

                    vwishDetailAddress.setText("地址：" + response.getData().getMain().getAddress());
                    vwishDetailSignendtime.setText("领取截止日期：" + response.getData().getMain().getEtime());
                    vwishDetailPeoplenum.setText("领取人数：" + response.getData().getMain().getSignup());

                    isJoin = response.getData().getMain().getIsjoin().equals("1");
                    if (!isJoin) {
                        vwishDetailSignup.setText("领取任务");
                        vwishDetailSignup.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_red));

                    } else {
                        vwishDetailSignup.setText("已经领取,待选择中");
                        vwishDetailSignup.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_light_gray));
                    }

                    itemList.clear();
                    for (int i = 0; i < response.getData().getInfo().size(); i++) {
                        itemList.add(new VWishBean(response.getData().getInfo().get(i).getId(),
                                response.getData().getInfo().get(i).getTitle(),
                                "",
                                response.getData().getInfo().get(i).getDemo(),
                                response.getData().getInfo().get(i).getStatus(),
                                response.getData().getInfo().get(i).getStime(),
                                response.getData().getInfo().get(i).getEtime(),
                                "",
                                response.getData().getInfo().get(i).getPurview(),
                                response.getData().getInfo().get(i).getSignup(),
                                response.getData().getInfo().get(i).getDname()));
                    }
                    adapter.setDataList(itemList);
                    zhanwei.setVisibility(View.GONE);
                } else {
                    ToolUtils.showToast(VWishDetailActivity.this, response.getMsg());
                }
            }
        }, map, "");

    }


    /**
     * 领取
     *
     * @param isLogin
     * @param context
     * @param id
     * @param textView
     */
    public void signUp(Boolean isLogin, final Context context, String id, final TextView textView) {
        if (isLogin) {
            context.startActivity(new Intent(context, LoginActivity.class));
        } else {
            Map map = new HashMap();
            map.put("uid", SharedPreferencesUtils.getParam(context, StaticVariables.USER_ID, ""));
            map.put("token", SharedPreferencesUtils.getParam(context, StaticVariables.TOKEN, ""));
            map.put("id", id);
            OkHttpUtil.getInstance(context).doPost(AddressRequest.ACTIVITIES_W_SING_UP, new OkHttpUtil.ResultCallback<BaseEntity<String>>() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(BaseEntity<String> response) {
                    if (response.isStatus()) {

                        textView.setText("已经领取,待选择中");
                        textView.setBackgroundDrawable(AppUtils.getDrawable(R.drawable.fill_bg_light_gray));

                        isJoin = true;
                    } else {
                        ToolUtils.showToast(context, response.getMsg());

                    }
                }
            }, map);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_v_wish_detail;
    }


    @OnClick({R.id.vwish_detail_signup, R.id.vwish_detail_rule, R.id.vwish_detail_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.vwish_detail_signup:
                //领取
                signUp(isLogin, this, id, vwishDetailSignup);

                break;
            case R.id.vwish_detail_rule:
                RuleDialog dialog = new RuleDialog(this, R.style.custom_dialog);
                dialog.setTitle("积分获取");
                dialog.setText("一、如何获得积分？\n" +
                        "积分：会员通过团购或商城购物获得相应积分，并以积分享受不同程度的专属优惠券或实物礼品兑换（实物兑换后期上线）、抽奖及参与知我药妆网不定期举行的活动。\n" +
                        "\n" +
                        "\n" +
                        "二、获得积分规则\n" +
                        "登录商城购物 在商城或团购购物每消费1元，获得1积分，积分按实际消费取整后金额计算， 无封顶\n" +
                        "\n" +
                        "\n" +
                        "三、如何查询积分？\n" +
                        "在\"个人中心\"-\"我的积分\"里查看积分数值\n" +
                        "\n" +
                        "步骤：登陆知我药妆网—点击个人中心\n" +
                        "\n" +
                        "\n" +
                        "四、何时获得积分？\n" +
                        "购物获得积分：订单发货后，积分为冻结状态，新积分暂时不显示，确认收货后积分会自动充值到账户。\n" +
                        "\n" +
                        "\n" +
                        "五、如何使用积分？\n" +
                        "兑礼券 可享受各种优惠的红包或代金券，优惠券和红包自兑换起3天有效，不定期积分抽奖，只需手指轻轻一动，梦想大奖随时拿回家！");
                dialog.show();
                break;
            case R.id.vwish_detail_more:
                startActivity(new Intent(this, ActivitiesThreeActivity.class));
                break;
        }
    }

    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {
    }


}
