package com.example.timeismoney;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import androidx.appcompat.app.AppCompatActivity;

import com.example.timeismoney.RegisterActivity;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private TextView signUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_button);
        signUpText = findViewById(R.id.signup_text);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para verificar as credenciais de login
            }
        });

        // Criação do hyperlink para a área de registro
        String signUpString = "Não possui uma conta? Crie uma aqui.";
        SpannableString spannableString = new SpannableString(signUpString);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                redirectToRegisterActivity();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };

        int startIndex = signUpString.indexOf("Crie uma aqui");
        int endIndex = startIndex + "Crie uma aqui".length();
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        signUpText.setText(spannableString);
        signUpText.setMovementMethod(LinkMovementMethod.getInstance());
        signUpText.setHighlightColor(Color.TRANSPARENT);


    }

    private void redirectToRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}

