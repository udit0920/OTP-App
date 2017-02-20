package com.example.uditsetia.smsapp.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uditsetia.smsapp.R;
import com.example.uditsetia.smsapp.apiclient.TwilioApiClient;
import com.example.uditsetia.smsapp.database.MyDatabaseHelper;
import com.example.uditsetia.smsapp.database.TableSentMessage;
import com.example.uditsetia.smsapp.model.Contact;

import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by uditsetia on 20/02/17.
 */

public class ComposeMessageActivity extends AppCompatActivity implements View.OnClickListener, TwilioApiClient.ApiCallback {
  private static final String TAG = "ComposeMessageActivity";
  private int otp;
  private Contact contact;
  private ProgressBar progressBar;

  @Override
  protected void onCreate (@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_compose);
    contact = getIntent().getParcelableExtra(Contact.EXTRA_CONTACT);
    TextView tvMessage = (TextView) findViewById(R.id.message);
    progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    getRandomOTP();
    tvMessage.setText(getString(R.string.otp_message, otp));
    findViewById(R.id.btn_send).setOnClickListener(this);
  }

  private void getRandomOTP () {
    //generate random number of 6 digits
    Random random = new Random();
    otp = 100000 + random.nextInt(900000);
  }

  @Override
  public void onClick (View v) {
    if (v.getId() == R.id.btn_send) {
      progressBar.setVisibility(View.VISIBLE);
      //      new TwilioApiClient(this).sendMessage(getString(R.string.otp_message, otp), "+12566774619", "+919677479502");
      new TwilioApiClient(this).sendMessage(getString(R.string.otp_message, otp), "+12566774619", "+919971792703");
    }
  }

  private void saveSentMessageToDb () {
    if (contact != null) {
      String name = String.format("%s %s", contact.getFirstName(), contact.getLastName());
      SQLiteDatabase database = MyDatabaseHelper.getDatabaseHelper(this).getWritableDatabase();
      long id = TableSentMessage.insertIntoTable(database, name, System.currentTimeMillis(), otp);
      Log.d(TAG, "saveSentMessageToDb: " + id);
    } else
      Log.d(TAG, "saveSentMessageToDb: contact is null");
  }

  @Override
  public void onSuccess (Response<ResponseBody> response) {
    progressBar.setVisibility(View.GONE);
    Toast.makeText(this, "Success: Message Sent!", Toast.LENGTH_SHORT).show();
    Log.d(TAG, "onResponse->success");
    saveSentMessageToDb();
    finish();
  }

  @Override
  public void onFailure (Throwable t) {
    progressBar.setVisibility(View.GONE);
    Toast.makeText(this, "Failure!", Toast.LENGTH_SHORT).show();
    Log.d(TAG, "onFailure " + t.getMessage());
    finish();
  }
}
