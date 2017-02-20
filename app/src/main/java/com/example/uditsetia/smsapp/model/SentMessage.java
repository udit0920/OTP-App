package com.example.uditsetia.smsapp.model;

/**
 * Created by uditsetia on 20/02/17.
 */
public class SentMessage {
  private String name;
  private long time;
  private int otp;

  public SentMessage (String name, long time, int otp) {
    this.name = name;
    this.time = time;
    this.otp = otp;
  }

  public String getName () {
    return name;
  }

  public long getTime () {
    return time;
  }

  public int getOtp () {
    return otp;
  }

  @Override
  public String toString () {
    return "SentMessage{" +
            "name='" + name + '\'' +
            ", time=" + time +
            ", otp=" + otp +
            '}';
  }
}
