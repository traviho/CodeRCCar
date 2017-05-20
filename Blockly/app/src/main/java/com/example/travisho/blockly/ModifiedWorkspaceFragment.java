package com.example.travisho.blockly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.blockly.android.WorkspaceFragment;
import com.google.blockly.android.control.BlocklyController;
import com.google.blockly.android.ui.BlockView;
import com.google.blockly.android.ui.VirtualWorkspaceView;
import com.google.blockly.android.ui.WorkspaceView;
import com.google.blockly.model.Workspace;

/**
 * Created by Travis Ho on 3/23/2017.
 */

public class ModifiedWorkspaceFragment extends WorkspaceFragment {

    private BlocklyController mController;
    private Workspace mWorkspace;
    private VirtualWorkspaceView mVirtualWorkspaceView;
    private WorkspaceView mWorkspaceView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.modified_fragment_workspace, container, false);

        mVirtualWorkspaceView =
                (VirtualWorkspaceView) rootView.findViewById(R.id.virtual_workspace);
        mWorkspaceView = (WorkspaceView) rootView.findViewById(R.id.workspace);

        if (mController != null) {
            mVirtualWorkspaceView.setZoomBehavior(
                    mController.getWorkspaceHelper().getZoomBehavior());
        }

        return rootView;
    }

    /**
     * Sets the controller to use in this fragment for instantiating views. This should be the same
     * controller used for any associated {@link FlyoutFragment FlyoutFragments}.
     *
     * @param controller The controller backing this fragment.
     */
    public void setController(BlocklyController controller) {
        if (controller == mController) {
            return; // no-op
        }

        mController = controller;
        mWorkspace = (controller == null) ? null : mController.getWorkspace();
        mController.initWorkspaceView(mWorkspaceView);

        if (mVirtualWorkspaceView != null) {
            mVirtualWorkspaceView.setZoomBehavior(
                    mController.getWorkspaceHelper().getZoomBehavior());
        }
    }

    /**
     * @return The workspace being used by this fragment.
     */
    public Workspace getWorkspace() {
        return mWorkspace;
    }

    /**
     * @return The {@link WorkspaceView} inside this fragment.
     */
    public WorkspaceView getWorkspaceView() {
        return mWorkspaceView;
    }
}

