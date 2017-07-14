package project.android.mini2.Fragment;


import java.util.Collections;
import java.util.Comparator;

import project.android.mini2.Data.Item;
import project.android.mini2.Main.MainActivity;

/**
 * Created by qkrqh on 2017-07-14.
 */

public class PopularityFragment extends BaseFragment {

    @Override
    void sorting() {
        Collections.sort(MainActivity.items, new ComparePopularity());

    }

    public class ComparePopularity implements Comparator<Item> {
        @Override
        public int compare(Item i1, Item i2) {
            return i1.getPopular() - (i2.getPopular());
        }
    }
}
