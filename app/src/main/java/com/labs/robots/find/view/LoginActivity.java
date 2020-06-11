package com.labs.robots.find.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.labs.robots.find.R;
import com.labs.robots.find.model.UserModel;
import com.labs.robots.find.presenter.LoginPresenter;
import com.labs.robots.find.utils.Pref;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity implements LoginView{
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private LoginPresenter loginPresenter;
    ProgressDialog progressDialog;

    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        Pref prefManager = Pref.getInstance(LoginActivity.this);

        if(prefManager.isLoggedIn()) {
            Intent intent = new Intent(this, MainActivity.class);
//            intent.putExtra(Pref.EXTRA_USER, prefManager.getUser());
            startActivity(intent);
            finish();
        }

        loginPresenter = new LoginPresenter(this);
        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed("e");
            return;
        }

        _loginButton.setEnabled(false);


        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

       loginPresenter.login(email, password);


    }


    public void onLoginSuccess(UserModel userModel) {
        progressDialog.dismiss();
        _loginButton.setEnabled(true);
        if(userModel.isError()) {
            Toast.makeText(getBaseContext(), "Неправильный email или пароль", Toast.LENGTH_LONG).show();
        } else {
            //Toast.makeText(getBaseContext(), userModel.getPassword(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Pref.EXTRA_USER, userModel.getUser());
            Pref prefManager = Pref.getInstance(LoginActivity.this);
            prefManager.setUserLogin(userModel.getUser());
            startActivity(intent);
            finish();
        }  //finish();
    }

    public void onLoginFailed(String error) {
        progressDialog.dismiss();
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
