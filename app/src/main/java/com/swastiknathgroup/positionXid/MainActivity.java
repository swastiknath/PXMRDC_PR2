package com.swastiknathgroup.positionXid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity {


        @VisibleForTesting
        public ProgressDialog mProgressDialog;

        public void showProgressDialog() {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("Communitcating with the PostionX Servers");
                mProgressDialog.setIndeterminate(true);
            }

            mProgressDialog.show();
        }

        public void hideProgressDialog() {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }

        public void hideKeyboard(View view) {
            final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }

        @Override
        public void onStop() {
            super.onStop();
            hideProgressDialog();
        }

    }

