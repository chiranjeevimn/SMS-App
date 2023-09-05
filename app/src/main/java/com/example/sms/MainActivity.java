package com.example.sms;
import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 123; // You can choose any unique request code

    private EditText recipientEditText;
    private EditText messageEditText;
    private Button sendButton;

    private PendingIntent sentPendingIntent;
    private PendingIntent deliveredPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipientEditText = findViewById(R.id.editTextRecipient);
        messageEditText = findViewById(R.id.editTextMessage);
        sendButton = findViewById(R.id.buttonSend);

        sentPendingIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT"), PendingIntent.FLAG_IMMUTABLE);
        deliveredPendingIntent = PendingIntent.getBroadcast(this, 1, new Intent("SMS_DELIVERED"), PendingIntent.FLAG_IMMUTABLE);

        sendButton.setOnClickListener(view -> {
            String recipient = recipientEditText.getText().toString();
            String message = messageEditText.getText().toString();

            if (recipient.isEmpty() || message.isEmpty()) {
                Toast.makeText(MainActivity.this, "Recipient and message cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check for permission before sending SMS
            if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted, request it
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            } else {
                // Permission is already granted, send the SMS
                sendSMS(recipient, message);
            }
        });
    }

    private void sendSMS(String recipient, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(recipient, null, message, sentPendingIntent, deliveredPendingIntent);
            Toast.makeText(this,"Message Sent Successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send SMS", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, send the SMS
                String recipient = recipientEditText.getText().toString();
                String message = messageEditText.getText().toString();
                sendSMS(recipient, message);
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(this, "SMS permission denied. Cannot send SMS.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
