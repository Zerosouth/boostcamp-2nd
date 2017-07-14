package project.android.mini2.Fragment;

import java.util.Collections;
import java.util.Comparator;

import project.android.mini2.Data.Item;
import project.android.mini2.Main.MainActivity;

/**
 * Created by qkrqh on 2017-07-14.
 */

public class RecentlyFragment extends BaseFragment {

    @Override
    void sorting() {
        Collections.sort(MainActivity.items, new CompareRecent());

    }

    public class CompareRecent implements Comparator<Item> {
        @Override
        public int compare(Item i1, Item i2) {
            return i1.getRecent() - (i2.getRecent());
        }
    }
}
