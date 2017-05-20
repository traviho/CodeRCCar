package com.example.travisho.blockly;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.google.blockly.android.BlocklyActivityHelper;
import com.google.blockly.android.CategorySelectorFragment;
import com.google.blockly.android.WorkspaceFragment;
import com.google.blockly.android.ui.BlockListUI;

/**
 * Created by Travis Ho on 3/21/2017.
 */

public class ModifiedBlocklyActivityHelper extends BlocklyActivityHelper {
    /**
     * Creates the activity helper and initializes Blockly. Must be called during
     * {@link Activity#onCreate}. Executes the following sequence of calls during initialization:
     * <ul>
     * <li>{@link #onCreateFragments} to find fragments</li>
     * <li>{@link #onCreateBlockViewFactory}</li>
     * </ul>
     * Subclasses should override those methods to configure the Blockly environment.
     *
     * @param activity
     * @throws IllegalStateException If error occurs during initialization. Assumes all initial
     *                               compile-time assets are known to be valid.
     */
    public ModifiedBlocklyActivityHelper(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    protected void onCreateFragments() {
        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        mWorkspaceFragment = (WorkspaceFragment)
                fragmentManager.findFragmentById(R.id.blockly_workspace);
        mToolboxBlockList = (BlockListUI) fragmentManager
                .findFragmentById(R.id.blockly_toolbox_ui);
        mCategoryFragment = (CategorySelectorFragment) fragmentManager
                .findFragmentById(R.id.blockly_categories_2);
        mTrashBlockList = (BlockListUI) fragmentManager
                .findFragmentById(R.id.blockly_trash_ui);

        if (mTrashBlockList != null) {
            // TODO(#14): Make trash list a drop location.
        }
    }
}
