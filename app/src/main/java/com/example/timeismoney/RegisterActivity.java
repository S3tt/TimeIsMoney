package com.example.timeismoney;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private Button registerButton;
    private TextView loginText;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        registerButton = findViewById(R.id.register_button);
        loginText = findViewById(R.id.login_text);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para processar o registro do usuário
                // Você pode acessar os campos de nome, email, senha e confirmar senha usando seus respectivos IDs
                // Valide os campos e registre os dados na Firebase
                if (validateFields()) {
                    registerUser();
                }
            }
        });

        String loginString = "Já possui conta? Faça login";
        SpannableString spannableString = new SpannableString(loginString);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Redirecionar o usuário para a tela de login
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };
        spannableString.setSpan(clickableSpan, 0, loginString.length(), 0);
        loginText.setText(spannableString);
        loginText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private boolean validateFields() {
        // Implemente a validação dos campos de registro (nome, email, senha e confirmar senha)
        // Retorne true se os campos estiverem válidos, caso contrário, retorne false
        return true; // Exemplo: validação simples, considerando todos os campos como válidos
    }

    private void registerUser() {
        // Implemente a lógica para registrar o usuário na Firebase
        // Utilize os valores dos campos de nome, email e senha para criar uma nova conta de usuário
        // Você pode usar a API de autenticação da Firebase para isso (por exemplo, FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)...)
        // Lembre-se de lidar comos casos de sucesso e falha no registro do usuário e fornecer feedback adequado ao usuário
    }
}
