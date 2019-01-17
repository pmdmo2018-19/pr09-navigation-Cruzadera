package es.iessaladillo.pedrojoya.pr08.ui.main;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.preference.PreferenceManager;
import es.iessaladillo.pedrojoya.pr08.R;
import es.iessaladillo.pedrojoya.pr08.ui.detail.DetailFragment;
import es.iessaladillo.pedrojoya.pr08.ui.settings.SettingsFragment;
import es.iessaladillo.pedrojoya.pr08.utils.FragmentUtils;

public class MainFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    private TextView lblLorem;
    private SharedPreferences sharedPreferences;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        setupViews();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_main, menu);
    }

    private void setupViews(){
        setupFab(requireView());
        setupToolbar(requireView());
        displayLorem();
    }

    private void setupFab(View view) {
        FloatingActionButton fab = ViewCompat.requireViewById(view, R.id.fabDetail);
        lblLorem = ViewCompat.requireViewById(view, R.id.lblLorem);
        fab.setOnClickListener(l -> navigateToDetail());
    }

    private void navigateToDetail() {
        FragmentUtils.replaceFragmentAddToBackstack(requireActivity().getSupportFragmentManager(),
                R.id.container, DetailFragment.newInstance(), DetailFragment.class.getSimpleName(), DetailFragment.class.getSimpleName(), FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = ViewCompat.requireViewById(view, R.id.toolbar);
        toolbar.setTitle(R.string.fragmentMainTittle);
        toolbar.inflateMenu(R.menu.fragment_main);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.mnuSettings:
                    navigateToSettings();
                    return true;
                default:
                    return false;
            }
        });
    }

    private void navigateToSettings() {
        FragmentUtils.replaceFragmentAddToBackstack(requireActivity().getSupportFragmentManager(),
                R.id.container, SettingsFragment.newInstance(),
                SettingsFragment.class.getSimpleName(), SettingsFragment.class.getSimpleName(),
                FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }

    private void displayLorem() {
        lblLorem.setText(sharedPreferences.getString(getString(R.string.prefLoremType_key),
                getString(R.string.main_latin_ipsum)));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (TextUtils.equals(key, getString(R.string.prefLoremType_key))) {
            displayLorem();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }
}
