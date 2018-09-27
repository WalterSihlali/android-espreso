package meniga.bank42.components.transact;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;

import meniga.bank42.R;
import meniga.bank42.components.transfers.amount.views.TransferDetailsActivity;
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
import static org.hamcrest.CoreMatchers.not;

public class InterAccountPageTest {
	@Rule
	public ActivityTestRule<TransferDetailsActivity> activityTestRule =
			new ActivityTestRule<TransferDetailsActivity>(TransferDetailsActivity.class) {

				@Override
				protected Intent getActivityIntent() {
					Context targetContext = InstrumentationRegistry
							.getInstrumentation()
							.getTargetContext();
					Intent result = new Intent(targetContext, TransferDetailsActivity.class);
					return result;
				}
			};

	@Test
	public void testNavigationToTransactMenu(){
		onView(withId(R.id.navigation_transact))
				.check(matches(isDisplayed()));
		onView(withText("Error loading page")).check(matches(not(isDisplayed())));
		onView(withText("TRANSFERS")).check(matches(isDisplayed()));
		onView(withText("From")).check(matches(isDisplayed()));
		onView(withText("To")).check(matches(isDisplayed()));
	}
	@Test
	public void testInsufficientBalance(){
		onView(withId(R.id.navigation_transact))
				.check(matches(isDisplayed()));
		accessPfmEditTextByParentId(R.id.fldTransferAmount)
				.perform(typeText("1000"), closeSoftKeyboard());
		onView(withId(R.id.btn_submit))
				.perform(click());
		waitFor(2000);
		onView(withText("Insufficient funds"))
				.check(matches(isDisplayed()));
	}

	@Test
	public void testValidationWhenNoAmountEntered(){
		onView(withText("TRANSFERS")).check(matches(isDisplayed()));
		onView(withId(R.id.btn_submit)).perform(click());
		onView(withText("The amount must be larger than 0")).check(matches(isDisplayed()));
	}

	@Test
	public void testSuccessfulAccountTransfer(){
		onView(withText("TRANSFERS")).check(matches(isDisplayed()));
		String fromAmount = accessPfmEditTextByParentId(R.id.fromAccountCard).toString();
		String toAmount = accessPfmEditTextByParentId(R.id.toAccountCard).toString();
		BigDecimal fromAmountNumber = getAmount(fromAmount);
		BigDecimal toAmountNumber = getAmount(toAmount);
	}

	private BigDecimal getAmount(String amount) {
		if (amount == null) {
			return BigDecimal.ZERO;
		} else {
			return new BigDecimal(stripCurrencyFromAmount(amount));
		}
	}

	private String stripCurrencyFromAmount(String amount) {
		return amount
				.replaceAll("\\s", "")
				.replaceAll("[A-Za-z]", "")
				.replaceAll(",", "\\.");
	}

}
