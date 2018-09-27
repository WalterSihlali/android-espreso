package meniga.bank42.components.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import meniga.bank42.R;



import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class CommonUtils {

    private CommonUtils(){

    }

    public static ViewInteraction accessPfmEditTextByParentId(int id) {
        return onView(nthChildOf(nthChildOf(nthChildOf(nthChildOf(withId(id)
                , 0), 0), 0), 0));
    }

	public static ViewInteraction accessPfmViewTextByParentId(int id) {
		return onView(nthChildOf(nthChildOf(nthChildOf(withId(id)
				, 0), 0), 3));
	}
    public static void validateAllNavigationMenuItemsAreDisplayed(){
        waitFor(2000);
        onView(withId(R.id.navigation_my_activity))
                .check(matches(isDisplayed()));
        onView(withId(R.id.navigation_my_plan))
                .check(matches(isDisplayed()));
        onView(withId(R.id.navigation_my_activity))
                .check(matches(isDisplayed()));
        onView(withId(R.id.navigation_transact))
                .check(matches(isDisplayed()));
        onView(withId(R.id.navigation_manage))
                .check(matches(isDisplayed()));
    }


    public static Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("with " + childPosition + " child view of type parentMatcher");
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view.getParent() instanceof ViewGroup)) {
                    return parentMatcher.matches(view.getParent());
                }
                ViewGroup group = (ViewGroup) view.getParent();
                return parentMatcher.matches(view.getParent()) && group.getChildAt(childPosition).equals(view);
            }
        };
    }

    public static void waitFor(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

	public static String getText(final Matcher<View> matcher) {
		final String[] stringHolder = { null };
		onView(matcher).perform(new ViewAction() {
			@Override
			public Matcher<View> getConstraints() {
				return isAssignableFrom(TextView.class);
			}

			@Override
			public String getDescription() {
				return "getting text from a TextView";
			}

			@Override
			public void perform(UiController uiController, View view) {
				TextView tv = (TextView)view; //Save, because of check in getConstraints()
				stringHolder[0] = tv.getText().toString();
			}
		});
		return stringHolder[0];
	}

}
