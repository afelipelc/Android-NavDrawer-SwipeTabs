package mx.edu.utim.ticsi.felipe.navdrawer.fragmentstabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.edu.utim.ticsi.felipe.navdrawer.R;

/**
 * Created by felipe on 4/03/15.
 */
public class ItemTwoFragmentTab extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    public ItemTwoFragmentTab() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_two,
                container, false);
        /*
        TextView dummyTextView = (TextView) rootView
                .findViewById(R.id.section_label);
        dummyTextView.setText(Integer.toString(getArguments().getInt(
                ARG_SECTION_NUMBER)));*/

        return rootView;
    }
}