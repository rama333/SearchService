package com.labs.robots.find.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.labs.robots.find.R;
import com.labs.robots.find.model.SignupModel;
import com.labs.robots.find.presenter.SignupPresenter;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements SignupView{
    private static final String TAG = "SignupActivity";
    ProgressDialog progressDialog;
    SignupPresenter signupPresenter;

    @BindView(R.id.input_name)
    EditText _nameText;
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_signup)
    Button _signupButton;
    @BindView(R.id.link_login)
    TextView _loginLink;
    @BindView(R.id.input_cpec)
    TextView _login_cpec;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        signupPresenter = new SignupPresenter(this);
        progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed("error");
            return;
        }

        _signupButton.setEnabled(false);


        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String spec = _login_cpec.getText().toString();

        // TODO: Implement your own signup logic here.

        signupPresenter.signup(name, email, password, spec);




    }


    public void onSignupSuccess(SignupModel signupModel) {
        progressDialog.dismiss();
        _signupButton.setEnabled(true);
        if(signupModel.isError()) {
            Toast.makeText(getBaseContext(), "Пользователь существет с таким email", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "success", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void onSignupFailed(String error) {
        progressDialog.dismiss();
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String spec = _login_cpec.getText().toString();


        if (spec.isEmpty() || spec.length() < 3) {
            _login_cpec.setError("Количесво символов должно быть > 3");
            valid = false;
        } else {
            _login_cpec.setError(null);
        }

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("Количесво символов должно быть > 3");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Введен некорректный email");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Пароль должен содержать минимум 4 символа");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}