package com.example.timeismoney;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private List<String> messageList;
    private ArrayAdapter<String> messageAdapter;
    private EditText messageEditText;
    private Button sendButton;

    //red
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ListView chatListView = findViewById(R.id.chatListView);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);

        messageList = new ArrayList<>();
        messageAdapter = new ArrayAdapter<String>(this, R.layout.item_message, R.id.messageTextView, messageList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView messageTextView = view.findViewById(R.id.messageTextView);
                messageTextView.setText(getItem(position));
                return view;
            }
        };
        chatListView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(v -> {
            String message = messageEditText.getText().toString().trim();
            if (!message.isEmpty()) {
                messageList.add(message);
                messageAdapter.notifyDataSetChanged();
                messageEditText.setText("");
            }
        });
    }
}
