package zhuri.com.partybuilding.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuri.com.partybuilding.R;
import zhuri.com.partybuilding.activity.NavigationActivity;
import zhuri.com.partybuilding.entity.BaseEntity;
import zhuri.com.partybuilding.entity.SplashEntity;
import zhuri.com.partybuilding.util.AddressRequest;
import zhuri.com.partybuilding.util.SharedPreferencesUtils;
import zhuri.com.partybuilding.util.StaticVariables;
import zhuri.com.partybuilding.util.StatusBarUtil;
import zhuri.com.partybuilding.util.countdown.CountDownTimerSupport;
import zhuri.com.partybuilding.util.countdown.OnCountDownTimerListener;
import zhuri.com.partybuilding.util.glideutils.GlideUtils;
import zhuri.com.partybuilding.util.okhttp.OkHttpUtil;

/**
 * @author zzq
 * @date 创建时间：2017/4/28 11:27
 * 描述:欢迎界面
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG             #
 * #                                                   #
 */

public class SplashActivity extends Activity implements View.OnClickListener {
    private boolean aBoolean;

    ImageView mIvSplash;

    TextView mLlCountDown;
    //倒计时
    private CountDownTimerSupport mTimer;


    //app第一次运行
    private ViewPager viewpager;
    private LinearLayout ll_indicate;

    // private List<String> image_ids;
    private int[] image_ids = new int[]{
            R.drawable.splash_bg_two,
            R.drawable.splash_bg_one
    };
    private ImageView image;
    private int pointLen;
    private TextView text;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //获取app运行次数
        int appStartNum = (int) SharedPreferencesUtils.getParam(this, StaticVariables.APP_START_NUM, 0);

        if (appStartNum == 0) {

            setContentView(R.layout.activity_splash_first);
            first();

            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SplashActivity.this, NavigationActivity.class));
                    finish();
                }
            });

        } else {
            setContentView(R.layout.activity_splash);
            nofish();
        }

        SharedPreferencesUtils.setParam(this, StaticVariables.APP_START_NUM, ++appStartNum);

    }

    ViewPagerAdapter viewPagerAdapter;

    //第一次
    public void first() {

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        ll_indicate = (LinearLayout) findViewById(R.id.ll_indicate);
        image = (ImageView) findViewById(R.id.image);
        text = (TextView) findViewById(R.id.text);
        initIndicate();
        //  image_ids = new ArrayList<>();


      //  viewPagerAdapter = new ViewPagerAdapter(image_ids);
        viewPagerAdapter = new ViewPagerAdapter();

        viewpager.setAdapter(viewPagerAdapter);
        getEntity("0");
//        viewpager.setPageTransformer(true,new DepthPageTransformer());
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int i = (int) (pointLen * (position + positionOffset));
                RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) image
                        .getLayoutParams();
                params.leftMargin = i;
                image.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("eeeeee", position + " ");
                if (position == image_ids.length - 1) {
                    text.setVisibility(View.VISIBLE);
                } else {
                    text.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initIndicate() {

        int num = image_ids.length;

        for (int i = 0; i < num; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(R.drawable.bg_solid);

            //设置小圆点的布局参数
            int pointSize = getResources().getDimensionPixelSize(R.dimen.dp10);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(pointSize, pointSize);

            if (i > 0) {
                layoutParams.leftMargin = getResources().getDimensionPixelOffset(R.dimen.dp10);
            }

            imageView.setLayoutParams(layoutParams);
            ll_indicate.addView(imageView);
        }

        ll_indicate.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll_indicate.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                //获取圆点之间的距离
                pointLen = ll_indicate.getChildAt(1).getLeft() - ll_indicate.getChildAt(0).getLeft();
            }
        });
    }

    class ViewPagerAdapter extends PagerAdapter {
        private List<String> list;

        public ViewPagerAdapter(List<String> list) {
            this.list = list;
        }

        public ViewPagerAdapter() {

        }

        @Override
        public int getCount() {
//            return list.size();
            return image_ids.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(SplashActivity.this).inflate(R.layout.item_welcome, null);
            ImageView image = (ImageView) view.findViewById(R.id.image);
            image.setBackgroundResource(image_ids[position]);
//            Glide.with(SplashActivity.this).load(image_ids.get(position))
//                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(image);
            //   GlideUtils.LoadImage(SplashActivity.this, image_ids.get(position), image);
            container.addView(view);
            return view;
        }
    }

    //非第一次
    public void nofish() {
        mIvSplash = (ImageView) findViewById(R.id.iv_splash);
        mLlCountDown = (TextView) findViewById(R.id.ll_count_down);
        mLlCountDown.setOnClickListener(this);

        mTimer = new CountDownTimerSupport(1000 * 5, 1000);
        mTimer.start();
        mTimer.setOnCountDownTimerListener(new OnCountDownTimerListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                if (!SplashActivity.this.isFinishing()) {
                    mLlCountDown.setText("跳过 " + millisUntilFinished / 1000 + "秒");
                }
            }

            @Override
            public void onFinish() {
                if (!SplashActivity.this.isFinishing()) {
                    mLlCountDown.setText("跳过 0秒");
                    if (!aBoolean) {
                        //操作

                        startActivity(new Intent(SplashActivity.this, NavigationActivity.class));
                        finish();
                    }
                }
            }

        });
        getEntity("1");

    }


    /**
     * 跳转到首页
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_count_down:
                mTimer.stop();
                aBoolean = true;

                startActivity(new Intent(this, NavigationActivity.class));
                finish();
                break;
        }
    }

    public void getEntity(final String type) {
        Map map = new HashMap();
        map.put("type", type);
        OkHttpUtil.getInstance(this).doPost(AddressRequest.GET_LOAD_PAGE, new OkHttpUtil.ResultCallback<BaseEntity<List<String>>>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(BaseEntity<List<String>> response) {

                if (type.equals("0")) {
                    //第一次

//                    for (int i = 0; i < response.getData().size(); i++) {
//                        image_ids.add(response.getData().get(i));
//                    }
//                    viewPagerAdapter.notifyDataSetChanged();
//
//                    for (int i = 0; i < response.getData().size(); i++) {
//                        ImageView imageView = new ImageView(SplashActivity.this);
//                        imageView.setBackgroundResource(R.drawable.bg_solid);
//
//                        //设置小圆点的布局参数
//                        int pointSize = getResources().getDimensionPixelSize(R.dimen.dp10);
//                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(pointSize, pointSize);
//
//                        if (i > 0) {
//                            layoutParams.leftMargin = getResources().getDimensionPixelOffset(R.dimen.dp10);
//                        }
//
//                        imageView.setLayoutParams(layoutParams);
//                        ll_indicate.addView(imageView);
//                    }
//
//                    ll_indicate.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                        @Override
//                        public void onGlobalLayout() {
//                            ll_indicate.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                            //获取圆点之间的距离
//                            pointLen = ll_indicate.getChildAt(1).getLeft() - ll_indicate.getChildAt(0).getLeft();
//                        }
//                    });
//
//
//                    viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                        @Override
//                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                            int i = (int) (pointLen * (position + positionOffset));
//                            RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) image
//                                    .getLayoutParams();
//                            params.leftMargin = i;
//                            image.setLayoutParams(params);
//                        }
//
//                        @Override
//                        public void onPageSelected(int position) {
//                            Log.e("eeeeee", position + " ");
//                            if (position == image_ids.size() - 1) {
//                                text.setVisibility(View.VISIBLE);
//                            } else {
//                                text.setVisibility(View.INVISIBLE);
//                            }
//                        }
//
//                        @Override
//                        public void onPageScrollStateChanged(int state) {
//
//                        }
//                    });

                } else {

                    if (response.isStatus()) {
                        Glide.with(SplashActivity.this).load(response.getData().get(0))
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mIvSplash);
                        Log.e("eeeeee", "图片地址： " + response.getData().get(0));
                    }


                }
            }
        }, map);
    }

}
