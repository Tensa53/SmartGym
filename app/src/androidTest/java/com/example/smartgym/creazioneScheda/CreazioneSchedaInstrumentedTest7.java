package com.example.smartgym.creazioneScheda;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.CoreMatchers.anything;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.smartgym.R;
import com.example.smartgym.gestioneScheda.application.activity.ScegliParteDelCorpoActivity;
import com.example.smartgym.start.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreazioneSchedaInstrumentedTest7 {

    @Rule public ActivityScenarioRule<MainActivity> activityScenario = new ActivityScenarioRule<MainActivity>(MainActivity.class);


    @Test
    public void creazioneSchedaTest() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.exercisesFragment)).perform(click());

        onView(withId(R.id.bt1)).perform(click());

        onView(withId(R.id.btmanuale)).perform(click());

        onView(ViewMatchers.withId(R.id.bttutto)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.eserciziLV)).onChildView(withId(R.id.ll1)).onChildView(withId(R.id.esercizioCheckBox)).atPosition(0).perform(click());

        for (int i = 0; i < 25; i++) {
            onData(anything()).inAdapterView(withId(R.id.eserciziLV)).onChildView(withId(R.id.ll1)).onChildView(withId(R.id.aumentaButton)).atPosition(0).perform(click());
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onData(anything()).inAdapterView(withId(R.id.eserciziLV)).onChildView(withId(R.id.ll1)).onChildView(withId(R.id.esercizioCheckBox)).atPosition(1).perform(click());

        for (int i = 0; i < 8; i++) {
            onData(anything()).inAdapterView(withId(R.id.eserciziLV)).onChildView(withId(R.id.ll1)).onChildView(withId(R.id.aumentaButton)).atPosition(1).perform(click());
        }


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onData(anything()).inAdapterView(withId(R.id.eserciziLV)).onChildView(withId(R.id.ll1)).onChildView(withId(R.id.esercizioCheckBox)).atPosition(2).perform(click());

        for (int i = 0; i < 12; i++) {
            onData(anything()).inAdapterView(withId(R.id.eserciziLV)).onChildView(withId(R.id.ll1)).onChildView(withId(R.id.aumentaButton)).atPosition(2).perform(click());
        }


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onData(anything()).inAdapterView(withId(R.id.eserciziLV)).onChildView(withId(R.id.ll1)).onChildView(withId(R.id.esercizioCheckBox)).atPosition(3).perform(click());

        for (int i = 0; i < 5; i++) {
            onData(anything()).inAdapterView(withId(R.id.eserciziLV)).onChildView(withId(R.id.ll1)).onChildView(withId(R.id.aumentaButton)).atPosition(3).perform(click());
        }


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onData(anything()).inAdapterView(withId(R.id.eserciziLV)).onChildView(withId(R.id.ll1)).onChildView(withId(R.id.esercizioCheckBox)).atPosition(4).perform(click());

        for (int i = 0; i < 20; i++) {
            onData(anything()).inAdapterView(withId(R.id.eserciziLV)).onChildView(withId(R.id.ll1)).onChildView(withId(R.id.aumentaButton)).atPosition(4).perform(click());
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.confermaButton)).perform(click());

        onView(withId(R.id.etNomeScheda)).perform(typeText("SchedaEserciziTest"))
                        .perform(closeSoftKeyboard());

        onView(withId(R.id.confermaButton)).perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
