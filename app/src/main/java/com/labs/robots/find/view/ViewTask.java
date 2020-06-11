package com.labs.robots.find.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.labs.robots.find.R;
import com.labs.robots.find.model.Feed;
import com.labs.robots.find.model.Task;
import com.labs.robots.find.model.UserModel;
import com.labs.robots.find.presenter.FeedPresenter;
import com.labs.robots.find.presenter.FeedbackPresenter;
import com.labs.robots.find.utils.Pref;
import com.labs.robots.find.view.adapters.FeedAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewTask extends AppCompatActivity implements LoginView, FeedView {
    @Nullable
    @BindView(R.id.viewName)
    TextView name;
    @Nullable
    @BindView(R.id.viewPrice)
    TextView price;
    @Nullable
    @BindView(R.id.ViewFulldesk)
    TextView desk;
    @Nullable
    @BindView(R.id.viewNumber)
    TextView number;
    @BindView(R.id.btn_Chat1)
    Button buttonChat;
    @BindView(R.id.btn_Click)
    Button buttonClick;
    @BindView(R.id.card_view1)
    CardView cardView1;
    FeedbackPresenter feedbackPresenter;
    String id_user;
    String who_user;
    String id_task;
    @BindView(R.id.rv1)
    RecyclerView recyclerView;
    FeedPresenter feedPresenter;
    FeedAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        ButterKnife.bind(this);

        feedPresenter = new FeedPresenter(this);
        feedbackPresenter =new FeedbackPresenter(this);


        List<Feed> list = new ArrayList<>();
        Pref prefManager1 = Pref.getInstance(ViewTask.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        feedAdapter = new FeedAdapter(list, this);

        recyclerView.setAdapter(feedAdapter);

        buttonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewTask.this, Chat.class);
                intent.putExtra("CHATIDUSER", id_user);
                intent.putExtra("IDTASK", id_task);
                startActivity(intent);
            }
        });

        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pref prefManager = Pref.getInstance(ViewTask.this);

                   feedbackPresenter.feedBack(id_user, String.valueOf(prefManager.getUser().getId()), id_task );

            }
        });


        Pref prefManager = Pref.getInstance(ViewTask.this);
        if (getIntent().getSerializableExtra("TASK") != null) {
            Task task = (Task) getIntent().getSerializableExtra("TASK");
            Toast.makeText(getBaseContext(), String.valueOf(task.getId()), Toast.LENGTH_LONG).show();

            name.setText(task.getName_task());
            price.setText(task.getPrice_task());
            desk.setText(task.getFull_task());
            number.setText(task.getNumber_task());
            id_task = task.getId();
            id_user = task.getUser_id();

            if(Integer.parseInt(task.getUser_id()) == prefManager.getUser().getId()){
                cardView1.setVisibility(View.GONE);

            } else{
                //cardView2.setVisibility(View.GONE);
            }






        } else
        {
            Toast.makeText(getBaseContext(), "Неправильный email или пароль", Toast.LENGTH_LONG).show();
        }

        feedPresenter.getFeed(prefManager1.getUser().getId(), Integer.valueOf(id_task));
    }

    public void onLoginSuccess(UserModel userModel) {

        if(userModel.isError()) {
            Toast.makeText(getBaseContext(), "Вы уже откликались", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "ok", Toast.LENGTH_LONG).show();

        }  //finish();
    }

    public void onLoginFailed(String error) {

        Toast.makeText(getBaseContext(), "failed", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onFeedSuccess(List<Feed> userModel) {


        if(!userModel.isEmpty()) {
            feedAdapter.add(0, userModel);

            Toast.makeText(getBaseContext(), "success" + userModel.get(0).getId(), Toast.LENGTH_LONG).show();
            //finish();
        } else{
            Toast.makeText(getBaseContext(), "Откликов нет пока" , Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onFeedFailed(String error) {
        Toast.makeText(getBaseContext(), "failed", Toast.LENGTH_LONG).show();
    }
}
