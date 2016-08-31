package androidskeleton.activities;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidskeleton.utils.ConditionalIgnoreRule;
import androidskeleton.utils.ConditionalIgnoreRule.ConditionalIgnore;
import apps.androidskeleton.BuildConfig;
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
    @Rule
    public ConditionalIgnoreRule ignoreRule = new ConditionalIgnoreRule();

    @Before
    public void setUp() throws Exception {
        openDebugDrawer();
    }

    @Test
    @ConditionalIgnore(condition = NotInDebug.class)
    public void testDebugDrawerExists() {
        onView(withText(R.string.development_settings)).check(matches(isDisplayed()));
    }

    @Test
    @ConditionalIgnore(condition = NotInDebug.class)
    public void testBuildSectionSetup() throws Exception {
        onView(withId(R.id.debug_build_name)).check(matches(withText(BuildConfig.VERSION_NAME)));
        onView(withId(R.id.debug_build_code)).check(matches(withText(Integer.toString(BuildConfig.VERSION_CODE))));
//        onView(withId(R.id.debug_build_sha)).check(matches(withText(BuildConfig.GIT_SHA)));
    }

    @Test
    @ConditionalIgnore(condition = NotInDebug.class)
    public void testDeviceInfoAvailable() throws Exception {
        onView(withText("Device Information")).check(matches(isDisplayed()));
    }

    @Test
    @ConditionalIgnore(condition = NotInDebug.class)
    public void testScalpelInfoAvailable() throws Exception {
        onView(withId(R.id.debug_ui_scalpel)).check(matches(isDisplayed()));
        onView(withId(R.id.debug_ui_scalpel_wireframe)).check(matches(isDisplayed()));
    }

    private void openDebugDrawer() {
        onView(withId(R.id.debug_activity_layout)).perform(DrawerActions.open(Gravity.END));
    }

    public class NotInDebug implements ConditionalIgnoreRule.IgnoreCondition {
        public boolean isSatisfied() {
            return !BuildConfig.DEBUG;
        }
    }

}
