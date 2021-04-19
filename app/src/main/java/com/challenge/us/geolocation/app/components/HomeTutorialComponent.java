package com.challenge.us.geolocation.app.components;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.challenge.us.geolocation.R;
import com.challenge.us.geolocation.databinding.ComponentHomeTutorialBinding;

public class HomeTutorialComponent extends ConstraintLayout {

    private ComponentHomeTutorialBinding tutorialBinding;

    public HomeTutorialComponent(@NonNull Context context) {
        super(context);
        onCreate(context);
    }

    public HomeTutorialComponent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onCreate(context);
    }

    public HomeTutorialComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onCreate(context);
    }

    private void onCreate(Context context) {
        inflate(context, R.layout.component_home_tutorial, this);
        tutorialBinding = ComponentHomeTutorialBinding.bind(this);
        tutorialBinding.tutorialMapOptionComponent.setAsDemoOnly();
        tutorialBinding.imageViewTutorialClose.setOnClickListener(v -> setVisibility(GONE));
    }
}
