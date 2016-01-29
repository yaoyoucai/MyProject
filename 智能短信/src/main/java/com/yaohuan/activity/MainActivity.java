package com.yaohuan.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.view.ViewPropertyAnimator;
import com.yaohuan.adapter.MainPagerAdapter;
import com.yaohuan.base.BaseActivity;
import com.yaohuan.fragment.ConversationFragment;
import com.yaohuan.fragment.GroupFragment;
import com.yaohuan.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ViewPager mViewPager;
    private TextView mTabConversation;
    private TextView mTabGroup;
    private TextView mTabSearch;
    private LinearLayout mLLConversation;
    private LinearLayout mLLGroup;
    private LinearLayout mLLSearch;
    private View mRedIndicateLine;
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabConversation = (TextView) findViewById(R.id.tv_tab_conversation);
        mTabGroup = (TextView) findViewById(R.id.tv_tab_group);
        mTabSearch = (TextView) findViewById(R.id.tv_tab_search);

        //初始化选项卡
        mLLConversation = (LinearLayout) findViewById(R.id.ll_tab_conversation);
        mLLGroup = (LinearLayout) findViewById(R.id.ll_tab_group);
        mLLSearch = (LinearLayout) findViewById(R.id.ll_tab_search);

        //初始化红线
        mRedIndicateLine = findViewById(R.id.view_indicate_line);
    }

    @Override
    public void initListener() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //计算红线位置
                int distance = positionOffsetPixels / 3;
                /*
                    设置红线的动画移动效果
                   注意：若需要使用ViewPropertyAnimator，则需要导入nineoldandroids的包
                 */
                ViewPropertyAnimator.animate(mRedIndicateLine).translationX(distance+position*mRedIndicateLine.getWidth()).setDuration(0);

            }

            @Override
            public void onPageSelected(int position) {
                setTextColorAndScale();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //初始化三个选项卡的点击事件
        mLLConversation.setOnClickListener(this);
        mLLGroup.setOnClickListener(this);
        mLLSearch.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        mFragments.add(new ConversationFragment());
        mFragments.add(new GroupFragment());
        mFragments.add(new SearchFragment());
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(pagerAdapter);
        setTextColorAndScale();

        //设置红线宽度
        setRedLineColor();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ll_tab_conversation:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.ll_tab_group:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.ll_tab_search:
                mViewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    /**
     * 改变选项卡颜色和大小
     */
    public void setTextColorAndScale() {
        int item = mViewPager.getCurrentItem();
        mTabConversation.setTextColor((item == 0) ? Color.WHITE : 0xaa666666);
        mTabGroup.setTextColor((item == 1) ? Color.WHITE : 0xaa666666);
        mTabSearch.setTextColor((item == 2) ? Color.WHITE : 0xaa666666);
    }

    /**
     * 设置红线宽度为当前屏幕的3/1
     */
    public void setRedLineColor() {
        int width = getWindowManager().getDefaultDisplay().getWidth();
        mRedIndicateLine.getLayoutParams().width = width / 3;
    }
}
