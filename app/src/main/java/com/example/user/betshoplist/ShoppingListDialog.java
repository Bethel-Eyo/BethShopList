package com.example.user.betshoplist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Config;
import com.firebase.client.Firebase;
import com.firebase.client.authentication.*;

/**
 * Created by user on 3/28/2017.
 */

public class ShoppingListDialog extends DialogFragment {
    EditText editText;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /*making the dialog fragment pop up as an alertbox*/
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.shopping_list_dialog, null);
        editText = (EditText) rootView.findViewById(R.id.added_item);
        /**
         * Call addMeal() when user taps "Done" keyboard action
         */
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    addItem();
                }
                return true;
            }
        });

        /* Inflate and set the layout for the dialog */
        /* Pass null as the parent view because its going in the dialog layout */
        builder.setView(rootView)
                /* Add action buttons */
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        addItem();
                    }
                });

        return builder.create();

    }

    /**
     * Open the keyboard automatically when the dialog fragment is opened
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void addItem(){
        Firebase ref = new Firebase(Constants.FIREBASE_URL);
        String enteredList = editText.getText().toString();
        ref.child("listName").setValue(enteredList);
    }

    public static ShoppingListDialog newInstance() {

        Bundle args = new Bundle();

        ShoppingListDialog fragment = new ShoppingListDialog();
        fragment.setArguments(args);
        return fragment;
    }
}
