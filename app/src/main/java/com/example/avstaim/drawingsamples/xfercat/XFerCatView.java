package com.example.avstaim.drawingsamples.xfercat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import com.example.avstaim.drawingsamples.R;

public class XFerCatView extends View {
    public XFerCatView(Context context) {
        super(context);
    }

    public XFerCatView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XFerCatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public XFerCatView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private static final int MODES = 15;
    private static final int SIZE = 734;

    private final Bitmap cat;
    private final Bitmap oval;

    private Paint xferPaint = new Paint();

    private PorterDuff.Mode porterDuffMode = PorterDuff.Mode.DST_IN;
    private Xfermode xfermode = new PorterDuffXfermode(porterDuffMode);

    private TextListener mTextListener;

    private int mode = 6;

    {
        Bitmap tmpCat = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        oval = BitmapFactory.decodeResource(getResources(), R.drawable.oval);

        cat  = Bitmap.createBitmap(tmpCat, 0, 0, SIZE, SIZE);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                switchMode();
            }
        });
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
                porterDuffMode = PorterDuff.Mode.CLEAR;
                break;

            case 1:
                porterDuffMode = PorterDuff.Mode.SRC;
                break;

            case 2:
                porterDuffMode = PorterDuff.Mode.DST;
                break;

            case 3:
                porterDuffMode = PorterDuff.Mode.SRC_OVER;
                break;

            case 4:
                porterDuffMode = PorterDuff.Mode.DST_OVER;
                break;

            case 5:
                porterDuffMode = PorterDuff.Mode.SRC_IN;
                break;

            case 6:
                porterDuffMode = PorterDuff.Mode.DST_IN;
                break;

            case 7:
                porterDuffMode = PorterDuff.Mode.SRC_OUT;
                break;

            case 8:
                porterDuffMode = PorterDuff.Mode.DST_OUT;
                break;

            case 9:
                porterDuffMode = PorterDuff.Mode.SRC_ATOP;
                break;

            case 10:
                porterDuffMode = PorterDuff.Mode.DST_ATOP;
                break;

            case 11:
                porterDuffMode = PorterDuff.Mode.XOR;
                break;

            case 12:
                porterDuffMode = PorterDuff.Mode.DARKEN;
                break;

            case 13:
                porterDuffMode = PorterDuff.Mode.LIGHTEN;
                break;

            case 14:
                porterDuffMode = PorterDuff.Mode.MULTIPLY;
                break;

            case 15:
                porterDuffMode = PorterDuff.Mode.SCREEN;
                break;


            default:
                throw new IllegalArgumentException();
        }

        if (mTextListener != null) {
            mTextListener.onTextChange(porterDuffMode.toString());
        }

        xfermode = new PorterDuffXfermode(porterDuffMode);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the original cat bitmap (DST)
        canvas.drawBitmap(cat, 0, 0, null);

        xferPaint.setXfermode(xfermode);
        // draw oval as SRC using given Xfermode
        canvas.drawBitmap(oval, 0, 0, xferPaint);
    }

    public interface TextListener {
        void onTextChange(String text);
    }

    public void setListener(TextListener listener) {
        mTextListener = listener;
    }
}
