package com.example.avstaim.drawingsamples;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.avstaim.drawingsamples.catmatrix.CatMatrixActivity;
import com.example.avstaim.drawingsamples.cattext.CatTextActivity;
import com.example.avstaim.drawingsamples.fourcoloredcats.FourColoredCatsActivity;
import com.example.avstaim.drawingsamples.rainbow.RainbowActivity;
import com.example.avstaim.drawingsamples.xfercat.XFerCatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void startActivity(Class<? extends Activity> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    public void onRainbowClick(View view) {
        startActivity(RainbowActivity.class);
    }

    public void onCatTextClick(View view) {
        startActivity(CatTextActivity.class);
    }

    public void onCatMatrixClick(View view) {
        startActivity(CatMatrixActivity.class);
    }

    public void onFourColoredCatsClick(View view) {
        startActivity(FourColoredCatsActivity.class);
    }

    public void onXFerCatClick(View view) {
        startActivity(XFerCatActivity.class);
    }

}
