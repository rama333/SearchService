package com.labs.robots.find.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.labs.robots.find.R;
import com.labs.robots.find.model.UserModel;
import com.labs.robots.find.model.api.ApiFactory;
import com.labs.robots.find.model.api.ApiInterface;
import com.labs.robots.find.utils.Pref;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profileUser extends AppCompatActivity {
    @BindView(R.id.user_profile_name)
    TextView _name;
    @BindView(R.id.user_profile_short_bio)
    TextView _c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        Pref prefManager = Pref.getInstance(profileUser.this);



        ApiInterface apiInterface = ApiFactory.getRetrofitInstance("http://79003208229.myjino.ru/").create(ApiInterface.class);
        LoginView loginView;

        List<UserModel> dataModels = new ArrayList<>();

        Intent intent = getIntent();
        int user_id = Integer.parseInt(intent.getStringExtra("IDUSERINT"));

        Call<UserModel> call = apiInterface.getUser(user_id);


        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                Toast.makeText(getBaseContext(), "ok", Toast.LENGTH_LONG).show();
             _name.setText(response.body().getUser().getUsername());
                _c.setText(response.body().getUser().getSpec());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(getBaseContext(), "error", Toast.LENGTH_LONG).show();
            }
        });




    }
}
