package meniga.bank42.components.onboarding;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;

import meniga.bank42.components.constants.OnBoardingConstants;
import meniga.bank42.R;
import meniga.bank42.components.landingpage.LandingPageActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static meniga.bank42.components.utils.CommonUtils.waitFor;

public class LandingPageTests {

    @Rule
    public ActivityTestRule<LandingPageActivity> activityTestRule =
            new ActivityTestRule<LandingPageActivity>(LandingPageActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry
                            .getInstrumentation()
                            .getTargetContext();
                    Intent result = new Intent(targetContext, LandingPageActivity.class);
                    return result;
                }
            };

    @Test
    public void testSwipeLeftAndRightOnLandingPage(){
        onView(withText(OnBoardingConstants.LANDING_PAGE1_TITLE))
                .check(matches(isDisplayed()));
        onView(withText(OnBoardingConstants.LANDING_PAGE1_DESCRIPTION))
                .check(matches(isDisplayed()));
        onView(withText(OnBoardingConstants.LANDING_PAGE1_TITLE))
                .perform(swipeLeft());
        onView(withText(OnBoardingConstants.LANDING_PAGE2_TITLE))
                .check(matches(isDisplayed()));
        onView(withText(OnBoardingConstants.LANDING_PAGE2_DESCRIPTION))
                .check(matches(isDisplayed()));
        waitFor(2000);
        onView(withText(OnBoardingConstants.LANDING_PAGE2_TITLE))
                .perform(swipeLeft());

        swipeRightOnLandingPage();
    }

    @Test
    public void testNavigationToLoginPageFromLandingPage() {
        onView(withText(OnBoardingConstants.LANDING_PAGE1_TITLE))
                .check(matches(isDisplayed()));
        onView(withId(R.id.text_logon))
                .perform(click());
        onView(withId(R.id.onboard_email_sign_in_button))
                .check(matches(isDisplayed()));
        onView(withId(R.id.onboard_email_sign_in_button))
                .perform(pressBack());
        onView(withText(OnBoardingConstants.LANDING_PAGE1_TITLE))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testNavigationToRegistrationPageFromLandingPage() {
        onView(withText(OnBoardingConstants.LANDING_PAGE1_TITLE))
                .check(matches(isDisplayed()));
        onView(withId(R.id.btn_getstarted))
                .perform(click());
        onView(withText(OnBoardingConstants.REGISTER_PAGE_TITLE))
                .check(matches(isDisplayed()));
        onView(withId(R.id.client_id_number))
                .perform(pressBack());
        onView(withText(OnBoardingConstants.LANDING_PAGE1_TITLE))
                .check(matches(isDisplayed()));
    }


    private void swipeRightOnLandingPage() {
        onView(withText(OnBoardingConstants.LANDING_PAGE3_TITLE))
                .check(matches(isDisplayed()));
        onView(withText(OnBoardingConstants.LANDING_PAGE3_DESCRIPTION))
                .check(matches(isDisplayed()));
        waitFor(2000);
        onView(withText(OnBoardingConstants.LANDING_PAGE3_TITLE))
                .perform(swipeRight());
        onView(withText(OnBoardingConstants.LANDING_PAGE2_TITLE))
                .check(matches(isDisplayed()));
        waitFor(2000);
        onView(withText(OnBoardingConstants.LANDING_PAGE2_TITLE))
                .perform(swipeRight());
        onView(withText(OnBoardingConstants.LANDING_PAGE1_TITLE))
                .check(matches(isDisplayed()));
    }

}
