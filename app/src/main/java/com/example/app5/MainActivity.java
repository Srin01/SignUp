package com.example.app5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextInputEditText email_input;
    TextInputEditText password_input;
    TextInputEditText first_name_input;
    TextInputEditText last_name_input;
    TextInputEditText confirm_password_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setSupportActionBar(toolbar);
        setUpListerner();
    }

    private void setUpListerner()
    {
        email_input.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                TextInputLayout emailLayout = findViewById(R.id.textInputLayout);
                String email_pattern = "^\\w+@[a-zA-Z]+?\\.[a-zA-Z]{2,3}$";
                Pattern email_regex = Pattern.compile(email_pattern);
                Matcher email_matcher = email_regex.matcher(charSequence.toString());
                if(!email_matcher.find())
                {
                    emailLayout.setError("Please enter a valid email-id");
                    emailLayout.setErrorEnabled(true);
                }
                else
                {
                    emailLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
        password_input.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TextInputLayout passwordLayout = findViewById(R.id.textInputLayout2);
                String password_pattern = "(\\w+|\\d+){8,}$";
                Pattern password_regex = Pattern.compile(password_pattern);
                Matcher matcher = password_regex.matcher(charSequence.toString());
                if(!matcher.find())
                {
                    passwordLayout.setError("Password length must be minimum of 8");
                    passwordLayout.setErrorEnabled(true);
                }
                else
                {
                    passwordLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void bindViews()
    {
        toolbar = findViewById(R.id.toolbar);
        email_input = findViewById(R.id.textinputEitText_email);
        password_input = findViewById(R.id.textinputEditText_password);
        first_name_input = findViewById(R.id.textinputFirstName);
        last_name_input = findViewById(R.id.textinputLastName);
        confirm_password_input = findViewById(R.id.textinputEditText_password_confirm);
    }

    private boolean validateInput(String email, String password, String first_name_input, String last_name_input)
    {
        if(email.equals(""))
        {
            TextInputLayout emailLayout = findViewById(R.id.textInputLayout);
            emailLayout.setError("Please enter a valid email-id");
            emailLayout.setErrorEnabled(true);
            return false;
        }
        if(password.equals(""))
        {
            TextInputLayout passwordLayout = findViewById(R.id.textInputLayout2);
            passwordLayout.setError("Please enter a valid password");
            passwordLayout.setErrorEnabled(true);
            return false;
        }
        if(first_name_input.equals(""))
        {
            TextInputLayout firsntNameLayout = findViewById(R.id.textInputLayout_firstName);
            firsntNameLayout.setError("First Name cannot be empty");
            firsntNameLayout.setErrorEnabled(true);
            return false;
        }
        if(last_name_input.equals(""))
        {
            TextInputLayout lastNameInput = findViewById(R.id.textInputLayout_lastName);
            lastNameInput.setError("Last Name cannot be empty");
            lastNameInput.setErrorEnabled(true);
            return false;
        }
        if(!password.equals(confirm_password_input.getText().toString()))
        {
            TextInputLayout pwd = findViewById(R.id.textInputLayout2_confirm);
            pwd.setError("Password doesn't match");
            pwd.setErrorEnabled(true);
            return false;
        }
        return true;
    }

    public void signUp(View view) {
        String email = email_input.getText().toString();
        String password = password_input.getText().toString();
        String firstName = first_name_input.getText().toString();
        String LastName = last_name_input.getText().toString();

        if(validateInput(email,password, firstName, LastName))
            Toast.makeText(this, email +" " + password + " Hii "+ firstName + LastName, Toast.LENGTH_SHORT).show();
    }

    public void showp(View view) {
        password_input.setInputType(InputType.TYPE_CLASS_TEXT);
    }
    public void show(View view) {
        confirm_password_input.setInputType(InputType.TYPE_CLASS_TEXT);
    }

}