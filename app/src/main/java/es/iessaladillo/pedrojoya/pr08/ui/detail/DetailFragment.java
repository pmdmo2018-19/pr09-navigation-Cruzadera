package es.iessaladillo.pedrojoya.pr08.ui.detail;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.FragmentManager;
import es.iessaladillo.pedrojoya.pr08.R;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    ActionBar actionBar;

    private FloatingActionButton fab;

    public DetailFragment() {
    }

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        fab.setOnClickListener(v -> {showMessage(); goBack();});
    }

    private void goBack() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.popBackStack();
        }
    }

    private void setupToolbar(View view) {
        CollapsingToolbarLayout collapsingToolbarLayout = ViewCompat.requireViewById(view,
                R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitle(getString(R.string.titleDetailFragment));
        Toolbar toolbar = ViewCompat.requireViewById(view, R.id.toolbarDetail);
        actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        ((AppCompatActivity)requireActivity()).setSupportActionBar(toolbar);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.titleSettingsFragment));
        }
    }

    private void showMessage() {
        Toast.makeText(requireContext(), getString(R.string.txtSave), LENGTH_SHORT).show();
    }

}
