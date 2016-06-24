package com.example.admin.mymeng.fragment;

import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.admin.mymeng.R;
import com.example.admin.mymeng.intf.OnMultiLineTabViewListener;
import com.example.admin.mymeng.view.MultiLineTabView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.IconHintView;

/**
 * Created by admin on 2016/06/21.
 * 演绎秀
 */
public class FragmentPerformShow extends Fragment {
    private RollPagerView mRollViewPager;
    private TestLoopAdapter mLoopAdapter;

    /**
     * 全部分类， 点击后弹出菜单
     */
    private TextView textView;

    private int nType;

    LayoutInflater mInflater;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        View view = inflater.inflate(R.layout.fg_performshow,container,false);

        mRollViewPager= (RollPagerView)view.findViewById(R.id.fg_performshow_roll);
        mRollViewPager.setPlayDelay(3000);
        mRollViewPager.setAdapter(mLoopAdapter = new TestLoopAdapter(mRollViewPager));
        mRollViewPager.setHintView(new IconHintView(view.getContext(),R.drawable.point_focus,R.drawable.point_normal));

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.fg_performshow_TabLayout_newhot);
        tabLayout.addTab(tabLayout.newTab().setText("最新"));
        tabLayout.addTab(tabLayout.newTab().setText("热门"));

        textView = (TextView)view.findViewById(R.id.tv_fg_performshow_alltype);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allTypeClick(v);
            }
        });

        return view;
    }


    private class TestLoopAdapter extends LoopPagerAdapter {
        private int[] imgs = {
                R.drawable.header_hot_user,
                R.drawable.header_drama_user,
                R.drawable.header_hot_show,

        };
        private int count = imgs.length;

        public TestLoopAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getRealCount() {
            return count;
        }

    }

    public void allTypeClick(View v)
    {
        View view = mInflater.inflate(R.layout.popup_alltype, null, false);
//        Button btn_xixi = (Button) view.findViewById(R.id.btn_xixi);
//        Button btn_hehe = (Button) view.findViewById(R.id.btn_hehe);

//        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tab_popup_alltype_main);
//        tabLayout.addTab(tabLayout.newTab().setText("呵呵"));

        MultiLineTabView multiLineTabView = (MultiLineTabView)view.findViewById(R.id.MultiLineTabView_popup_alltype_main);
        multiLineTabView.addItem("全部分类", 0);
        multiLineTabView.addItem("古风", 1);
        multiLineTabView.addItem("动漫", 2);

        multiLineTabView.addItem("小说", 3);
        multiLineTabView.addItem("影视", 4);
        multiLineTabView.addItem("都市", 5);

        multiLineTabView.addItem("纯爱", 6);
        multiLineTabView.addItem("逗比", 7);
        multiLineTabView.addItem("耽美百合", 8);

        multiLineTabView.addItem("剧情歌", 9);
        multiLineTabView.addItem("游戏", 10);
        multiLineTabView.addItem("诗词句", 11);

        multiLineTabView.addItem("CV练习", 12);
        multiLineTabView.addItem("魔幻", 13);
        multiLineTabView.addItem("民国", 14);

        multiLineTabView.addItem("外语", 15);
        multiLineTabView.addItem("童话", 16);
        multiLineTabView.addItem("连环画", 17);


        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

//        popWindow.setAnimationStyle(R.anim.push_left_in);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                //这个事件可以不添加 --weijl
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x5FFF0000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, 50, 0);


        multiLineTabView.setmDelegate(new OnMultiLineTabViewListener() {
            @Override
            public void onClick(View v) {
                textView.setText(((TextView)v).getText());
                nType = (int)v.getTag();
                Log.e("type", "" + nType );
                popWindow.dismiss();
            }
        });

    }
}
