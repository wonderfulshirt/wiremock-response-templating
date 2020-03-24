import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"json:target/json-report/cucumber.json"},
    glue = "com.wonderfulshirt.glue",
    features = "src/test/resources/features/",
    tags = "@demo")
public final class CucumberRunnerFT {}
