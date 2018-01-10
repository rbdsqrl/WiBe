package com.dovo.wibe.resources;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Created by Narendran on 11-12-2017.
 */

public class ScoreBar extends Drawable {

    private Paint mPaint;


    public ScoreBar() {
        mPaint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {

        // get drawable dimensions
        Rect b = getBounds();
        mPaint.setColor(0xFFED0C3D);
        float start = 0;
        float x =  b.width() * (100F/350F);
        float y =  b.width() *(200F/350F);
        float z =  b.width() * (300F/350F);
        float radius = (b.height())/2;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(start, 0, x, b.height(),radius,radius, mPaint);
        }
        canvas.drawRect(start+radius, 0, x, b.height(), mPaint);
        mPaint.setColor(0xFFFA6C00);
        canvas.drawRect(x, 0, y, b.height(), mPaint);
        mPaint.setColor(0xFFECE819);
        canvas.drawRect(y, 0, z, b.height(), mPaint);
        mPaint.setColor(0xFF39D704);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(z, 0, b.width(), b.height(),radius,radius, mPaint);
        }
        canvas.drawRect(z, 0, b.width()-radius, b.height(), mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

}
