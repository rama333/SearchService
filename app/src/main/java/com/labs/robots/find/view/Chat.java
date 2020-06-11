package com.labs.robots.find.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.labs.robots.find.R;
import com.labs.robots.find.model.ChatModel;
import com.labs.robots.find.model.api.ApiFactory;
import com.labs.robots.find.model.api.ApiInterface;
import com.labs.robots.find.utils.Pref;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat extends AppCompatActivity{
    LinearLayout layout;
    RelativeLayout layoutr;

    ImageView send;
    EditText message;
    ScrollView scrollView;
   // ChatPresenter chatPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        layout = (LinearLayout) findViewById(R.id.layout1);
        layoutr = (RelativeLayout)findViewById(R.id.layout2);
        send = (ImageView)findViewById(R.id.sendButton);
        message = (EditText)findViewById(R.id.messageArea);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
      //  chatPresenter = new ChatPresenter(this);
        ApiInterface apiInterface = ApiFactory.getRetrofitInstance("http://79003208229.myjino.ru/").create(ApiInterface.class);
        LoginView loginView;
        Toast.makeText(getBaseContext(), "ok", Toast.LENGTH_LONG).show();


        List<com.labs.robots.find.model.Chat> dataModels = new ArrayList<>();

        Intent intent = getIntent();
        int user_id = Integer.parseInt(intent.getStringExtra("CHATIDUSER"));
        int task_id = Integer.parseInt(intent.getStringExtra("IDTASK"));
        Pref prefManager = Pref.getInstance(Chat.this);
        Call<ChatModel> call1 = apiInterface.getChat(prefManager.getUser().getId(), user_id,  "vf", task_id);

        call1.enqueue(new Callback<ChatModel>() {
            @Override
            public void onResponse(Call<ChatModel> call, Response<ChatModel> response) {

                int a = prefManager.getUser().getId();
                for (int i = 0; i < response.body().getChat().size(); i++) {
                    if(a != response.body().getChat().get(i).getId()){
                        addMessage("You:-\n" +response.body().getChat().get(i).getText(), 1);
                    } else{
                        addMessage("He:-\n" +response.body().getChat().get(i).getText(), 0);
                    }

                }

            }

            @Override
            public void onFailure(Call<ChatModel> call, Throwable t) {
                Toast.makeText(getBaseContext(), "error", Toast.LENGTH_LONG).show();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = message.getText().toString();

                if(!messageText.equals("")){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("user", "user");

                    message.setText("");

                  //  presenter.messager(map);

                    addMessage("You:-\n" +messageText, 1);

                    Call<ChatModel> call = apiInterface.setChat(prefManager.getUser().getId(), user_id,  messageText,  task_id);




                    call.enqueue(new Callback<ChatModel>() {
                        @Override
                        public void onResponse(Call<ChatModel> call, Response<ChatModel> response) {

                            Toast.makeText(getBaseContext(), "ok", Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onFailure(Call<ChatModel> call, Throwable t) {
                            Toast.makeText(getBaseContext(), "error", Toast.LENGTH_LONG).show();
                        }
                    });




                }
            }
        });
    }

    public void addMessage(String message, int type){
        TextView textView = new TextView(Chat.this);
        textView.setText(message);

        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        l.weight = 1.0f;

        if(type == 1) {
            l.gravity = Gravity.LEFT;
        }
        else{
            l.gravity = Gravity.RIGHT;
        }
        textView.setLayoutParams(l);
        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}
