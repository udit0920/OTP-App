package com.example.uditsetia.smsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.uditsetia.smsapp.R;
import com.example.uditsetia.smsapp.adapter.SectionsPagerAdapter;
import com.example.uditsetia.smsapp.fragment.ContactListFragment;
import com.example.uditsetia.smsapp.model.Contact;

public class MainActivity extends AppCompatActivity implements ContactListFragment.Communicator {
  //The PagerAdapter that will provide fragments for each of the sections
  private SectionsPagerAdapter mSectionsPagerAdapter;

  //The ViewPager that will host the section contents.
  private ViewPager mViewPager;

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    // Create the adapter that will return a fragment for each of the two primary sections of the activity.
    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

    // Set up the ViewPager with the sections adapter.
    mViewPager = (ViewPager) findViewById(R.id.viewPager);
    mViewPager.setAdapter(mSectionsPagerAdapter);

    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(mViewPager);
  }

  @Override
  public void onContactClick (Contact contact) {
    Intent intent = new Intent(this, ContactInfoActivity.class);
    intent.putExtra(Contact.EXTRA_CONTACT, contact);
    startActivity(intent);
  }
}
