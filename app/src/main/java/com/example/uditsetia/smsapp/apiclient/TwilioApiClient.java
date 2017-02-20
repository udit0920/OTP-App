package com.example.uditsetia.smsapp.apiclient;

import android.util.Base64;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by uditsetia on 20/02/17.
 */

public class TwilioApiClient {
  private static final String TAG = "TwilioApiClient";
  private static final String BASE_URL = "https://api.twilio.com/2010-04-01/";
  private static final String ACCOUNT_SID = "ACbd8e515f5d226c4251a718e13a498048";
  private static final String AUTH_TOKEN = "46dd7b00ed234c8c686b13dbb3ce261b";

  private ApiCallback apiCallback;

  public TwilioApiClient (ApiCallback apiCallback) {
    this.apiCallback = apiCallback;
  }

  public void sendMessage (String body, String from, String to) {
    String base64EncodedCredentials = "Basic " + Base64.encodeToString(
            (ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes(), Base64.NO_WRAP
    );

    Map<String, String> data = new HashMap<>();
    data.put("From", from);
    data.put("To", to);
    data.put("Body", body);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build();
    TwilioApiService api = retrofit.create(TwilioApiService.class);

    api.sendMessage(ACCOUNT_SID, base64EncodedCredentials, data).enqueue(new Callback<ResponseBody>() {
      @Override
      public void onResponse (Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()) {
          apiCallback.onSuccess(response);
        } else {
          apiCallback.onFailure(new Throwable("onResponse->Failure"));
        }
      }

      @Override
      public void onFailure (Call<ResponseBody> call, Throwable t) {
        apiCallback.onFailure(t);
      }
    });
  }

  public interface ApiCallback {
    void onSuccess (Response<ResponseBody> response);

    void onFailure (Throwable t);

  }
}
