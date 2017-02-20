package com.example.uditsetia.smsapp.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.uditsetia.smsapp.R;
import com.example.uditsetia.smsapp.adapter.SentMessageListAdapter;
import com.example.uditsetia.smsapp.database.MyDatabaseHelper;
import com.example.uditsetia.smsapp.database.TableSentMessage;
import com.example.uditsetia.smsapp.model.SentMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uditsetia on 20/02/17.
 */

public class SentMessageListFragment extends Fragment {
  private static final String TAG = "SentMessageListFragment";
  private ListView listView;
  private SentMessageListAdapter listAdapter;
  private List<SentMessage> messageList;

  @Override
  public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View layout = inflater.inflate(R.layout.fragment_sent_message_list, container, false);
    listView = (ListView) layout.findViewById(R.id.list_view);
    messageList = new ArrayList<>();
    listAdapter = new SentMessageListAdapter(getContext(), messageList);
    listView.setAdapter(listAdapter);
    return layout;
  }

  @Override
  public void onStart () {
    super.onStart();
    fetchAllSentMessages();
  }

  private void fetchAllSentMessages () {
    new AsyncTask<Void, Void, List<SentMessage>>() {

      @Override
      protected List<SentMessage> doInBackground (Void... params) {
        SQLiteDatabase database = MyDatabaseHelper.getDatabaseHelper(getContext()).getWritableDatabase();
        return TableSentMessage.queryForSentMessages(database);
      }

      @Override
      protected void onPostExecute (List<SentMessage> messages) {
        if (messages != null) {
          messageList.clear();
          messageList.addAll(messages);
          listAdapter.notifyDataSetChanged();
        }
        super.onPostExecute(messageList);
      }
    }.execute();
  }
}
