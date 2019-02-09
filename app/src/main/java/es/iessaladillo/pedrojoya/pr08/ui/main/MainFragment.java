package es.iessaladillo.pedrojoya.pr08.ui.main;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;
import es.iessaladillo.pedrojoya.pr08.R;

public class MainFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    private TextView lblLorem;
    private SharedPreferences sharedPreferences;
    private NavController navController;
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        navController = NavHostFragment.findNavController(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        setupViews();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
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
        navController.navigate(R.id.action_mainFragment_to_detailFragment);
    }

    private void setupToolbar(View view) {
        toolbar = ViewCompat.requireViewById(view, R.id.toolbar);
        toolbar.inflateMenu(R.menu.fragment_main);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_mainFragment_to_settingsFragment:
                    navigateToSettings();
                    return true;
                default:
                    return false;
            }
        });
        setupAppBar();
    }

    private void setupAppBar(){
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
    }

    private void navigateToSettings() {
        navController.navigate(R.id.action_mainFragment_to_settingsFragment);
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
