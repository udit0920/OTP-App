package com.example.uditsetia.smsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.uditsetia.smsapp.R;
import com.example.uditsetia.smsapp.model.Contact;


/**
 * Created by uditsetia on 20/02/17.
 */

public class ContactInfoActivity extends AppCompatActivity implements View.OnClickListener {
  private Contact contact;

  @Override
  protected void onCreate (@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact_info);
    contact = getIntent().getParcelableExtra(Contact.EXTRA_CONTACT);

    TextView tvName = (TextView) findViewById(R.id.name);
    TextView tvPhone = (TextView) findViewById(R.id.phone);
    if (contact != null) {
      tvName.setText(String.format("%s %s", contact.getFirstName(), contact.getLastName()));
      tvPhone.setText(contact.getPhoneNo());
    }
    findViewById(R.id.btn_send_message).setOnClickListener(this);
  }

  @Override
  public void onClick (View v) {
    if (v.getId() == R.id.btn_send_message) {
      Intent intent = new Intent(this, ComposeMessageActivity.class);
      intent.putExtra(Contact.EXTRA_CONTACT, contact);
      startActivity(intent);
      finish();
    }
  }
}
