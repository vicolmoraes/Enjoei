package com.enjoei.vicolmoraes.enjoei.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.enjoei.vicolmoraes.enjoei.R;

import java.util.ArrayList;

public class ProdutoFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private SliderLayout slider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.produto_fragment, container, false);

        iniciarViews(view);
        return view;
    }

    private void iniciarViews(View view) {
        slider = view.findViewById(R.id.sl_produto_slider);

        ArrayList<String> list = new ArrayList();
        list.add("http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        list.add("https://http2.mlstatic.com/manual-de-criaco-de-canario-belga-envio-gratuito-e-imediato-D_NQ_NP_856221-MLB28102770579_092018-F.jpg");
        slider = setarSlider(list, (PagerIndicator) view.findViewById(R.id.sl_produto_pager), view.getContext());
    }

    public SliderLayout setarSlider(ArrayList<String> list, PagerIndicator pager, Context context) {
        for (String item : list) {
            TextSliderView textSliderView = new TextSliderView(context);
            textSliderView.image(item);
            textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
            slider.addSlider(textSliderView);

            RelativeLayout f = (RelativeLayout) slider.getCurrentSlider().getView();
            LinearLayout p = f.findViewById(R.id.description_layout);
            p.setVisibility(View.GONE);
        }

        slider.setDuration(4000);
        slider.addOnPageChangeListener(this);
        slider.setCustomIndicator(pager);

        return slider;
    }

    @Override
    public void onStop() {
        slider.stopAutoCycle();
        super.onStop();
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