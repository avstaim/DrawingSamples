package com.example.avstaim.drawingsamples.cattext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.avstaim.drawingsamples.R;

public class CatTextView extends TextView {

    public CatTextView(Context context) {
        super(context);
    }

    public CatTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CatTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CatTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                switchMode();
            }
        });
    }

    private static final int MODES = 3;

    private static final int T_WIDTH = 73;
    private static final int T_HEIGHT = 98;

    private int mode = 0;

    private int width;
    private int height;

    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);

        this.width = width;
        this.height = height;

        update();
    }


    private void switchMode() {
        mode++;
        if (mode > MODES) {
            mode = 0;
        }
        update();
        invalidate();
    }

    private void update() {
        switch (mode) {
            case 0:
                bigCat();
                return;

            case 1:
                smallCat(Shader.TileMode.CLAMP);
                return;

            case 2:
                smallCat(Shader.TileMode.REPEAT);
                return;

            case 3:
                smallCat(Shader.TileMode.MIRROR);
                return;

            default:
                throw new IllegalArgumentException();
        }
    }

    private void bigCat() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        Bitmap scaled = Bitmap.createScaledBitmap(bitmap, width, height, false);

        Shader shader = new BitmapShader(scaled,
                Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);

        getPaint().setShader(shader);
    }

    private void smallCat(Shader.TileMode tileMode) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        Bitmap scaled = Bitmap.createScaledBitmap(bitmap, T_WIDTH, T_HEIGHT, false);

        Shader shader = new BitmapShader(scaled, tileMode, tileMode);

        getPaint().setShader(shader);
    }

}
