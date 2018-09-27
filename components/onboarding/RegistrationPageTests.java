package meniga.bank42.components.onboarding;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;

import meniga.bank42.components.constants.OnBoardingConstants;
import meniga.bank42.R;
import meniga.bank42.components.registration.RegistrationActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static meniga.bank42.components.utils.CommonUtils.accessPfmEditTextByParentId;
import static meniga.bank42.components.utils.CommonUtils.validateAllNavigationMenuItemsAreDisplayed;
import static meniga.bank42.components.utils.CommonUtils.waitFor;

public class RegistrationPageTests extends OnBoardingTestBase {

    @Rule
    public ActivityTestRule<RegistrationActivity> activityTestRule =
            new ActivityTestRule<RegistrationActivity>(RegistrationActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry
                            .getInstrumentation()
                            .getTargetContext();
                    Intent result = new Intent(targetContext, RegistrationActivity.class);
                    return result;
                }
            };

    @Test
    public void testInlineValidationOnRegistrationView() {
        onView(withText(OnBoardingConstants.REGISTER_PAGE_TITLE))
                .check(matches(isDisplayed()));
        onView(withId(R.id.client_id_number))
                .perform(click());
        onView(withId(R.id.fldCurrentPassword))
                .perform(click());
        onView(withId(R.id.fldNewPassword))
                .perform(click());
        onView(withId(R.id.text_input_password_toggle))
                .perform(click(),closeSoftKeyboard());
        onView(withId(R.id.checkbox_terms_and_conditions))
                .perform(click());
        waitFor(2000);
        onView(withId(R.id.button_change_password))
		        .perform(click());

        validateRegistrationErrors();
    }

    @Test
	public void testRegisteringExistingUsername() {
		onView(withText(OnBoardingConstants.REGISTER_PAGE_TITLE))
				.check(matches(isDisplayed()));
		accessPfmEditTextByParentId(R.id.client_id_number)
				.perform(typeText("user101@gmail.com"), closeSoftKeyboard());
		accessPfmEditTextByParentId(R.id.fldCurrentPassword)
				.perform(typeText("0917373773"), closeSoftKeyboard());
		accessPfmEditTextByParentId(R.id.fldNewPassword)
				.perform(typeText("Testing01"), closeSoftKeyboard());
		onView(withId(R.id.text_input_password_toggle))
				.perform(click(),closeSoftKeyboard());
		onView(withId(R.id.checkbox_terms_and_conditions))
				.perform(click());
		waitFor(2000);
		onView(withId(R.id.button_change_password))
				.perform(click());
		existingUserValidations();
	}

	@Test
	public void testSuccessfulRegistrationFlow() {
    	try {
		    enterDataOnRegistrationPageAndTapContinue();
		    enterDataOnOTPPageAndContinue();
		    acceptTermsAndConditions();
		    declineFingerPrintRegistration();
		    getStartedWelcomePage();
		    validateAllNavigationMenuItemsAreDisplayed();
	    } catch (Exception ex){
    		ex.getMessage();
	    }
	}
}
