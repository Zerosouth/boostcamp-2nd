package project.android.mini2.Fragment;

import java.util.Collections;
import java.util.Comparator;

import project.android.mini2.Data.Item;
import project.android.mini2.Main.MainActivity;

/**
 * Created by qkrqh on 2017-07-14.
 */

public class DistanceFragment extends BaseFragment {

    @Override
    void sorting() {

         Collections.sort(MainActivity.items, new CompareDistance());
    }

    public class CompareDistance implements Comparator<Item>{
        @Override
        public int compare(Item i1, Item i2) {
            if(MainActivity.order == MainActivity.up)
                return i1.getDistance() - i2.getDistance();
            else
                return i2.getDistance() - i1.getDistance();

        }
    }

}
