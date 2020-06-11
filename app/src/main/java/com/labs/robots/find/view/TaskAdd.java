package com.labs.robots.find.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.labs.robots.find.R;
import com.labs.robots.find.model.TasksModel;
import com.labs.robots.find.presenter.TaskPresenter;
import com.labs.robots.find.utils.Pref;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskAdd extends AppCompatActivity implements TaskAddView {
    ProgressDialog progressDialog;
    TaskPresenter taskPresenter;
    @BindView(R.id.btn_task)
    Button _button;
    @BindView(R.id.name_task)
    EditText _name_task;
    @BindView(R.id.city_task)
    EditText _city_task;
    @BindView(R.id.full_des)
    EditText _full_task;
    @BindView(R.id.short_des)
    EditText _short_task;
    @BindView(R.id.price_desk)
    EditText _price_task;
    @BindView(R.id.number_task)
    EditText _number_task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);
        ButterKnife.bind(this);
        taskPresenter = new TaskPresenter(this);
        progressDialog = new ProgressDialog(TaskAdd.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");

        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task();

            }
        });
    }


    public void task(){

        if (!validate()) {
         //   onTaskFailed("error");
            return;
        }

        _button.setEnabled(false);

        String name_task = _name_task.getText().toString();
        String city_task = _city_task.getText().toString();
        String full_task = _full_task.getText().toString();
        String short_task = _short_task.getText().toString();
        String price_task = _price_task.getText().toString();
        String number_task = _number_task.getText().toString();
        Pref prefManager = Pref.getInstance(TaskAdd.this);
        taskPresenter.addTask(prefManager.getUser().getId() , name_task, city_task, full_task, short_task, price_task, number_task);
        _button.setEnabled(false);

        progressDialog.show();

    }


    public void onTaskSuccess(TasksModel taskModel) {
        progressDialog.dismiss();
        Toast.makeText(getBaseContext(), taskModel.getTasks().get(0).getName_task(), Toast.LENGTH_LONG).show();
        _button.setEnabled(true);
        if(taskModel.isError()) {
            Toast.makeText(getBaseContext(), taskModel.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "success", Toast.LENGTH_LONG).show();
            //finish();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onTaskFailed(String error) {
        progressDialog.dismiss();
        Toast.makeText(getBaseContext(), error, Toast.LENGTH_LONG).show();

        _button.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;




        String name_task = _name_task.getText().toString();
        String city_task = _city_task.getText().toString();
        String full_task = _full_task.getText().toString();
        String short_task = _short_task.getText().toString();
        String price_task = _price_task.getText().toString();
        String number_task = _number_task.getText().toString();

        if (name_task.isEmpty() || name_task.length() < 3) {
            _name_task.setError("Количесво символов должно быть >=3 ");
            valid = false;
        } else {
            _name_task.setError(null);
        }

        if (city_task.isEmpty() ||city_task.length() < 3) {
            _city_task.setError("Количесво символов должно быть >=3");
            valid = false;
        } else {
            _city_task.setError(null);
        }

        if (full_task.isEmpty() ||full_task.length() < 10) {
            _full_task.setError("Количесво символов должно быть >=10");
            valid = false;
        } else {
            _full_task.setError(null);
        }

        if (short_task.isEmpty() || short_task.length() < 5) {
            _short_task.setError("Количесво символов должно быть >=5");
            valid = false;
        } else {
            _short_task.setError(null);
        }

        if (price_task.isEmpty() || price_task.length() < 2) {
            _price_task.setError("Количесво символов должно быть >=2");
            valid = false;
        } else {
            _price_task.setError(null);
        }

        if (number_task.isEmpty() || number_task.length() < 9) {
            _number_task.setError("Количесво символов должно быть >=9");
            valid = false;
        } else {
            _number_task.setError(null);
        }



        return valid;
    }
}
