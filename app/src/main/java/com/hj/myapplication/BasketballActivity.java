package com.hj.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class BasketballActivity extends AppCompatActivity {

    private TextView mApointTextView;
    private TextView mBpointTextView;

    private int mApointText;
    private int mBpointText;

    private Button mAplus3Button;
    private Button mAplus2Button;
    private Button mAfreeButton;

    private Button mBplus3Button;
    private Button mBplus2Button;
    private Button mBfreeButton;

    private Button mResetButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        setContentView(R.layout.activity_basketball);


        mAplus3Button = (Button) findViewById(R.id.a_plus3_button);
        mAplus2Button = (Button) findViewById(R.id.a_plus2_button);
        mAfreeButton = (Button) findViewById(R.id.a_free_button);

        mBplus3Button = (Button) findViewById(R.id.b_plus3_button);
        mBplus2Button = (Button) findViewById(R.id.b_plus2_button);
        mBfreeButton = (Button) findViewById(R.id.b_free_button);

        mResetButton = (Button) findViewById(R.id.reset_button);

        mApointTextView = (TextView) findViewById(R.id.a_point_text);
        mBpointTextView = (TextView) findViewById(R.id.b_point_text);

        mAplus3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApointText += 3;
                displayApoint();
            }
        });


        mAplus2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApointText += 2;
                displayApoint();
            }
        });

        mAfreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApointText += 1;
                displayApoint();
            }
        });

        mBplus3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBpointText += 3;
                displayBpoint();

            }
        });

        mBplus2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBpointText += 2;
                displayBpoint();
            }
        });

        mBfreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBpointText += 1;
                displayBpoint();
            }
        });


        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageA = "TeamA " + mApointTextView.getText().toString();
                String messageB = "TeamB " + mBpointTextView.getText().toString();
                Toast.makeText(BasketballActivity.this, messageA + "\n" + messageB, Toast.LENGTH_SHORT).show();

                mApointText = 0;
                mBpointText = 0;

                mApointTextView.setText("" + mApointText);
                mBpointTextView.setText("" + mBpointText);
            }
        });

    }

    private void displayBpoint() {
        mBpointTextView.setText("" + mBpointText);
    }

    private void displayApoint() {
        mApointTextView.setText("" + mApointText);
    }

    private void init() {
        mApointText = 0;
        mBpointText = 0;
    }
}
