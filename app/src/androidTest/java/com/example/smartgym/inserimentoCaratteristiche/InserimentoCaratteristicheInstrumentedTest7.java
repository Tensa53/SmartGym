package com.example.smartgym.inserimentoCaratteristiche;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.smartgym.R;
import com.example.smartgym.infoUtenti.application.activity.InserimentoModificaCaratteristicheActivity;
import com.example.smartgym.start.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class InserimentoCaratteristicheInstrumentedTest7 {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenario = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void inserimentoCaratteristicheTest() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(ViewMatchers.withId(R.id.profileFragment)).perform(click());

        onView(withId(R.id.btModificaCaratteristiche)).perform(click());

        onView(withId(R.id.etPeso)).perform(clearText());
        onView(ViewMatchers.withId(R.id.etPeso)).perform(typeText("80"));
        onView(withId(R.id.etAltezza)).perform(clearText());
        onView(withId(R.id.etAltezza)).perform(typeText("190"));
        onView(withId(R.id.spinnerEsperienza)).perform(click());
        onView(withText("Principiante")).perform(click());
        onView(withId(R.id.etAllenamenti)).perform(clearText());
        onView(withId(R.id.etAllenamenti)).perform(typeText("5"));

        onView(withId(R.id.btUpdate)).perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
