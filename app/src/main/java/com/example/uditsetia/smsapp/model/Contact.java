package com.example.uditsetia.smsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by uditsetia on 20/02/17.
 */

public class Contact implements Parcelable {
  public static final String EXTRA_CONTACT = "contact";
  private String firstName;
  private String lastName;
  private String phoneNo;

  public Contact (String firstName, String lastName, String phoneNo) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNo = phoneNo;
  }

  protected Contact (Parcel in) {
    firstName = in.readString();
    lastName = in.readString();
    phoneNo = in.readString();
  }

  public String getFirstName () {
    return firstName;
  }

  public String getLastName () {
    return lastName;
  }

  public String getPhoneNo () {
    return phoneNo;
  }

  @Override
  public int describeContents () {
    return 0;
  }

  @Override
  public void writeToParcel (Parcel dest, int flags) {
    dest.writeString(firstName);
    dest.writeString(lastName);
    dest.writeString(phoneNo);
  }

  public static final Creator<Contact> CREATOR = new Creator<Contact>() {
    @Override
    public Contact createFromParcel (Parcel in) {
      return new Contact(in);
    }

    @Override
    public Contact[] newArray (int size) {
      return new Contact[size];
    }
  };

  @Override
  public String toString () {
    return "Contact{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", phoneNo='" + phoneNo + '\'' +
            '}';
  }
}
