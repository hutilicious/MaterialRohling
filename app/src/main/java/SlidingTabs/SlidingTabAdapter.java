package SlidingTabs;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by crothhass on 16.09.2015.
 */
public class SlidingTabAdapter extends PagerAdapter {

    SparseArray<View> views = new SparseArray<View>();
    SparseArray<String> pageTitles = new SparseArray<String>();

    /**
     * @return the number of pages to display
     */
    @Override
    public int getCount() {
        return views.size();
    }

    /**
     * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
     * same object as the {@link View} added to the {@link ViewPager}.
     */
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o == view;
    }

    /**
     * Return the title of the item at {@code position}. This is important as what this method
     * returns is what is displayed in the {@link SlidingTabLayout}.
     * <p/>
     * Here we construct one using the position value, but for real application the title should
     * refer to the item's contents.
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles.get(position);
    }

    /**
     * Instantiate the {@link View} which should be displayed at {@code position}. Here we
     * inflate a layout from the apps resources and then change the text view to signify the position.
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = views.get(position);
        container.addView(v);
        return v;
    }

    /**
     * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
     * {@link View}.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        views.remove(position);
    }

    public int addView(View v, String title) {
        return this.addView(v, title, -1);
    }

    public int addView(View v, String title, int position) {
        if (position == -1) {
            position = views.size();
        }
        views.put(position, v);
        pageTitles.put(position, title);
        return position;
    }

    public int removeView() {
        return this.removeView(-1);
    }

    public int removeView(int position) {
        if (position == -1) {
            position = views.size() - 1;
        }
        if (position > 0) {
            views.remove(position);
            pageTitles.remove(position);
        }
        return position;
    }

}
