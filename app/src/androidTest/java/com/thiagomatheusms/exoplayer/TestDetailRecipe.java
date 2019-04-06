package com.thiagomatheusms.exoplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.thiagomatheusms.exoplayer.Model.Ingredient;
import com.thiagomatheusms.exoplayer.Model.Recipe;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
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

@RunWith(AndroidJUnit4.class)
public class TestDetailRecipe {

    @Rule
    public ActivityTestRule<DetailRecipeActivity> mActivityRule =
            new ActivityTestRule<DetailRecipeActivity>(DetailRecipeActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = getInstrumentation()
                            .getTargetContext();

//                    List<Ingredient> ingredients = new ArrayList<>();
//                    Ingredient a = new Ingredient();
//                    a.setMeasure("CUP");
//                    a.setIngredientName("sal");
//                    a.setQuantity(2);
//                    ingredients.add(a);

                    Intent result = new Intent(targetContext, DetailRecipeActivity.class);
                    result.putParcelableArrayListExtra("INGREDIENTS", (ArrayList<Ingredient>) TestUtils.getRecipes().get(0).getIngredients());
                    Recipe recipe = new Recipe("TESTE");
                    result.putExtra("RECIPE", TestUtils.getRecipes().get(0));
                    return result;
                }
            };


    @Test
    public void teste() {

//        IngredientsListFragment fragment = new IngredientsListFragment();
//        myActivityTestRule2.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.ingredients_container, fragment).commit();
        TestUtils.delay(1000);
        onView(ViewMatchers.withId(R.id.ingredients_container))
                .check(matches(isEnabled()));
    }

}
