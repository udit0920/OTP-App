package com.example.uditsetia.smsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uditsetia.smsapp.R;
import com.example.uditsetia.smsapp.model.SentMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by uditsetia on 20/02/17.
 */

public class SentMessageListAdapter extends BaseAdapter {
  private List<SentMessage> sentMessageList;
  private LayoutInflater inflater;

  public SentMessageListAdapter (Context context, List<SentMessage> sentMessageList) {
    inflater = LayoutInflater.from(context);
    this.sentMessageList = sentMessageList;
  }

  @Override

  public int getCount () {
    if (sentMessageList != null)
      return sentMessageList.size();
    return 0;
  }

  @Override
  public SentMessage getItem (int position) {
    if (position < sentMessageList.size())
      return sentMessageList.get(position);
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
      view = inflater.inflate(R.layout.item_sent_message, parent, false);
    }
    TextView tvName = (TextView) view.findViewById(R.id.name);
    TextView tvTime = (TextView) view.findViewById(R.id.time);
    TextView tvOtp = (TextView) view.findViewById(R.id.otp);

    tvName.setText(getItem(position).getName());
    tvTime.setText(getFormattedTime(getItem(position).getTime()));
    tvOtp.setText(String.format("OTP: %s", getItem(position).getOtp()));
    return view;
  }

  private String getFormattedTime (long timeInMillis) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy HH:mm");
    Date resultdate = new Date(timeInMillis);
    return sdf.format(resultdate);
  }
}
