/*
 * Copyright 2015 Google Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.blockly.android.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * ViewGroup base class that does not propagate pressed, activated, or selected state to child
 * Views.
 */
public abstract class NonPropagatingViewGroup extends ViewGroup {
    public NonPropagatingViewGroup(Context context) {
        super(context);
    }

    public NonPropagatingViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonPropagatingViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void dispatchSetActivated(boolean activated) {
        // Do nothing.  Do not assign the activated state to children.
    }

    @Override
    public void dispatchSetPressed(boolean pressed) {
        // Do nothing.  Do not assign the pressed state to children.
    }

    @Override
    public void dispatchSetSelected(boolean selected) {
        // Do nothing.  Do not assign the selected state to children.
    }
}
