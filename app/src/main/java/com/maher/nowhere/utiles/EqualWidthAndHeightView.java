package com.maher.nowhere.utiles;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by RaniaH on 31/10/2017.
 */

public class EqualWidthAndHeightView extends android.support.v7.widget.AppCompatImageView {
    public EqualWidthAndHeightView(Context context) {
        super(context);
    }

    public EqualWidthAndHeightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EqualWidthAndHeightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int width, int height) {
        // note we are applying the width value as the height
        super.onMeasure(width, width);
    }
}
