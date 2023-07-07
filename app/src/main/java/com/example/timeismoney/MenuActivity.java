package com.example.timeismoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private TextView welcomeText;
    private TextView usernameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        welcomeText = findViewById(R.id.welcome_text);
        usernameText = findViewById(R.id.username_text);

        // Simulando a autenticação do usuário
        String username = authenticateUser("nome_de_usuario", "senha"); // Substitua com a lógica de autenticação real

        if (username != null) {
            welcomeText.setVisibility(View.VISIBLE);
            usernameText.setVisibility(View.VISIBLE);
            usernameText.setText(username);
        }
    }

    // Método para simular a autenticação do usuário
    private String authenticateUser(String username, String password) {
        // Aqui você deve implementar a lógica de autenticação do usuário e acessar a base de dados para obter o nome correspondente.
        // Neste exemplo, estamos apenas retornando o nome fixo "John Doe" para ilustração.
        if (username.equals("nome_de_usuario") && password.equals("senha")) {
            return "John Doe";
        }
        return null;
    }

    // Resto da classe MainActivity
}

