package es.iessaladillo.pedrojoya.pr08.ui.detail;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import es.iessaladillo.pedrojoya.pr08.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    private FloatingActionButton fab;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViews(requireView());
    }

    private void setupViews(View view) {
        setupToolbar(view);
        fab = ViewCompat.requireViewById(view, R.id.fabSave);
        fab.setOnClickListener(v -> showMessage());
    }

    private void setupToolbar(View view) {
        CollapsingToolbarLayout collapsingToolbarLayout = ViewCompat.requireViewById(view,
                R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitle(getString(R.string.titleDetailFragment));
        Toolbar toolbar = ViewCompat.requireViewById(view, R.id.toolbarDetail);
        ((AppCompatActivity)requireActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.titleSettingsFragment));
        }
    }

    private void showMessage() {
        Snackbar.make(fab,"Se ha pulsado en el botón de guardar" , Snackbar.LENGTH_SHORT).show();
    }

}
