package androidskeleton.activities;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import apps.androidskeleton.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DebugAppContainerTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    @Ignore
    public void testDebugDrawerExists() {
        onView(withId(R.id.debug_activity_layout)).perform(DrawerActions.open(Gravity.END));

        onView(withText(R.string.development_settings)).check(matches(isDisplayed()));
    }
}
