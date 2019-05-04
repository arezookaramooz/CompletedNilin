package com.example.completenilin;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.completenilin.Data.Address;
import com.example.completenilin.Data.Company;
import com.example.completenilin.Data.Geo;
import com.example.completenilin.Data.User;
import com.example.completenilin.Managers.UsersManager;

public class ProfileActivity extends AppCompatActivity {

    int usersPosition;
    UsersManager usersManager;
    User user;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_profile);

        usersManager = UsersManager.getInstance(this);

        Intent mIntent = getIntent();

        usersPosition = mIntent.getIntExtra("albumId", 0);
        user = usersManager.getUsers().get(usersPosition);

        initViews();

        initUser(usersPosition);
    }

    private void initViews() {
        
    }

    private void initUser(int usersPosition) {
        String name = user.getName();
        String userName = user.getUsername();
        String email = user.getEmail();

        Address address = user.getAddress();
        String street = address.getStreet();
        String suite = address.getSuite();
        String city = address.getCity();
        String zipcode = address.getZipcode();

        Geo geo = address.getGeo();
        String lat = geo.getLat();
        String lng = geo.getLng();

        String phone = user.getPhone();
        String website = user.getWebsite();

        Company company = user.getCompany();
        String companyName = company.getName();
        String catchPhrase = company.getCatchphrase();
        String bs = company.getBs();
    }
}
