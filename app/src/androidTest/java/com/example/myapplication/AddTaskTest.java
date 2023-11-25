package com.example.myapplication;


import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.example.myapplication.activity.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddTaskTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void addTaskTest() {
        ViewInteraction materialButton = onView(
allOf(withId(R.id.addTaskbtn), withText("Add Task"),
childAtPosition(
allOf(withId(R.id.home),
childAtPosition(
withId(android.R.id.content),
0)),
3),
isDisplayed()));
        materialButton.perform(click());
        
        ViewInteraction appCompatEditText = onView(
allOf(withId(R.id.taskTitle),
childAtPosition(
allOf(withId(R.id.constraintLayout),
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
0)),
1),
isDisplayed()));
        appCompatEditText.perform(replaceText("g"), closeSoftKeyboard());
        
        ViewInteraction appCompatEditText2 = onView(
allOf(withId(R.id.taskDescription),
childAtPosition(
allOf(withId(R.id.constraintLayout),
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
0)),
2),
isDisplayed()));
        appCompatEditText2.perform(replaceText("g"), closeSoftKeyboard());
        
        ViewInteraction appCompatEditText3 = onView(
allOf(withId(R.id.taskDescription), withText("g"),
childAtPosition(
allOf(withId(R.id.constraintLayout),
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
0)),
2),
isDisplayed()));
        appCompatEditText3.perform(pressImeActionButton());
        
        ViewInteraction materialButton2 = onView(
allOf(withId(R.id.addTaskbtn), withText("Add Task"),
childAtPosition(
allOf(withId(R.id.constraintLayout),
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
0)),
3),
isDisplayed()));
        materialButton2.perform(click());
        
        ViewInteraction materialButton3 = onView(
allOf(withId(R.id.backbtn2), withText("Back"),
childAtPosition(
allOf(withId(R.id.constraintLayout),
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
0)),
6),
isDisplayed()));
        materialButton3.perform(click());
        }
    
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
