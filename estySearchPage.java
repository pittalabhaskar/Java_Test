import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class estySearchPage extends BrowserFactory {
    private BrowserFactory browserFactory;

    public estySearchPage(BrowserFactory browserFactory) {
        this.browserFactory = browserFactory;
        PageFactory.initElements(browserFactory.getDriver(), this);
    }

    @FindBy(how = How.ID, using = "search-query")
    @CacheLookup
    public WebElement txtSearch;

    @FindBy(how = How.XPATH, using = "//*[@id='gnav-search']/div/div[2]/button")
    @CacheLookup
    public WebElement btnSearch;

    @FindBy(how = How.XPATH, using = "//*[@id='sortby']/div/button")
    @CacheLookup
    public WebElement bntSortBy;

    @FindBy(how = How.XPATH, using = "//*[@id='sortby']/div/div/div/ul/li[4]")
    @CacheLookup
    public WebElement bntSortByOption;


  public void PrintTopTen()
  {
      List<WebElement> elementTypes =  browserFactory.getDriver().findElements(By.xpath("//*[@id='content']//*[contains(@class,'currency-value')]"));

      int loopsize ;
      if(elementTypes.size() > 10)
          loopsize = 10;
      else
          loopsize = elementTypes.size();

          for (int i =0; i<loopsize;i++)
              System.out.println("Price of Item : " + i + " is : " +  elementTypes.get(i).getText());


  }
}
