package com.example.avstaim.drawingsamples.fourcoloredcats;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ColorCatsView extends ImageView {
    private final Bitmap bitmap;
    private final Canvas canvas;

    private final Bitmap quarter;
    private final Canvas quarterCanvas;

    Paint paint = new Paint();

    private static final int SIZE = 734;
    private static final int HALF_SIZE = SIZE / 2;

    private boolean isBitmapReady = false;

    public ColorCatsView(Context context) {
        super(context);
    }

    public ColorCatsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorCatsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ColorCatsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    {
        bitmap = Bitmap.createBitmap(SIZE, SIZE, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);

        quarter = Bitmap.createBitmap(HALF_SIZE, HALF_SIZE, Bitmap.Config.ARGB_8888);
        quarterCanvas = new Canvas(quarter);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(SIZE, SIZE);
    }

    protected void onDraw(Canvas canvas) {
        if (!isBitmapReady) {
            quarterCanvas.scale(0.5f, 0.5f);
            super.onDraw(quarterCanvas);
            quarterCanvas.scale(2, 2);

            createBitmap();
            quarter.recycle();
            isBitmapReady = true;
        }

        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    private void createBitmap() {
        int centerX = HALF_SIZE;
        int centerY = HALF_SIZE;

        applyColor(paint, Color.BLUE);
        canvas.drawBitmap(quarter, 0, 0, paint);

        applyColor(paint, Color.RED);
        canvas.drawBitmap(quarter, centerX, 0, paint);

        applyColor(paint, Color.GREEN);
        canvas.drawBitmap(quarter, 0, centerY, paint);

        applyColor(paint, Color.YELLOW);
        canvas.drawBitmap(quarter, centerX, centerY, paint);
    }

    private void applyColor(Paint paint, @ColorInt int color) {
        paint.setColorFilter(new LightingColorFilter(color, 0));
    }
}
