package com.thiagomatheusms.exoplayer;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;



import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TestActivityDetail {

    @Rule
    public ActivityTestRule<DetailStepActivity> mDetailStepActivityActivityTestRule =
            new ActivityTestRule<>(DetailStepActivity.class, false, true);

    @Test
    public void checkButtons() {
        onView(withId(R.id.btn_next)).check(matches(isDisplayed()));
    }


}
