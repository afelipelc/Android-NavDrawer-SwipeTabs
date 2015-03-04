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
package mx.edu.utim.ticsi.felipe.navdrawer.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.edu.utim.ticsi.felipe.navdrawer.MainActivity;
import mx.edu.utim.ticsi.felipe.navdrawer.R;

/**
 * Created by felipe on 4/03/15.
 */
public class PrincipalFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PrincipalFragment newInstance(int sectionNumber) {
        PrincipalFragment fragment = new PrincipalFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PrincipalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}