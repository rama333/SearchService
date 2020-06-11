package com.labs.robots.find.view;

import android.os.Bundle;
import android.widget.TextView;

import com.labs.robots.find.R;
import com.labs.robots.find.utils.Pref;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Profile extends AppCompatActivity {

    @BindView(R.id.user_profile_name)
    TextView _name;
    @BindView(R.id.user_profile_short_bio)
    TextView _c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        Pref prefManager = Pref.getInstance(Profile.this);

        _name.setText(prefManager.getUser().getUsername());
        _c.setText(prefManager.getUser().getSpec());


    }
}
