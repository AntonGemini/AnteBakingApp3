package com.example.asassa.bakingapp3;

import android.media.session.PlaybackState;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.assertThat;

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
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class)))).check(matches(withText("Nutella Pie")));
        onData(anything()).inAdapterView(withId(R.id.rv_steps)).atPosition(0).perform(click());
        onView(allOf(withId(R.id.exo_details_video),withClassName(is(SimpleExoPlayerView.class.getName()))))
                .check(new VideoViewAssertion());
    }

    class VideoViewAssertion implements ViewAssertion
    {

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            SimpleExoPlayerView exoPlayerView = (SimpleExoPlayerView) view;
            SimpleExoPlayer player = exoPlayerView.getPlayer();
            Matcher<Boolean> matcher = is(true);
            int state = player.getPlaybackState();
            Log.i("PLAYER_STATE",String.valueOf(state));
            if (state == PlaybackState.STATE_BUFFERING || state == PlaybackState.STATE_PLAYING
                   || state == PlaybackState.STATE_PAUSED )
            {
                assertThat(true,matcher);
            }
            else
            {
                assertThat(false,matcher);
            }
        }
    }

}
