package com.thiagomatheusms.exoplayer;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.anything;

import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thiagomatheusms.exoplayer.Fragments.IngredientsListFragment;
import com.thiagomatheusms.exoplayer.Model.Recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Observable;

import okhttp3.OkHttpClient;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    public final ActivityTestRule<MainActivity> myActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, false, true);


    private static final Intent MY_ACTIVITY_INTENT = new Intent(InstrumentationRegistry.getTargetContext(), MainActivity.class);


    @Before
    public void setup() {
        myActivityTestRule.launchActivity(MY_ACTIVITY_INTENT);

    }


    @Test
    public void checkRecipesRecyclerView_and_SwitchPositionOnClick() {

        TestUtils.delay(1000);
        onView(withId(R.id.recyclerView_recipes))
                .check(matches(hasDescendant(withId(R.id.tv_recipeName)))).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recyclerView_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1,
                        click()));
    }


}


