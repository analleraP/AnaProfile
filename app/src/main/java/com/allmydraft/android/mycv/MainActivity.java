package com.allmydraft.android.mycv;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        setupViewPager(viewPager);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.htab_tabs);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton callFab = (FloatingActionButton) findViewById(R.id.call_fab);
        callFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:".concat(getString(R.string.phone_number))));
                try {
                    startActivity(callIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, R.string.intent_phone_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
        FloatingActionButton mailFab = (FloatingActionButton) findViewById(R.id.mail_fab);
        mailFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.email)});
                try {
                    startActivity(Intent.createChooser(emailIntent, getString(R.string.intent_mail_title)));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, R.string.intent_mail_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ExperienceFragment(), "Experience");
        adapter.addFrag(new AppsFragment(), "Apps");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public String loadJSONFromAsset(String name) {
        String json = null;
        try {

            InputStream is = getAssets().open(name);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
        }
        return json;
    }
}