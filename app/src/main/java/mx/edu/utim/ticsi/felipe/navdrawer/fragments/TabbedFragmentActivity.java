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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Locale;

import mx.edu.utim.ticsi.felipe.navdrawer.MainActivity;
import mx.edu.utim.ticsi.felipe.navdrawer.R;
import mx.edu.utim.ticsi.felipe.navdrawer.fragmentstabs.ItemOneFragmentTab;
import mx.edu.utim.ticsi.felipe.navdrawer.fragmentstabs.ItemThreeFragmentTab;
import mx.edu.utim.ticsi.felipe.navdrawer.fragmentstabs.ItemTwoFragmentTab;

/**
 * Created by felipe on 4/03/15.
 */
public class TabbedFragmentActivity extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    public static final String TAG = TabbedFragmentActivity.class.getSimpleName();

    /**
     * The {@link android.support.v4.view.ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;


    public static TabbedFragmentActivity newInstance(int sectionNumber) {
        TabbedFragmentActivity fragment = new TabbedFragmentActivity();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabbed_activity, container, false);
        mSectionsPagerAdapter = new SectionsPagerAdapter(
                getChildFragmentManager());

        mViewPager = (ViewPager) v.findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    /**
     * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a DummySectionFragment (defined as a static inner class
            // below) with the page number as its lone argument.
            /*Fragment fragment = new DummySectionFragment();
            Bundle args = new Bundle();
            args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
            fragment.setArguments(args);*/
            //return fragment;

            //replaced with our fragments one by tab
            switch (position){
                case 0:
                    return  new ItemOneFragmentTab();
                case 1:
                    return  new ItemTwoFragmentTab();
                case 2:
                    return  new ItemThreeFragmentTab();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            //get title from array strings as Title tab
            return getResources().getStringArray(R.array.tabs_options)[position].toUpperCase(l);

            //original
            /*
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;*/
        }
    }


}