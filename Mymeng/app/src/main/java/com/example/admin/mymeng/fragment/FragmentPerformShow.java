package com.example.admin.mymeng.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.mymeng.R;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_performshow,container,false);

        mRollViewPager= (RollPagerView)view.findViewById(R.id.fg_performshow_roll);
        mRollViewPager.setPlayDelay(3000);
        mRollViewPager.setAdapter(mLoopAdapter = new TestLoopAdapter(mRollViewPager));
        mRollViewPager.setHintView(new IconHintView(view.getContext(),R.drawable.point_focus,R.drawable.point_normal));

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
}
