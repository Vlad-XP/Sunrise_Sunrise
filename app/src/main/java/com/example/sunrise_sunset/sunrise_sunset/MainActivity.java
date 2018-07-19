package com.example.sunrise_sunset.sunrise_sunset;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements WorkerCommunicationCallback, ConnectionReceiver.ConnectionReceiverListener {

    private TextView mTv;
    private EditText mET;
    private Button mButton;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mET = findViewById(R.id.editText);
        mTv = findViewById(R.id.textView);
        mButton = findViewById(R.id.button);

        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Please wait...");
        mDialog.setCancelable(false);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkConnection()) {
                    WorkerThread workerThread = new WorkerThread(mET.getText().toString(), MainActivity.this);
                    workerThread.start();
                    showDialog();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        AppConnectivity.getInstance().setConnectionListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            mTv.setText(getResources().getString(R.string.NoInternet_Message));
        } else {
            mTv.setText("");
        }
    }

    @Override
    public void errorMessage(final String error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTv.setText(error);
                hideDialog();
            }
        });
    }

    @Override
    public void results(String[] res) {

        final String result = convertTimeFromUTCtoDeviceLocalTime(res[0]) + "  " + convertTimeFromUTCtoDeviceLocalTime(res[1]);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTv.setText(result);
                hideDialog();
            }
        });
    }

    private String convertTimeFromUTCtoDeviceLocalTime(String utcTime) {

        // Time converting
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        Date date;
        long timeInMilliseconds = 0;
        try {
            // time format given by sunrise-sunset.org api
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+SS:SS").parse(utcTime);
            timeInMilliseconds = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        timeInMilliseconds += tz.getOffset(cal.getTimeInMillis());
        cal.setTimeInMillis(timeInMilliseconds);

        return new SimpleDateFormat("HH:mm:ss").format(cal.getTime());
    }

    private void showDialog() {
        if (!mDialog.isShowing())
            mDialog.show();
    }

    private void hideDialog() {
        if (mDialog.isShowing())
            mDialog.dismiss();
    }

    private boolean checkConnection() {
        boolean isConnected = ConnectionReceiver.isConnected();
        if (!isConnected) {
            mTv.setText(getResources().getString(R.string.NoInternet_Message));
        }
        return isConnected;
    }
}
