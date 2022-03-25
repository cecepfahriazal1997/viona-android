package com.viona.mobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;

import com.viona.mobile.R;
import com.viona.mobile.activity.Walkthrough;

public class WalkthroughFragment extends Fragment {
    private static final String ARG_TITLE = "title";
    private static final String ARG_DESC = "desc";
    private static final String ARG_DRAWABLE = "drawable";

    private String TITLE;
    private String DESC;
    private int DRAWABLE;

    private TextView titleWalkthrough, description;
    private ImageView imageWalkthrough;
    private Walkthrough parent;

    public WalkthroughFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent          = (Walkthrough) getActivity();
        if (getArguments() != null) {
            TITLE               = getArguments().getString(ARG_TITLE);
            DESC                = getArguments().getString(ARG_DESC);
            DRAWABLE            = getArguments().getInt(ARG_DRAWABLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_walkthrough, container, false);

        titleWalkthrough            = v.findViewById(R.id.title);
        description                 = v.findViewById(R.id.description);
        imageWalkthrough            = v.findViewById(R.id.image);

        titleWalkthrough.setText(TITLE);
        description.setText(DESC);
        imageWalkthrough.setImageResource(DRAWABLE);
        return v;
    }

    public static com.viona.mobile.fragment.WalkthroughFragment newInstance(CharSequence title, CharSequence description,
                                                                                     @DrawableRes int imageDrawable,
                                                                                     int position) {
        com.viona.mobile.fragment.WalkthroughFragment slide = new com.viona.mobile.fragment.WalkthroughFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title.toString());
        args.putString(ARG_DESC, description.toString());
        args.putInt(ARG_DRAWABLE, imageDrawable);
        slide.setArguments(args);

        return slide;
    }
}
