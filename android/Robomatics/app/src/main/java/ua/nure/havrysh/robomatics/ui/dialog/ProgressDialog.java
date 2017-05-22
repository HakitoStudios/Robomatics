package ua.nure.havrysh.robomatics.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.nure.havrysh.robomatics.R;

public class ProgressDialog extends DialogFragment {
    @Override
    public Dialog getDialog() {
        setStyle(STYLE_NO_FRAME,  R.style.AppTheme_ProgressDialog);
        return new AlertDialog.Builder(getContext())
                .create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_progress, container, false);
    }
}
