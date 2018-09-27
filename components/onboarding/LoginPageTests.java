package meniga.bank42.components.onboarding;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;

import meniga.bank42.components.constants.OnBoardingConstants;
import meniga.bank42.R;
import meniga.bank42.components.login.LoginActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static meniga.bank42.components.utils.CommonUtils.accessPfmEditTextByParentId;

public class LoginPageTests {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule =
            new ActivityTestRule<LoginActivity>(LoginActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry
                            .getInstrumentation()
                            .getTargetContext();
                    Intent result = new Intent(targetContext, LoginActivity.class);
                    return result;
                }
            };

    @Test
    public void testCorrectErrorMessageShownWhenLoginWithIncorrectDetails() {
        accessPfmEditTextByParentId(R.id.tlLoginUsername)
                .perform(clearText());
        accessPfmEditTextByParentId(R.id.tlLoginUsername)
                .perform(typeText("aaa"), closeSoftKeyboard());
        accessPfmEditTextByParentId(R.id.onboard_password)
                .perform(typeText("aaa"), closeSoftKeyboard());
        onView(withId(R.id.onboard_email_sign_in_button))
                .perform(click());
        onView(withText(OnBoardingConstants.INCORRECT_CREDENTIALS)).check(matches(isDisplayed()));
        onView(withId(R.id.md_buttonDefaultPositive))
                .perform(click());
        onView(withId(R.id.onboard_email_sign_in_button))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testInlineValidationOnLoginUsernameField() {
        onView(withId(R.id.onboard_email_sign_in_button))
                .check(matches(isDisplayed()));
        accessPfmEditTextByParentId(R.id.tlLoginUsername)
                .perform(clearText());
        onView(withId(R.id.onboard_email_sign_in_button))
                .perform(click());
        onView(withText(OnBoardingConstants.LOGIN_USERNAME_VALIDATION))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testInlineValidationOnLoginPasswordField() {
        accessPfmEditTextByParentId(R.id.tlLoginUsername)
                .perform(clearText());
        accessPfmEditTextByParentId(R.id.tlLoginUsername)
                .perform(typeText("aaa"), closeSoftKeyboard());
        onView(withId(R.id.onboard_email_sign_in_button))
                .perform(click());
        onView(withText(OnBoardingConstants.EMPTY_PASSWORD_VALIDATION))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testInlineValidationOnForgotPasswordView() {
        onView(withId(R.id.login_forgotPassword))
                .perform(click());
        onView(withText(OnBoardingConstants.FORGOT_PASSWORD_PAGE_DESCRIPTION))
                .check(matches(isDisplayed()));
        onView(withId(R.id.btn_forgot_password_submit))
                .check(matches(isDisplayed()));
        onView(withId(R.id.btn_forgot_password_submit))
                .perform(click());
        onView(withText(OnBoardingConstants.FORGOT_PASSWORD_VALIDATION))
                .check(matches(isDisplayed()));
    }

}