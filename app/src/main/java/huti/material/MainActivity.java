package huti.material;


import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import SlidingTabs.SlidingTabLayout;
import SlidingTabs.SlidingTabAdapter;



public class MainActivity extends ActionBarActivity {

    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    SlidingTabLayout mSlidingTabLayout;
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initLayout();
        this.bindTabEvents();
        this.bindNavDrawerEvents();

        this.addContent();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //--------------------------------------------------------------------------
        // triggers if the user selects a menu item
        //--------------------------------------------------------------------------
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();

        if (id == R.id.action_exit) {
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        //--------------------------------------------------------------------------
        // make sure the drawer toggle is in the right state, nothing to do here
        //--------------------------------------------------------------------------
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        //--------------------------------------------------------------------------
        // close the nav drawer if user pressed the back button
        // nothing to do here
        //--------------------------------------------------------------------------
        if (mDrawerLayout.isDrawerOpen(Gravity.START | Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    public void initLayout() {
        //--------------------------------------------------------------------------
        // create the material toolbar
        //--------------------------------------------------------------------------
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        //--------------------------------------------------------------------------
        // create the material navdrawer
        //--------------------------------------------------------------------------
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorMainDark));

        //--------------------------------------------------------------------------
        // create the material navdrawer toggle and bind it to the navigation_drawer
        //--------------------------------------------------------------------------
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //--------------------------------------------------------------------------
        // create the viewpager which holds the tab contents
        // tell the viewpager which tabs he have to listen to
        //--------------------------------------------------------------------------
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(5); // tabcachesize (=tabcount for better performance)
        mViewPager.setAdapter(new SlidingTabAdapter());

        //--------------------------------------------------------------------------
        // create sliding tabs and bind them to the viewpager
        //--------------------------------------------------------------------------
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);

        // use own style rules for tab layout
        mSlidingTabLayout.setCustomTabView(R.layout.toolbar_tab, R.id.toolbar_tab_txtCaption);
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.tab_indicator_color));

        mSlidingTabLayout.setDistributeEvenly(true); // each tab has the same size
        mSlidingTabLayout.setViewPager(mViewPager);

    }

    public void bindTabEvents() {
        // Tab events
        if (mSlidingTabLayout != null) {
            mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset,
                                           int positionOffsetPixels) {
                    // TODO add code tabbar is scrolled
                }

                @Override
                public void onPageSelected(int position) {
                    // TODO add code tab page select
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    // TODO add code tab scrollstate changed
                }
            });
        }
    }

    public void bindNavDrawerEvents() {
        // Click event for one Navigation element
        LinearLayout navButton = (LinearLayout) findViewById(R.id.txtNavButton);
        navButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // close drawer if you want
                /*if (mDrawerLayout.isDrawerOpen(Gravity.START | Gravity.LEFT)) {
                    mDrawerLayout.closeDrawers();
                }*/
                // display a nice toast message
                Toast.makeText(v.getContext(), "navitem Home clicked", Toast.LENGTH_SHORT).show();

                // update loaded Views if you want
                //mViewPager.getAdapter().notifyDataSetChanged();
            }
        });
    }

    public void addContent(){

        // Inflate your Layouts here
        addTab(R.layout.tabcontent_1,"Home");
        addTab(R.layout.tabcontent_2, "Settings");
        addTab(R.layout.tabcontent_3, "Newsfeed");
        addTab(R.layout.tabcontent_1, "Second Home");
    }

    public void addTab(int layout,String tabTitle)
    {
        this.addTab(layout,tabTitle,-1);
    }
    public void addTab(int layout,String tabTitle,int position)
    {
        SlidingTabAdapter mTabs = (SlidingTabAdapter)mViewPager.getAdapter();
        mTabs.addView(getLayoutInflater().inflate(layout,null),tabTitle,position);
        mTabs.notifyDataSetChanged();
        mSlidingTabLayout.populateTabStrip();
    }

    public void removeTab()
    {
        this.removeTab(-1);
    }
    public void removeTab(int position)
    {
        SlidingTabAdapter mTabs = (SlidingTabAdapter)mViewPager.getAdapter();
        mTabs.removeView(position);
        mTabs.notifyDataSetChanged();
        mSlidingTabLayout.populateTabStrip();
    }
}
