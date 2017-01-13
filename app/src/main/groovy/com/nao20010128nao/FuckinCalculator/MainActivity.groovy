package com.nao20010128nao.FuckinCalculator

import android.graphics.Color
import android.graphics.drawable.Drawable;
import android.os.Bundle
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentTabHost
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log;
import android.widget.TabHost
import android.widget.TabWidget
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {

    FragmentTabHost fth
    TabHost.TabSpec nPr,nCr,factorial,multiplyCoefficients

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        contentView=R.layout.activity_main
        supportActionBar= findViewById(R.id.toolbar) as Toolbar
        fth = findViewById(android.R.id.tabhost) as FragmentTabHost
        fth.setup(this, supportFragmentManager, R.id.container)
        fth.onTabChangedListener=this

        nPr=fth.newTabSpec("nPr")
        nPr.indicator=resources.getString(R.string.title_npr)
        fth.addTab(nPr,NPRFragment,null)

        nCr=fth.newTabSpec("nCr")
        nCr.indicator=resources.getString(R.string.title_ncr)
        fth.addTab(nCr,NCRFragment,null)

        factorial=fth.newTabSpec("factorial")
        factorial.indicator=resources.getString(R.string.title_factorial)
        fth.addTab(factorial,FactorialFragment,null)

        multiplyCoefficients=fth.newTabSpec("multiplyCoefficients")
        multiplyCoefficients.indicator=resources.getString(R.string.title_multiply_coefficients)
        fth.addTab(multiplyCoefficients,MultiplyCoefficientsFragment,null)
    }


    public void onTabChanged(String a){
        TabWidget tw=fth.tabWidget
        int selected=fth.currentTab
        int[] colors=new int[tw.tabCount]
        Arrays.fill(colors,Color.argb((BigDecimal.valueOf(0xff) * new BigDecimal("0.3")) as int,0xff,0xff,0xff))
        colors[selected]=Color.WHITE
        Drawable tabUnderlineSelected=getTintedDrawable(R.drawable.abc_tab_indicator_mtrl_alpha,Color.argb(0xff,0xff,0xff,0xf))
        for (int i = 0; i < tw.childCount; i++) {
            TextView tv = tw.getChildAt(i).findViewById(android.R.id.title) as TextView
            tv.textColor=colors[i]
            tw.getChildAt(i).backgroundColor=0
        }
        ViewCompat.setBackground(tw.getChildAt(selected),tabUnderlineSelected)
        Log.d("TabChild",tw.getChildAt(selected).class.name)
    }

    public Drawable getTintedDrawable(@DrawableRes int res, int color){
        Drawable d=ResourcesCompat.getDrawable(resources,res,theme)
        d=DrawableCompat.wrap(d)
        DrawableCompat.setTint(d,color)
        return d
    }
}
