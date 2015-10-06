package com.example.szgd.criminalintent;

import android.app.FragmentManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity implements CrimeFragment.OnFragmentInteractionListener {
    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager=new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);
        mCrimes=CrimeLab.get(this).getmCrimes();
        FragmentManager fm=getFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(null) {//TODO:can not cast
            @Override
            public Fragment getItem(int position) {
                Crime c=mCrimes.get(position);
                //return (CrimeFragment.newInstance(c.getmId()));//TODO:can not cast
                return null;
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        UUID id= (UUID) getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for(int i=0;i<mCrimes.size();i++){
            if(mCrimes.get(i).getmId().equals(id)){
                mViewPager.setCurrentItem(i);//set current item
                break;
            }
        }
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Crime c=mCrimes.get(position);
                if(c.getmTitle()!=null)
                    setTitle(c.getmTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
