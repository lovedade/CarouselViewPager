package uc.benkkstudio.bscarouselviewpager;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.viewpager.widget.ViewPager;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class BSCarouselViewPager extends ViewPager {

    public static final String ENCHANTED_VIEWPAGER_POSITION = "ENCHANTED_VIEWPAGER_POSITION";
    private boolean mUseAlpha;
    private boolean mUseScale;
    private Boolean disable = false;
    public BSCarouselViewPager(Context context) {
        super(context);

        init();
    }

    public BSCarouselViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        useAlpha();
        useScale();

        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset >= 0f && positionOffset <= 1f) {
                    View curPage = findViewWithTag(ENCHANTED_VIEWPAGER_POSITION + position);

                    if (curPage != null) {
                        if (mUseScale) {
                            curPage.setScaleY(BSCarouselPagerConstants.BIG_SCALE - (BSCarouselPagerConstants.DIFF_SCALE * positionOffset));
                            curPage.setScaleX(BSCarouselPagerConstants.BIG_SCALE - (BSCarouselPagerConstants.DIFF_SCALE * positionOffset));
                        }


                    }

                    View nextPage = findViewWithTag(ENCHANTED_VIEWPAGER_POSITION + (position + 1));
                    if (nextPage != null) {
                        if (mUseScale) {
                            nextPage.setScaleY(BSCarouselPagerConstants.SMALL_SCALE + (BSCarouselPagerConstants.DIFF_SCALE * positionOffset));
                            nextPage.setScaleX(BSCarouselPagerConstants.SMALL_SCALE + (BSCarouselPagerConstants.DIFF_SCALE * positionOffset));
                        }


                    }

                    View previousPage = findViewWithTag(ENCHANTED_VIEWPAGER_POSITION + (position - 1));
                    if (previousPage != null) {
                        if (mUseScale) {
                            previousPage.setScaleY(BSCarouselPagerConstants.SMALL_SCALE + (BSCarouselPagerConstants.DIFF_SCALE * positionOffset));
                            previousPage.setScaleX(BSCarouselPagerConstants.SMALL_SCALE + (BSCarouselPagerConstants.DIFF_SCALE * positionOffset));
                        }


                    }

                    View nextAfterPage = findViewWithTag(ENCHANTED_VIEWPAGER_POSITION + (position + 2));
                    if (nextAfterPage != null) {
                        if (mUseScale) {
                            nextAfterPage.setScaleX(BSCarouselPagerConstants.SMALL_SCALE);
                            nextAfterPage.setScaleY(BSCarouselPagerConstants.SMALL_SCALE);
                        }

                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return !disable && super.onInterceptTouchEvent(event);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return !disable && super.onTouchEvent(event);
    }

    public void disableScroll(Boolean disable){
        this.disable = disable;
    }

    public void useScale() {

    }

    public void useAlpha() {
        mUseAlpha = true;
    }
}
