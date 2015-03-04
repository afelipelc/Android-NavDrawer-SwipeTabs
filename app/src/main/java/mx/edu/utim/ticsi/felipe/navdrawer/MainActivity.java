/**
 * Ejemplo de Navigation Drawer personalizado y Fragment con SwipeTabs
 *
 * Elaborado por Felipe Lima afelipelc@gmail.com marzo.2015
 * Basado en el ejemplo http://androideity.com/2013/12/16/android-navigation-drawer-parte-1/ -Nav Drawer-
 * y en http://www.paulusworld.com/technical/android-navigationdrawer-sliding-tabs -Tabs-
 *
 * El trabajo realizado consistio en unificar una aplicacion con estas caracteristicas ya que regularmente encontramos ejemplos con una sola caracteristica
 *
 */

package mx.edu.utim.ticsi.felipe.navdrawer;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

import mx.edu.utim.ticsi.felipe.navdrawer.fragments.PrincipalFragment;
import mx.edu.utim.ticsi.felipe.navdrawer.fragments.TabbedFragmentActivity;


public class MainActivity extends ActionBarActivity {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private String[] titulos;
    private DrawerLayout NavDrawerLayout;
    private ListView NavList;
    private ArrayList<Menu_Item> NavItms;
    private TypedArray NavIcons;
    NavigationAdapter NavAdapter;

    private ActionBarDrawerToggle mDrawerToggle;


    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Drawer Layout
        NavDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Lista
        NavList = (ListView) findViewById(R.id.lista);
        //Declaramos el header el caul sera el layout de header.xml
        View header = getLayoutInflater().inflate(R.layout.header, null);
        //Establecemos header
        NavList.addHeaderView(header);
        //Tomamos listado  de imgs desde drawable
        NavIcons = getResources().obtainTypedArray(R.array.navigation_iconos);
        //Tomamos listado  de titulos desde el string-array de los recursos @string/nav_options
        titulos = getResources().getStringArray(R.array.nav_options);
        //Listado de titulos de barra de navegacion
        NavItms = new ArrayList<Menu_Item>();
        //Agregamos objetos Item_objct al array
        //Perfil
        NavItms.add(new Menu_Item(titulos[0], NavIcons.getResourceId(0, -1)));
        //Favoritos
        NavItms.add(new Menu_Item(titulos[1], NavIcons.getResourceId(1, -1)));
        //Eventos
        NavItms.add(new Menu_Item(titulos[2], NavIcons.getResourceId(2, -1)));
        //Lugares
        NavItms.add(new Menu_Item(titulos[3], NavIcons.getResourceId(3, -1)));
        //Etiquetas
        NavItms.add(new Menu_Item(titulos[4], NavIcons.getResourceId(4, -1)));
        //Configuracion
        NavItms.add(new Menu_Item(titulos[5], NavIcons.getResourceId(5, -1)));
        //Share
        NavItms.add(new Menu_Item(titulos[6], NavIcons.getResourceId(6, -1)));
        //Declaramos y seteamos nuestrp adaptador al cual le pasamos el array con los titulos
        NavAdapter= new NavigationAdapter(this,NavItms);
        NavList.setAdapter(NavAdapter);
        //Siempre vamos a mostrar el mismo titulo

        mTitle = getTitle();

        //Declaramos el mDrawerToggle y las imgs a utilizar
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                NavDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* Icono de navegacion*/
                R.string.app_name,  /* "open drawer" description */
                R.string.app_name  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                Log.e("Cerrado completo", "!!");
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                Log.e("Apertura completa", "!!");
            }
        };

        // Establecemos que mDrawerToggle declarado anteriormente sea el DrawerListener
        NavDrawerLayout.setDrawerListener(mDrawerToggle);
        //Establecemos que el ActionBar muestre el Boton Home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Establecemos la accion al clickear sobre cualquier item del menu.
        //De la misma forma que hariamos en una app comun con un listview.
        NavList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {

                ShowFragment(position);
            }
        });

        //Cuando la aplicacion cargue por defecto mostrar la opcion Home
        ShowFragment(1);

    }

    private void ShowFragment(int position){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        switch (position){
            case 1:
                ft.replace(R.id.container, PrincipalFragment.newInstance(position))
                    .commit();
                break;
            case 2:
                ft.replace(R.id.container, TabbedFragmentActivity.newInstance(position))
                        .commit();
                break;
        }

        NavDrawerLayout.closeDrawer(Gravity.START); // close the DrawerToggle
    }

    public void onSectionAttached(int number) {
        //get title of selected option // showing current fragment
        mTitle = getResources().getStringArray(R.array.nav_options)[number-1];
        /*switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }*/
        restoreActionBar(); // restore actionBar with the new Title of current fragment
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mDrawerToggle.isDrawerIndicatorEnabled()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            Log.e("mDrawerToggle pushed", "x");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}
