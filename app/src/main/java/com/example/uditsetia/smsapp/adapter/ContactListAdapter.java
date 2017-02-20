package com.example.uditsetia.smsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uditsetia.smsapp.R;
import com.example.uditsetia.smsapp.model.Contact;

import java.util.List;

/**
 * Created by uditsetia on 20/02/17.
 */

public class ContactListAdapter extends BaseAdapter {
  private List<Contact> contactList;
  private LayoutInflater inflater;

  public ContactListAdapter (Context context, List<Contact> contactList) {
    inflater = LayoutInflater.from(context);
    this.contactList = contactList;
  }

  @Override
  public int getCount () {
    if (contactList != null)
      return contactList.size();
    return 0;
  }

  @Override
  public Contact getItem (int position) {
    if (position < contactList.size())
      return contactList.get(position);
    return null;
  }

  @Override
  public long getItemId (int position) {
    return position;
  }

  @Override
  public View getView (int position, View convertView, ViewGroup parent) {
    View view = convertView;
    if (view == null) {
      view = inflater.inflate(R.layout.item_contact_list, parent, false);
    }
    TextView tvName = (TextView) view.findViewById(R.id.name);
    tvName.setText(String.format("%s %s", getItem(position).getFirstName(), getItem(position).getLastName()));
    return view;
  }
}
