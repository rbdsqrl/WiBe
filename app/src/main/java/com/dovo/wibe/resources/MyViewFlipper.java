package com.dovo.wibe.resources;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

import com.dovo.wibe.R;

/**
 * Created by Narendran on 11-12-2017.
 */

public class MyViewFlipper extends ViewFlipper {
    Paint paint = new Paint();

    public MyViewFlipper(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);
        int width = getWidth();

        float margin = 5;
        float radius = 10;
        float cx = (width / 2)-((radius + margin) * 2 * getChildCount() / 2);
        float cy = getHeight()-15;
        float selectedRadius = 12;
        canvas.save();

        for (int i = 0; i < getChildCount(); i++)
        {
            if (i == getDisplayedChild())
            {
                paint.setColor(getResources().getColor(R.color.colorSelected));
                canvas.drawCircle(cx, cy, selectedRadius, paint);

            } else
            {
                paint.setColor(getResources().getColor(R.color.colorUnselected));
                canvas.drawCircle(cx, cy, radius, paint);
            }
            cx += 2 * (radius + margin);
        }
        canvas.restore();
    }
}