package meniga.bank42.components.onboarding;

import java.util.Random;

import meniga.bank42.components.constants.OnBoardingConstants;
import meniga.bank42.R;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static meniga.bank42.components.utils.CommonUtils.accessPfmEditTextByParentId;
import static meniga.bank42.components.utils.CommonUtils.waitFor;
import static org.hamcrest.CoreMatchers.not;

public class OnBoardingTestBase {

	static final private Random r = new Random(System.currentTimeMillis());

	public void validateRegistrationErrors(){
		onView(withText(OnBoardingConstants.REGISTER_EMAIL_VALIDATION))
				.check(matches(isDisplayed()));
		onView(withText(OnBoardingConstants.REGISTER_MOBILE_VALIDATION))
				.check(matches(isDisplayed()));
		onView(withText(OnBoardingConstants.REGISTER_PASSWORD_VALIDATION))
				.check(matches(isDisplayed()));
	}

	public void existingUserValidations() {
		onView(withText(OnBoardingConstants.REGISTER_ERROR_DIALOG_TITLE))
				.check(matches(isDisplayed()));
		onView(withText(OnBoardingConstants.REGISTER_EXISTING_USER_ERROR))
				.check(matches(isDisplayed()));
		onView(withId(R.id.md_buttonDefaultPositive))
				.perform(click());
		onView(withText(OnBoardingConstants.REGISTER_PAGE_TITLE))
				.check(matches(isDisplayed()));
	}

	public void enterDataOnRegistrationPageAndTapContinue(){
		onView(withText(OnBoardingConstants.REGISTER_PAGE_TITLE))
				.check(matches(isDisplayed()));
		accessPfmEditTextByParentId(R.id.client_id_number)
				.perform(typeText("user" + r.nextInt() + "@gmail.com"), closeSoftKeyboard());
		accessPfmEditTextByParentId(R.id.fldCurrentPassword)
				.perform(typeText("0917373772"), closeSoftKeyboard());
		accessPfmEditTextByParentId(R.id.fldNewPassword)
				.perform(typeText("Testing01"), closeSoftKeyboard());
		onView(withId(R.id.text_input_password_toggle))
				.perform(click(),closeSoftKeyboard());
		onView(withId(R.id.checkbox_terms_and_conditions))
				.perform(click());
		waitFor(2000);
		onView(withId(R.id.button_change_password))
				.perform(click());
	}

	public void enterDataOnOTPPageAndContinue(){
		waitFor(2000);
		onView(withText(OnBoardingConstants.OTP_PAGE_DESCRIPTION))
				.check(matches(isDisplayed()));
		accessPfmEditTextByParentId(R.id.edtOtp)
				.perform(typeText("123456"), closeSoftKeyboard());
		onView(withId(R.id.btn_otp_continue))
				.perform(click());
	}

	public void resendOTP(){
		waitFor(2000);
		onView(withText(OnBoardingConstants.OTP_PAGE_DESCRIPTION))
				.check(matches(isDisplayed()));
		onView(withId(R.id.btn_otp_resend))
				.perform(click());
		onView(withText(OnBoardingConstants.OTP_SENT))
				.check(matches(isDisplayed()));
		onView(withText(OnBoardingConstants.OTP_SENT_TO_MOBILE))
				.check(matches(isDisplayed()));
		onView(withId(R.id.md_buttonDefaultPositive))
				.perform(click());
		onView(withText(OnBoardingConstants.OTP_PAGE_DESCRIPTION))
				.check(matches(isDisplayed()));
	}

	public void acceptTermsAndConditions() {
		waitFor(2000);
		onView(withText(OnBoardingConstants.TERMS_AND_CONDITIONS))
				.check(matches(isDisplayed()));
		onView(withId(R.id.accept_button))
				.perform(click());
	}

	private void declineTermsAndConditions() {
		waitFor(2000);
		onView(withText(OnBoardingConstants.TERMS_AND_CONDITIONS))
				.check(matches(isDisplayed()));
		onView(withId(R.id.decline_button))
				.perform(click());
	}

	public void declineFingerPrintRegistration(){
		waitFor(4000);
		onView(withText(OnBoardingConstants.FINGERPRINT_DIALOG_ERROR_TITLE))
				.check(matches(isDisplayed()));
		onView(withText(OnBoardingConstants.FINGERPRINT_DIALOG_ERROR_DESCRIPTION))
				.check(matches(isDisplayed()));
		onView(withId(R.id.md_buttonDefaultNegative))
				.perform(click());
	}

	public void getStartedWelcomePage(){
		waitFor(2000);
		onView(withText(OnBoardingConstants.HELLO_THERE))
				.check(matches(isDisplayed()));
		onView(withId(R.id.welcome_button_next))
				.perform(click());
		onView(withText(OnBoardingConstants.FEED_TITLE))
				.check(matches(isDisplayed()));

		onView(withText(OnBoardingConstants.NO_TRANSACTIONS))
				.check(matches(isDisplayed()));
	}

}
