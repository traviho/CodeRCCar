package com.example.travisho.blockly;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.google.blockly.android.ui.CategoryView;
import com.google.blockly.model.BlocklyCategory;

/**
 * Created by Travis Ho on 3/23/2017.
 */

public class ModifiedCategoryView extends CategoryView {

    public ModifiedCategoryView(Context context) {
        super(context);
    }

    public ModifiedCategoryView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public ModifiedCategoryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setCurrentCategory(@Nullable BlocklyCategory category) {
        if (category == mCurrentCategory) {
            return;
        }
        mCurrentCategory = category;
        mCategoryTabs.setSelectedCategory(category);
    }
}
