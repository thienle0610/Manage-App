package com.example.manageapp.activities;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manageapp.R;
import com.example.manageapp.activities.dummy.DummyContent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A fragment representing a list of Items.
 */
public class ExpenseList extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ExpenseList() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ExpenseList newInstance(int columnCount) {
        ExpenseList fragment = new ExpenseList();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
            List<CustomerModel> customerModelList = dataBaseHelper.getEveryone();
            List<DummyContent.DummyItem> dummyItemList = new ArrayList<>();
            for (int i = 0; i < customerModelList.size(); i++)
            {
                CustomerModel customerModel = customerModelList.get(i);
                DummyContent.DummyItem dummyItem = new DummyContent.DummyItem("" + customerModel.getCustomerID(), customerModel.getName(), customerModel.getCategory(), "" + customerModel.getAmount(), "" + customerModel.getDay() + "/" + customerModel.getMonth() + "/" + customerModel.getYear());
                dummyItemList.add(dummyItem);
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(dummyItemList));
        }
        return view;
    }
}