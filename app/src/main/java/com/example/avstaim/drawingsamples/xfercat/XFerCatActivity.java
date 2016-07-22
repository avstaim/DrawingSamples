package com.example.avstaim.drawingsamples.xfercat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.avstaim.drawingsamples.R;

public class XFerCatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfer_cat);

        XFerCatView xFerCatView = (XFerCatView) findViewById(R.id.xfercat);
        final TextView textView = (TextView) findViewById(R.id.mode_text);

        xFerCatView.setListener(new XFerCatView.TextListener() {
            @Override
            public void onTextChange(String text) {
                textView.setText(text);
            }
        });
    }
}
