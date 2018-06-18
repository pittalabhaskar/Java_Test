

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import static junit.framework.TestCase.assertTrue;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"./src/"},
        format = {
                "pretty",
                "html:target/cucumber-reports/cucumber-html-report",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt",
                "junit:target/cucumber-junit-report/cucumbertestreport.xml"
        },
        plugin = { "html:target/cucumber-reports/cucumber-html-report",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "pretty:target/cucumber-reports/cucumber-pretty.txt",
                "usage:target/cucumber-reports/cucumber-usage.json",
                "junit:target/cucumber-reports/cucumber-results.xml" },
        glue = {"./src/"},
        tags = { "~@ignore" })
public class Runner     {

    @Test
    public void testMe() {
        assertTrue(true);
    }
}
