package com.xmh.weiku.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.xmh.weiku.R;
import com.xmh.weiku.adapter.FragmentsAdapter;
import com.xmh.weiku.fragment.CookFragment;
import com.xmh.weiku.fragment.MainFragment;
import com.xmh.weiku.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;

public class MainActivity extends BaseActivity {

    //region index of fragments
    private static final int MAIN_FRAGMENT_INDEX=0;
    private static final int COOK_FRAGMENT_INDEX=1;
    private static final int ME_FRAGMENT_INDEX=2;
    //endregion

    //region views
    @Bind(R.id.vp_pages)
    ViewPager vpPagess;
    @Bind(R.id.tv_main_page)
    TextView tvMainPage;
    @Bind(R.id.tv_cook_page)
    TextView tvCookPage;
    @Bind(R.id.tv_me_page)
    TextView tvMePage;
    //endregion

    //region fragments
    private FragmentsAdapter mFragmentsAdapter;
    private List<Fragment> fragments=new ArrayList<>();
    private MainFragment mMainFragment;
    private CookFragment mCookFragment;
    private MeFragment mMeFragment;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initView();
        initData();

    }

    private void initView() {
        mMainFragment=new MainFragment();
        mCookFragment=new CookFragment();
        mMeFragment=new MeFragment();
        fragments.add(mMainFragment);
        fragments.add(mCookFragment);
        fragments.add(mMeFragment);
        mFragmentsAdapter=new FragmentsAdapter(getSupportFragmentManager());
        mFragmentsAdapter.setList(fragments);
        vpPagess.setAdapter(mFragmentsAdapter);
    }

    private void initData() {
        vpPagess.setCurrentItem(MAIN_FRAGMENT_INDEX);
        clearPageSelect();
        tvMainPage.setSelected(true);
    }

    private void clearPageSelect() {
        tvMainPage.setSelected(false);
        tvCookPage.setSelected(false);
        tvMePage.setSelected(false);
    }

    @OnClick({R.id.tv_main_page,R.id.tv_cook_page,R.id.tv_me_page})
    public void onPageSelectorClick(View view){
        clearPageSelect();
        view.setSelected(true);
        switch (view.getId()){
            case R.id.tv_main_page:
                vpPagess.setCurrentItem(MAIN_FRAGMENT_INDEX);
                break;
            case R.id.tv_cook_page:
                vpPagess.setCurrentItem(COOK_FRAGMENT_INDEX);
                break;
            case R.id.tv_me_page:
                vpPagess.setCurrentItem(ME_FRAGMENT_INDEX);
                break;
        }
    }

    @OnPageChange(value=R.id.vp_pages,callback = OnPageChange.Callback.PAGE_SELECTED)
    public void onPageSelected(int position){
                clearPageSelect();
        switch (position){
            case MAIN_FRAGMENT_INDEX:
                tvMainPage.setSelected(true);
                break;
            case COOK_FRAGMENT_INDEX:
                tvCookPage.setSelected(true);
                break;
            case ME_FRAGMENT_INDEX:
                tvMePage.setSelected(true);
                break;
        }
    }

    @Override
    public void onBackPressed() {
     exitApp();
    }

}
