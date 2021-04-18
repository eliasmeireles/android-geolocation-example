package com.challenge.us.geolocation.app.components;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.challenge.us.geolocation.R;
import com.challenge.us.geolocation.databinding.ComponentMapOptionsBinding;

public class MapOptionsComponent extends ConstraintLayout {

    private ComponentMapOptionsBinding optionsBinding;

    public MapOptionsComponent(@NonNull Context context) {
        super(context);
        onCreate(context);
    }

    public MapOptionsComponent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onCreate(context);
    }

    public MapOptionsComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onCreate(context);
    }

    private void onCreate(Context context) {
        inflate(context, R.layout.component_map_options, this);
        optionsBinding = ComponentMapOptionsBinding.bind(this);
    }
}
