package com.example.avstaim.drawingsamples.catmatrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.avstaim.drawingsamples.R;

public class CatMatrixActivity extends AppCompatActivity {
    private static final int MODES = 6;

    private ImageView mCatImage;

    private Bitmap original;

    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_matrix);

        original = BitmapFactory.decodeResource(getResources(), R.drawable.cat);

        mCatImage = (ImageView) findViewById(R.id.cat_image);

        mCatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchMode();
            }
        });

        update();
    }

    private void switchMode() {
        mode++;

        if (mode > MODES) mode = 0;

        update();
    }

    private void update() {
        mCatImage.setImageBitmap(getBitmap());
    }

    private Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(original.getWidth(),
                original.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(
                getColorMatrix()));
        canvas.drawBitmap(original, 0, 0, paint);

        return bitmap;
    }

    private ColorMatrix getColorMatrix() {
        switch (mode) {
            case 0: return getDefaultColorMatrix();
            case 1: return getGrayScaleColorMatrix();
            case 2: return getSepiaColorMatrix();
            case 3: return getBinaryColorMatrix();
            case 4: return getInvertColorMatrix();
            case 5: return getAlphaBlueColorMatrix();
            case 6: return getAlphaPinkColorMatrix();

            default:
                throw new IllegalArgumentException();
        }
    }

    private ColorMatrix getDefaultColorMatrix() {
        return new ColorMatrix();
    }

    private ColorMatrix getGrayScaleColorMatrix() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        return colorMatrix;
    }

    private ColorMatrix getSepiaColorMatrix() {
        ColorMatrix grayScale = getGrayScaleColorMatrix();

        ColorMatrix brown = new ColorMatrix();
        brown.setScale(1, 1, 0.8f, 1);

        // grayScale + brown = sepia
        grayScale.postConcat(brown);

        return grayScale;
    }

    private ColorMatrix getBinaryColorMatrix() {
        ColorMatrix grayScale = getGrayScaleColorMatrix();

        float m = 255f;
        float t = -255 * 128f;

        ColorMatrix threshold = new ColorMatrix(new float[] {
                m, 0, 0, 1, t,
                0, m, 0, 1, t,
                0, 0, m, 1, t,
                0, 0, 0, 1, 0
        });

        // grayScale + threshold = binary
        grayScale.postConcat(threshold);

        return grayScale;
    }

    private ColorMatrix getInvertColorMatrix() {
        return new ColorMatrix(new float[] {
                -1,  0,  0,  0, 255,
                0,  -1,  0,  0, 255,
                0,   0, -1,  0, 255,
                0,   0,  0,  1,   0
        });
    }

    private ColorMatrix getAlphaBlueColorMatrix() {
        return new ColorMatrix(new float[] {
                0,    0,    0,    0,   0,
                0.3f, 0,    0,    0,  50,
                0,    0,    0,    0, 255,
                0.2f, 0.4f, 0.4f, 0, -30
        });
    }

    private ColorMatrix getAlphaPinkColorMatrix() {
        return new ColorMatrix(new float[] {
                0,    0,    0,    0, 255,
                0,    0,    0,    0,   0,
                0.2f, 0,    0,    0,  50,
                0.2f, 0.2f, 0.2f, 0, -20
        });
    }
}
