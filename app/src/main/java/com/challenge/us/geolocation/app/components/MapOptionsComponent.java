package com.challenge.us.geolocation.app.components;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.BuildConfig;

import com.challenge.us.geolocation.R;
import com.challenge.us.geolocation.databinding.ComponentMapOptionsBinding;

public class MapOptionsComponent extends ConstraintLayout {

    private ComponentMapOptionsBinding optionsBinding;

    private MapOptionDelegate delegate;

    public interface MapOptionDelegate {
        void searByGeolocation(String value);

        void clipCurrentGeolocation();
    }

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
        optionsBinding.inputActionButton.setOnClickListener(v -> delegate.searByGeolocation(optionsBinding.textInputGeolocation.getText().toString()));
        optionsBinding.textViewCopyMapTarget.setOnClickListener(v -> delegate.clipCurrentGeolocation());
    }

    public void setDelegate(MapOptionDelegate delegate) {
        this.delegate = delegate;
    }

    public void showError(boolean show) {
        optionsBinding.inputAlertErrorMsg.setVisibility(show ? VISIBLE : GONE);
    }
}
