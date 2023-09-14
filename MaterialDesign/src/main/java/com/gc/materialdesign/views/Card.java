package com.gc.materialdesign.views;

import com.gc.materialdesign.R;
import com.gc.materialdesign.utils.Utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class Card extends CustomView {


    int backgroundColor = Color.parseColor("#FFFFFF");

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(attrs);
    }


    // Set attributes of XML to View
    protected void setAttributes(AttributeSet attrs) {

        setBackgroundResource(R.drawable.background_button_rectangle);
        //Set background Color
        // Color by resource
        int backgroundColor = attrs.getAttributeResourceValue(ANDROIDXML, "background", -1);
        if (backgroundColor != -1) {
            setBackgroundColor(getResources().getColor(backgroundColor));
        } else {
            // Color by hexadecimal
            String background = attrs.getAttributeValue(ANDROIDXML, "background");
            if (background != null)
                setBackgroundColor(Color.parseColor(background));
            else
                setBackgroundColor(this.backgroundColor);
        }

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Card);
        int aCount = a.getIndexCount();
        for (int i = 0; i < aCount; ++i) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.Card_cornerRadius) {
                float radius = a.getDimension(attr, -1);
                if (radius != -1)
                    setCornerRadius(radius);
            }
        }
        a.recycle();
    }

    // Set color of background
    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
        if (isEnabled())
            beforeBackground = backgroundColor;
        GradientDrawable shape = getCardDrawable();
        shape.setColor(backgroundColor);
    }

    public void setCornerRadius(float radius) {
        try {
            GradientDrawable shape = getCardDrawable();
            shape.setCornerRadius(radius);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private GradientDrawable getCardDrawable() {
        LayerDrawable layer = (LayerDrawable) getBackground();
        return (GradientDrawable) layer.findDrawableByLayerId(R.id.shape_bacground);
    }
}
