package com.example.uditsetia.smsapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.uditsetia.smsapp.R;
import com.example.uditsetia.smsapp.adapter.ContactListAdapter;
import com.example.uditsetia.smsapp.model.Contact;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by uditsetia on 20/02/17.
 */

public class ContactListFragment extends Fragment implements AdapterView.OnItemClickListener {
  private static final String TAG = "ContactListFragment";
  private ListView listView;
  private ContactListAdapter listAdapter;
  private Communicator communicator;

  @Override
  public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View layout = inflater.inflate(R.layout.fragment_contact_list, container, false);
    listView = (ListView) layout.findViewById(R.id.list_view);
    listAdapter = new ContactListAdapter(getContext(), getContactList());
    listView.setAdapter(listAdapter);
    listView.setOnItemClickListener(this);
    return layout;
  }

  private List<Contact> getContactList () {
    Gson gson = new Gson();
    Type listType = new TypeToken<List<Contact>>() {
    }.getType();
    List<Contact> contactList = gson.fromJson(getString(R.string.json_contact_list), listType);
    Log.d(TAG, "getContactList: " + contactList);
    return contactList;
  }

  @Override
  public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
    communicator.onContactClick(listAdapter.getItem(position));
  }

  @Override
  public void onAttach (Context context) {
    communicator = (Communicator) context;
    super.onAttach(context);
  }

  public interface Communicator {
    void onContactClick (Contact contact);
  }
}
