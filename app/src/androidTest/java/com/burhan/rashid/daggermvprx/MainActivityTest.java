package com.burhan.rashid.daggermvprx;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.burhan.rashid.daggermvprx.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Created by Burhanuddin Rashid on 17-Aug-16.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void checkRecyclerView() throws Exception {
        // Espresso.registerIdlingResources(mainActivityActivityTestRule.getActivity().getCountingIdlingResource());
        Espresso.onView(ViewMatchers.withId(R.id.my_list)).perform(ViewActions.click());
    }
}
