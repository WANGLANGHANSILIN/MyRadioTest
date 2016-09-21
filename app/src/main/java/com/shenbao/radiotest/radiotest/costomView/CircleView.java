package com.shenbao.radiotest.radiotest.costomView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by wangl on 2016/9/20 0020.
 */
public class CircleView extends View {

    private int mHeight;//高度
    private int mWidth;//宽度

    private ArrayList<DataClass> dataClasses;//数据集合

    private float startAngle = 0;//其实角度，默认为0

    private Paint paint;//画比

    // 颜色表
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth/2 , mHeight/2);//将画布移动到中心的位置
        float currentAngle = startAngle;
        if (dataClasses == null)
            return;

        float r = (float) (Math.min(mWidth, mHeight) / 2 *1.0);//园的半径

        RectF f = new RectF(-r,-r,r,r);

        for (int i = 0; i < dataClasses.size(); i++) {
            DataClass dataClass = dataClasses.get(i);
            canvas.drawArc(f,currentAngle,dataClass.getAngle(),true,paint);
//            canvas.drawText(dataClass.getName(),);
            currentAngle+=dataClass.getAngle();
        }

    }

    public void setDataClass(ArrayList<DataClass> dataClasses){
        this.dataClasses = dataClasses;
        initData(dataClasses);
        invalidate();
    }

    private void initData(ArrayList<DataClass> dataClasses) {

        if (dataClasses.isEmpty() || dataClasses.size() == 0)
            return;

        int sumValue = 0;
        for (int i = 0; i < dataClasses.size(); i++) {
            DataClass aClass = dataClasses.get(i);
            sumValue += aClass.getValue();

            int j = i % mColors.length;       //设置颜色
            aClass.setColor(mColors[j]);
        }

        float sumAngle = 0;
        for (int i = 0; i < dataClasses.size(); i++) {
            DataClass pie = dataClasses.get(i);

            float percentage = pie.getValue() / sumValue;   // 百分比
            float angle = percentage * 360;                 // 对应的角度

            pie.setPercentage(percentage);                  // 记录百分比
            pie.setAngle(angle);                            // 记录角度大小
            sumAngle += angle;

            Log.i("angle", "" + pie.getAngle());
        }

    }

    public void setStartAngle(float angle){
        this.startAngle = angle;
        invalidate();
    }
}
