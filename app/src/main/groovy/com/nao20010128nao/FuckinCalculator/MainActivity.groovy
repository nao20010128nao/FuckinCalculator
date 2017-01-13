package com.nao20010128nao.FuckinCalculator;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    FragmentTabHost fth;
    TabHost.TabSpec nPr,nCr,fact,multiply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((android.support.v7.widget.Toolbar)findViewById(R.id.toolbar));
        fth = (FragmentTabHost)findViewById(android.R.id.tabhost);
        fth.setup(this, getSupportFragmentManager(), R.id.container);
    }
}
