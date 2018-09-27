package meniga.bank42.components.transact;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import meniga.bank42.components.onboarding.LoginSuccessTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		LoginSuccessTest.class,
		InterAccountPageTest.class

})

public class TransactTestSuiteRunner {
}
