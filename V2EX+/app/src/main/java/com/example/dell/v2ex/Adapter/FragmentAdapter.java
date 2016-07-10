package com.example.dell.v2ex.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dell.v2ex.Fragment.APPLEFragment;
import com.example.dell.v2ex.Fragment.AllFragment;
import com.example.dell.v2ex.Fragment.AnswerAndQuestionFragment;
import com.example.dell.v2ex.Fragment.AttentionFragment;
import com.example.dell.v2ex.Fragment.CityFragment;
import com.example.dell.v2ex.Fragment.CoolWorkFragment;
import com.example.dell.v2ex.Fragment.CreativeFtagment;
import com.example.dell.v2ex.Fragment.DealFragment;
import com.example.dell.v2ex.Fragment.FunnyFragment;
import com.example.dell.v2ex.Fragment.HotFragment;
import com.example.dell.v2ex.Fragment.R2Fragment;
import com.example.dell.v2ex.Fragment.RecentlyFragment;
import com.example.dell.v2ex.Fragment.TechnologyFragment;

/**
 * Created by dell on 2016/7/10.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private static final int TAB_COUNT = 13;
    private String[] Tabtitle = {"全部","最热","技术","创意","好玩","APPLE","酷工作","交易","城市","问与答","R2","关注","最近"};

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                AllFragment allFragment = new AllFragment();
                return allFragment;
            case 1:
                HotFragment hotFragment = new HotFragment();
                return hotFragment;
            case 2:
                TechnologyFragment technologyFragment = new TechnologyFragment();
                return technologyFragment;
            case 3:
                CreativeFtagment creativeFtagment = new CreativeFtagment();
                return creativeFtagment;
            case 4:
                FunnyFragment funnyFragment = new FunnyFragment();
                return  funnyFragment;
            case 5:
                APPLEFragment appleFragment = new APPLEFragment();
                return appleFragment;
            case 6:
                CoolWorkFragment coolWorkFragment = new CoolWorkFragment();
                return  coolWorkFragment;
            case 7:
                DealFragment dealFragment = new DealFragment();
                return dealFragment;
            case 8:
                CityFragment cityFragment = new CityFragment();
                return cityFragment;
            case 9:
                AnswerAndQuestionFragment answerAndQuestionFragment = new AnswerAndQuestionFragment();
                return answerAndQuestionFragment;
            case 10:
                R2Fragment r2Fragment = new R2Fragment();
                return r2Fragment;
            case 11:
                AttentionFragment attentionFragment =new AttentionFragment();
                return attentionFragment;
            case 12:
                RecentlyFragment recentlyFragment = new RecentlyFragment();
                return recentlyFragment;

        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Tabtitle[position];
    }
}
