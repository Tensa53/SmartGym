package com.example.smartgym.login;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.smartgym.R;
import com.example.smartgym.infoUtenti.application.activity.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginInstrumentedTest3 {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenario = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);

    @Test
    public void loginTest() {

        onView(ViewMatchers.withId(R.id.etEmail)).perform(typeText("giuseppeverdi@mail.it"));
        onView(withId(R.id.etPassword)).perform(typeText("OkayMyPass1@"));
        onView(withId(R.id.btLogin)).perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

