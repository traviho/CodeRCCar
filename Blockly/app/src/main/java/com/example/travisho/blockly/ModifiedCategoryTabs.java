package com.example.travisho.blockly;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.blockly.android.ui.CategoryTabs;

/**
 * Created by Travis Ho on 3/22/2017.
 */

public class ModifiedCategoryTabs extends CategoryTabs{

    public ModifiedCategoryTabs(Context context) {
        super(context);
    }
    public ModifiedCategoryTabs(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        setLabelAdapter(new ModifiedDefaultTabsAdapter());
    }

    public ModifiedCategoryTabs(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        setLabelAdapter(new ModifiedDefaultTabsAdapter());
    }

    protected class ModifiedDefaultTabsAdapter extends DefaultTabsAdapter{

        @Override
        public View onCreateLabel() {
            return (TextView) LayoutInflater.from(getContext())
                    .inflate(R.layout.modified_default_toolbox_tab, null);
        }
    }
}
