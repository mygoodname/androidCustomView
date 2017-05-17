package com.example.administrator.overrideviewgroup;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 *
 * Created by Administrator on 2017/5/7.
 */

public class DragView extends FrameLayout {
    private View leftView,mainView;
    private ViewDragHelper viewDragHelper;
    private int mainViewWidth;
    private int mwidth;

    public DragView(Context context) {
        this(context,null);
    }

    public DragView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * ViewDragHelper.create() 中的第二个参数为最小敏感度范围 值也小也灵敏
         */
        viewDragHelper = ViewDragHelper.create(this,1.0f,callBack);
    }

    /**
     * 加载完xml后调用的方法 一般用来初始化子view
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        leftView=getChildAt(0);
        mainView=getChildAt(1);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mainViewWidth=mainView.getMeasuredWidth();
        mwidth= (int) (mainViewWidth*0.6);
    }

    /**
     * 是否拦截触摸事件（这里交由viewDragHelper决定是否处理）
     * 此方法将framlayout 后的点击事件由viewDragHelper处理
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    /**
     * 由viewDragHelper.processTouchEvent(event)决定如何处理触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    ViewDragHelper.Callback callBack = new ViewDragHelper.Callback() {
        /**
         * 是否捕获子参数中的child 返回true表示可拖动(默认不可拖动)
         * pointerId  为多点触摸时用得参数
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        /**
         * 横向拖拽得范围
         * @param child
         * @return
         */
        @Override
        public int getViewHorizontalDragRange(View child) {
            return mwidth;
        }

        /**
         *
         * @param child
         * @param left  为建议的距离
         * @param dx   为拖动的距离
         * @return
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
        }

        /**
         * 子view位置变化时伴随的动作
         * @param changedView
         * @param left
         * @param top
         * @param dx
         * @param dy
         */
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
        }
    };
}
