package com.labs.robots.find.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.labs.robots.find.R;
import com.labs.robots.find.model.Task;
import com.labs.robots.find.view.ViewTask;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolderTask> {

    List<Task> task;
    Context context;

    public TasksAdapter(List<Task> task, Context context) {
        this.task = task;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderTask onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rowView = layoutInflater.inflate(R.layout.list_task, parent, false);

        return new ViewHolderTask(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTask holder, int position) {

        holder.textName.setText(task.get(position).getName_task());
        holder.desc.setText(task.get(position).getShort_task());
        holder.state.setText(task.get(position).getPrice_task());

        holder.itemView.setOnClickListener(view -> {
            startActivity(task.get(position));
        });

    }

    private void startActivity(Task tasks) {
        Intent intent = new Intent(context, ViewTask.class);
        intent.putExtra("TASK", tasks);
        context.startActivity(intent);

    }
    @Override
    public int getItemCount() {
        return task.size();
    }
    public void add(int i, List<Task> list) {

        Collections.reverse(list);

        task.addAll(i, list);
        notifyItemRangeChanged(i, list.size());
    }

    public class ViewHolderTask extends RecyclerView.ViewHolder{

        @BindView(R.id.textName)
        TextView textName;
        @BindView(R.id.desc)
        TextView desc;
        @BindView(R.id.state)
        TextView state;
        @BindView(R.id.card_view)
        CardView cardView;

        public ViewHolderTask(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
