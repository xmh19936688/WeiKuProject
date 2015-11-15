package com.xmh.weiku.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.xmh.weiku.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mengh on 2015/10/28 028.
 */
public class MainFragment extends Fragment {

    //region view
    private View mRootView;
    @Bind(R.id.v_net_errors)View vNetError;
    @Bind(R.id.ptr_sv_main)PullToRefreshScrollView svMain;
    @Bind(R.id.ll_content)LinearLayout llConent;
    //endregion

    //region window
    Dialog loadingDialog;
    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @OnClick(R.id.v_net_errors)
    public void reLoadData(View v){
        loadData();
    }

    private void initView() {
        initLoadingDialog();
        svMain.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadData();
                    }
                }, 1000);
            }
        });
    }

    private void loadData() {
        svMain.onRefreshComplete();
        vNetError.setVisibility(View.GONE);
        llConent.setVisibility(View.VISIBLE);
    }

    private void initLoadingDialog() {
        loadingDialog=new Dialog(getActivity(),R.style.dialog_loading_style);
        loadingDialog.setContentView(R.layout.dialog_loading);
        loadingDialog.setCanceledOnTouchOutside(true);//点击空白处消失
        final ImageView ivAnim= (ImageView) loadingDialog.findViewById(R.id.iv_anim);
        loadingDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface d) {
                AnimationDrawable animDrawable = (AnimationDrawable) ivAnim.getDrawable();
                animDrawable.start();
            }
        });
        loadingDialog.getWindow().setWindowAnimations(R.style.dialog_loading_style);//dialog的动画要设置在window上
    }

    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    private void requestData() {
        loadingDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismiss();
                    }
                });
            }
        }).start();
    }
}
