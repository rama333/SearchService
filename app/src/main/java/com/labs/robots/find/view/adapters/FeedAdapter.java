package com.labs.robots.find.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.labs.robots.find.R;
import com.labs.robots.find.model.Feed;
import com.labs.robots.find.view.Chat;
import com.labs.robots.find.view.profileUser;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolderTask> {


    List<Feed> task;
    Context context;

    public FeedAdapter(List<Feed> task, Context context) {
        this.task = task;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderTask onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rowView = layoutInflater.inflate(R.layout.feed, parent, false);

        return new ViewHolderTask(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTask holder, int position) {

        holder.textName.setText(String.valueOf( "Пользователь под id" + task.get(position).getWhyid() + "предлагает свои услуги"));
        holder.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Chat.class);
                intent.putExtra("CHATIDUSER", task.get(position).getWhyid());
                intent.putExtra("IDTASK", task.get(position).getTaskid());
                context.startActivity(intent);
            }
        });


        holder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, profileUser.class);
                intent.putExtra("IDUSERINT", task.get(position).getWhyid());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return task.size();
    }
    public void add(int i, List<Feed> list) {

        //Collections.reverse(list);

        task.addAll(i, list);
        notifyItemRangeChanged(i, list.size());
    }
    public class ViewHolderTask extends RecyclerView.ViewHolder{

        @BindView(R.id.nammeFade)
        TextView textName;

        @BindView(R.id.btn_Clfsick)
        Button profile;
        @BindView(R.id.btn_Clicdrk)
        Button chat;
        @BindView(R.id.card_view5)
        CardView cardView;

        public ViewHolderTask(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
