package EmCio;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions( plugin = {"json:target/cucumber-report/cucumber.json"})
public class RunCucumberTest {
}
