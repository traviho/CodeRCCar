package com.example.travisho.blockly;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.blockly.android.CategorySelectorFragment;
import com.google.blockly.android.ui.CategoryView;

/**
 * Created by Travis Ho on 3/21/2017.
 */

public class ModifiedCategorySelectorFragment extends CategorySelectorFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        int layout = R.layout.modified_default_category_start;

        LinearLayout linearLayout = (LinearLayout) inflater.inflate(layout, null);
        ((ImageView) linearLayout.findViewById(R.id.ic_play)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!SendToArduino.runState) {
                    SendToArduino.runState = true;
                    ((BlocklyActivity) getActivity()).doRunCode();
                }
            }
        });
        mCategoryView = (CategoryView) linearLayout.findViewById(R.id.category_fuck);
        mCategoryView.setLabelRotation(mLabelRotation);
        mCategoryView.setScrollOrientation(mScrollOrientation);

        return linearLayout;
    }
}
