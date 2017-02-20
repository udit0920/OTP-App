package com.example.uditsetia.smsapp.adapter;

/**
 * Created by uditsetia on 20/02/17.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.uditsetia.smsapp.fragment.ContactListFragment;
import com.example.uditsetia.smsapp.fragment.SentMessageListFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

  public SectionsPagerAdapter (FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem (int position) {
    // getItem is called to instantiate the fragment for the given page.
    // Return suitable fragment for that position
    switch (position) {
      case 0:
        return new ContactListFragment();
      case 1:
        return new SentMessageListFragment();
    }
    return null;
  }

  @Override
  public int getCount () {
    // Show 2 total pages.
    return 2;
  }

  @Override
  public CharSequence getPageTitle (int position) {
    switch (position) {
      case 0:
        return "Contact List";
      case 1:
        return "Sent Messages";
    }
    return null;
  }
}
