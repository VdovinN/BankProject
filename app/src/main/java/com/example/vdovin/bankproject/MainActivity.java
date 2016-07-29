package com.example.vdovin.bankproject;

import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.vdovin.bankproject.Fragment.AboutFragment;
import com.example.vdovin.bankproject.Fragment.CalculatorFragment;
import com.example.vdovin.bankproject.Fragment.MapsFragment;
import com.example.vdovin.bankproject.Fragment.TableFragment;
import com.example.vdovin.bankproject.controller.ParserController;
import com.example.vdovin.bankproject.controller.SyncController;
import com.example.vdovin.bankproject.datasource.BankDataSource;
import com.example.vdovin.bankproject.datasource.BankExchangeRateDataSource;
import com.example.vdovin.bankproject.datasource.CurrencyDataSource;
import com.example.vdovin.bankproject.extra.Utils;
import com.example.vdovin.bankproject.models.BankExchangeRate;
import com.example.vdovin.bankproject.orm.DatabaseHelper;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.ll_waiting);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*if (Utils.isNetworkAvailable(MainActivity.this)) {
            initDatabase();
            if(Session.loadAllBankExchangeRates(MainActivity.this).size() > 0)
            initNavigationDrawer();
        } else if(Session.loadAllBankExchangeRates(MainActivity.this).size() > 0){
            initNavigationDrawer();
        }*/
        if(Utils.isNetworkAvailable(MainActivity.this)) {
            initDatabase();
        }
        //initNavigationDrawer();


    }


    public void initDatabase(){
        new AsyncTask<Void,Void, Void>(){


            @Override
            protected void onPreExecute() {
                //deleteDatabase(DatabaseHelper.DATABASE_NAME);
            }


            @Override
            protected Void doInBackground(Void... voids) {
                String response = SyncController.getInstance().
                        doSync();
                if(response != null) {
                    //BankDataSource.getmInstance(MainActivity.this).clear();
                    //CurrencyDataSource.getmInstance(MainActivity.this).clear();
                    //BankExchangeRateDataSource.getmInstance(MainActivity.this).clear();
                    ParserController.getInstance()
                            .parseResponse(response);
                    BankDataSource.getmInstance(MainActivity.this)
                            .insert(ParserController.getInstance().getBanksList());
                    CurrencyDataSource.getmInstance(MainActivity.this)
                            .insert(ParserController.getInstance().getCurrenciesList());
                    BankExchangeRateDataSource.getmInstance(MainActivity.this)
                            .insert(ParserController.getInstance().getBankExchangeRateList());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                linearLayout.setVisibility(View.INVISIBLE);
                initNavigationDrawer();
                /*List<BankExchangeRate> test = Session.loadAllBankExchangeRates(MainActivity.this);
                for (int i = 0; i < test.size(); i++) {
                    System.out.println("ID " + test.get(i).getId() + " BANK_ID " + test.get(i).getBankId() + " CURRENCY_ID"  + test.get(i).getCurrencyId());
                }*/

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }
    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = null;
                Class fragmentClass;

                switch (id){
                    case R.id.nav_first_fragment:
                        fragmentClass = TableFragment.class;
                        break;
                    case R.id.nav_second_fragment:
                        fragmentClass = CalculatorFragment.class;
                        break;
                    case R.id.nav_third_fragment:
                        fragmentClass = MapsFragment.class;
                        break;
                    case R.id.about_app:
                        fragmentClass = AboutFragment.class;
                        break;
                    default:
                        fragmentClass = TableFragment.class;
                        break;
                }

                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                drawerLayout.closeDrawers();



                return true;
            }
        });
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.getMenu().performIdentifierAction(R.id.nav_first_fragment, 0);

    }


}
