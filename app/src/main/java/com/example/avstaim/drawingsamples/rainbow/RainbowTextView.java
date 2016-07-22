package com.example.avstaim.drawingsamples.rainbow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

public class RainbowTextView extends TextView {

    public RainbowTextView(Context context) {
        super(context);
    }

    public RainbowTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RainbowTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RainbowTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private static final int[] RAINBOW = {
            Color.RED,
            Color.YELLOW,
            Color.GREEN,
            Color.BLUE,
            Color.MAGENTA
    };

    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);

        Shader shader = new LinearGradient(0, 0, width, height, RAINBOW,
                null, Shader.TileMode.MIRROR);

        getPaint().setShader(shader);
    }
}
