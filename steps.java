
        import java.util.Properties;
        import java.io.*;

        import cucumber.api.PendingException;
        import cucumber.api.java.en.*;


public class steps extends BrowserFactory {
    private BrowserFactory browserFactory;
    private estySearchPage objSearch;
    private Properties prop = new Properties();
    private InputStream input = null;
    private static String currentPath = System.getProperty("user.dir");

    public steps() {
    }

    public steps(BrowserFactory browserFactory) {
        this.browserFactory = browserFactory;
        objSearch = new estySearchPage(this.browserFactory);

    }


    @Given("I am a Proteus User")
    public void GivenIAmAProteusUser() {
        System.out.println("I am a Proteus User step");
    }

    @Given("^I go to the etsy$")
    public void iGoToTheEtsyCom() throws Throwable {
        BrowserFactory.initBrowser("etsy.com");
    }

    @When("^I Search for \"([^\"]*)\"$")
    public void iSearchFor(String strSearchKey) throws Exception  {
        objSearch.txtSearch.sendKeys(strSearchKey);
        objSearch.btnSearch.click();
        Thread.sleep(100);
    }

    @Then("^Page loaded with Sports shoes$")
    public void pageLoadedWithSportsShoes() throws Throwable {
       System.out.println("Page loaded with Sports shoes");
    }

    @When("^I select sort by price$")
    public void iSelectSortByPrice() throws Throwable {
        objSearch.bntSortBy.click();
        Thread.sleep(100);
        objSearch.bntSortByOption.click();
        Thread.sleep(100);
    }

    @Then("^Page sorted by price$")
    public void pageSortedByPrice() throws Throwable {
        System.out.println("Page sorted by price");
    }

    @And("^Top (\\d+) Prices printed$")
    public void topPricesPrinted(int arg0) throws Throwable {
       objSearch.PrintTopTen();
    }
}
