package com.example.smartgym;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.CoreMatchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.smartgym.gestioneScheda.application.activity.ScegliParteDelCorpoActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreazioneSchedaInstrumentedTest1 {

    @Rule public ActivityScenarioRule<ScegliParteDelCorpoActivity> activityScenario = new ActivityScenarioRule<ScegliParteDelCorpoActivity>(ScegliParteDelCorpoActivity.class);


    @Test
    public void creazioneSchedaTest() {

        onView(withId(R.id.bttutto)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.eserciziLV)).onChildView(withId(R.id.ll1)).onChildView(withId(R.id.esercizioCheckBox)).atPosition(0).perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onData(anything()).inAdapterView(withId(R.id.eserciziLV)).onChildView(withId(R.id.ll1)).onChildView(withId(R.id.esercizioCheckBox)).atPosition(1).perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.confermaButton)).perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
