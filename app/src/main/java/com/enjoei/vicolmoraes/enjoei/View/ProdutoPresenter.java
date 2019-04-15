package com.enjoei.vicolmoraes.enjoei.View;

import android.content.Context;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;

interface HomePresenterInterface {
    SliderLayout setarSlider(SliderLayout slider, ArrayList<String> list, PagerIndicator pager, Context context);
}

public class ProdutoPresenter implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, HomePresenterInterface {

    @Override
    public SliderLayout setarSlider(SliderLayout slider, ArrayList<String> list, PagerIndicator pager, Context context) {
        for (String item : list) {
            TextSliderView textSliderView = new TextSliderView(context);
            textSliderView.image(item);
            textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);

            slider.addSlider(textSliderView);
        }
        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setDuration(4000);
        slider.addOnPageChangeListener(this);
        slider.setCustomIndicator(pager);

        return slider;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
