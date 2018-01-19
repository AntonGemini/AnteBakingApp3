package com.example.asassa.bakingapp3;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.example.asassa.bakingapp3.Utils.Recipe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Created by ASassa on 18.01.2018.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdle()
    {
        mIdlingResource = activityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
    }


    @Test
    public void gridViewTest()
    {
        onView(ViewMatchers.withId(R.id.rv_recipes)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.tv_recipe_name)).check(matches(withText("Nutella Pie")));
    }

}
