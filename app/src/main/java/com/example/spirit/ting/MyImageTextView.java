package com.example.spirit.ting;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.spirit.ting.utils.EvaluateUtil;
import com.example.spirit.ting.utils.Util;

public class MyImageTextView extends FrameLayout {

    private String text;
    private int image;
    private TypedArray typedArray;
    private Paint paint;
    private float textSize = 15;
    private int width;
    private int height;
    private int textLeft;
    private int bitmapLeft;
    private Bitmap bitmap;
    private int paddingLeft = 0;
    private int scaleWidth = 16;
    private int scaleHeight = 16;

    public MyImageTextView(@NonNull Context context) {
        this(context, null);
    }

    public MyImageTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageTextView(@NonNull Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        init();
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyImageTextView);
        image = typedArray.getResourceId(R.styleable.MyImageTextView_image, -1);
        text = typedArray.getString(R.styleable.MyImageTextView_text);
        scaleWidth = typedArray.getInteger(R.styleable.MyImageTextView_scaleWidth, getTextRect()
                .height());
        scaleHeight = typedArray.getInteger(R.styleable.MyImageTextView_scaleHeight, getTextRect
                ().height());
        paddingLeft = typedArray.getInteger(R.styleable.MyImageTextView_drawablePaddingLeft, 0);
        textSize = typedArray.getInteger(R.styleable.MyImageTextView_textSize, EvaluateUtil.sp2px
                (15));
        paint.setTextSize(textSize);
        typedArray.recycle();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        //paint.setTextAlign(Paint.Align.CENTER);
//        paint.setTextSize(textSize);
//        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
//        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
//        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
//        //基线中间点的y轴计算公式
//        baseLineY = (int) (getTextRect().centerY() - top / 2 - bottom / 2);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        height = getMeasuredHeight();

        bitmap = scaleBitMap(getBitmap(), scaleWidth, scaleHeight);
        int totalWidth = getTextRect().width() + bitmap.getWidth() + paddingLeft;
        bitmapLeft = width / 2 - totalWidth / 2;
        textLeft = bitmapLeft + bitmap.getWidth() + paddingLeft;
        //System.out.println(bitmap.getWidth() + "##############" + getTextRect().width());
    }

    private Rect getTextRect() {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect;
    }

    private Bitmap getBitmap() {
        return BitmapFactory.decodeResource(Util.getContext().getResources(), image);
    }

    private Bitmap scaleBitMap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) return null;
        int width = origin.getWidth();
        int height = origin.getHeight();
        float scaleWidth = ((float) newWidth) / width+0.16f;
        float scaleHeight = ((float) newHeight) / height+0.16f;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBitmap = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(text, textLeft, (height / 2 + getTextRect().height() / 2), paint);
        canvas.drawBitmap(bitmap, bitmapLeft, height / 2 - (bitmap.getHeight() / 2), paint);
        //canvas.drawLine(0, height / 2, width, height / 2, paint);
    }
}
