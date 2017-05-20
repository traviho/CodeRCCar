package com.example.travisho.blockly;

import android.content.Context;
import android.util.AttributeSet;

import com.google.blockly.android.ZoomBehavior;
import com.google.blockly.android.ui.VirtualWorkspaceView;

/**
 * Created by Travis Ho on 3/23/2017.
 */

public class ModifiedVirtualWorkspaceView extends VirtualWorkspaceView {

    public ModifiedVirtualWorkspaceView(Context context) {
        super(context);
    }

    public ModifiedVirtualWorkspaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ModifiedVirtualWorkspaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //override setScrollable to get rid of UI of scrollbars, override setZoomBehavior to make not scalable
    //Since can't override shouldDrawGrid, override onDraw
}
