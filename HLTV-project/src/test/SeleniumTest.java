package test;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeleniumTest {

    private static WebDriver webDriver;
    private static String baseUrl;

    @org.junit.jupiter.api.BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Muhamed\\Downloads\\chromedriver_win32\\chromedriver.exe");
        webDriver = new ChromeDriver();
        baseUrl = "https://www.hltv.org/";
        webDriver.manage().window().maximize();
    }

    @org.junit.jupiter.api.AfterAll
    static void tearDown() {
        webDriver.close();
    }

    @Test
    void testLogIn() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement signIn = webDriver.findElement(By.xpath("/html/body/div[1]/nav/div[9]"));
        signIn.click();
        WebElement username = webDriver.findElement(By.xpath("//*[@id=\"loginpopup\"]/form/input[1]"));
        WebElement password = webDriver.findElement(By.xpath("//*[@id=\"loginpopup\"]/form/input[2]"));
        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"loginpopup\"]/form/button"));
        username.sendKeys("abc@gmail.com");
        password.sendKeys("your_password");
        login.click();
        Thread.sleep(5000);
    }

    @Test
    void testSearchBar() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement searchBar = webDriver.findElement(By.xpath("/html/body/div[1]/nav/div[5]/form/span/input"));
        searchBar.click();
        searchBar.sendKeys("s1mple");
        Thread.sleep(2000);
        searchBar.sendKeys(Keys.ENTER);
        WebElement playerProfile = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/div[2]/table[1]/tbody/tr[2]/td/a"));
        playerProfile.click();
        Thread.sleep(2000);
        WebElement playerRealName = webDriver.findElement(By.cssSelector("body > div.bgPadding > div > div.colCon > div.contentCol" +
                " > div.playerProfile > div.playerContainer > div.playerName > div.playerRealname"));
        assertEquals("Aleksandr Kostyliev", playerRealName.getText());

    }

    @Test
    void testNavbarLinks() {
        webDriver.get(baseUrl);

        String[] expectedLinks = {
                "https://www.hltv.org/",
                "https://www.hltv.org/",
                baseUrl + "matches",
                baseUrl + "results",
                baseUrl + "events",
                baseUrl + "stats",
                baseUrl + "galleries",
                baseUrl + "ranking/teams",
                baseUrl + "forums",
                baseUrl + "betting/analytics",
                baseUrl + "live",
                baseUrl + "fantasy",
                baseUrl + "signup"
        };

        WebElement navbar = webDriver.findElement(By.xpath("/html/body/div[1]"));
        List<WebElement> links = navbar.findElements(By.tagName("a"));

        for(int i = 0; i < links.size(); i++){
           assertEquals(expectedLinks[i], links.get(i).getAttribute("href"));
        }
    }

    @Test
    void testSocialLinks() {
        webDriver.get(baseUrl);

        String[] expectedSocialLinks = {
                "https://www.facebook.com/HLTV.org",
                "https://www.twitch.tv/hltvorg",
                "https://www.youtube.com/user/wwwHLTVorg",
                "https://www.twitter.com/HLTVorg",
                "https://vk.com/hltvorg",
                "https://www.instagram.com/hltvorg/",

        };

        WebElement socialLinksNavbar = webDriver.findElement(By.cssSelector("body > footer > div.widthControl.footerlinks > span:nth-child(8)"));
        List<WebElement> socialLinks = socialLinksNavbar.findElements(By.tagName("a"));

        for(int i = 0; i < socialLinks.size(); i++){
            assertEquals(expectedSocialLinks[i], socialLinks.get(i).getAttribute("href"));
        }


    }

    @Test
    void testBestPlayer() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement stats = webDriver.findElement(By.xpath("/html/body/div[1]/nav/a[6]"));
        stats.click();
        WebElement bestPlayers = webDriver.findElement(By.cssSelector("body > div.bgPadding > div > div.colCon > div.contentCol > " +
                "div.stats-section > div.stats-quick-navigation-container > a:nth-child(1)"));
        bestPlayers.click();


        Select timeFilter = new Select(webDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/div[2]/div[1]/div/div/div[2]/form/select")));
        timeFilter.selectByVisibleText("2017");
        Thread.sleep(2000);

        WebElement bestPlayer = webDriver.findElement(By.cssSelector("body > div.bgPadding > div > div.colCon > div.contentCol >" +
                " div.stats-section > table > tbody > tr:nth-child(1) > td.playerCol > a"));
        assertEquals("NiKo", bestPlayer.getText());
        Thread.sleep(2000);
    }

    @Test
    void testTeamRankings() {
        webDriver.get("https://www.hltv.org/ranking/teams");

        WebElement topTeam = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/div[2]/div[1]/div[3]/div/div[1]/div[1]/div[1]/span[1]"));
        assertEquals("Natus Vincere", topTeam.getText());

        WebElement regionalRanking = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/div[2]/div[1]/div[1]/span"));
        regionalRanking.click();

        WebElement rankingEU = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/div[2]/div/div[1]/div/a[3]"));
        rankingEU.click();

        WebElement topEuTeam = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/div[2]/div/div[3]/div/div[1]/div[1]/div[1]/span[1]"));
        assertEquals("G2", topEuTeam.getText());

    }

    @Test
    void applyForJob() throws InterruptedException {
        webDriver.get(baseUrl);

        WebElement contact = webDriver.findElement(By.xpath("/html/body/footer/div[2]/span[2]/a"));
        contact.click();
        Thread.sleep(1000);
        WebElement applyForJob = webDriver.findElement(By.cssSelector("body > div.bgPadding > div > div.colCon > div.contentCol > " +
                "div.contact > div > div.g-grid.contact-nav > div:nth-child(5) > a"));
        applyForJob.click();
        Thread.sleep(1000);
        WebElement applyForAdmin = webDriver.findElement(By.xpath("//*[@id=\"applyForJobSection\"]/div/div/div[3]/div/a"));
        applyForAdmin.click();
        Thread.sleep(1000);
        String URL = webDriver.getCurrentUrl();
        assertEquals("https://www.hltv.org/contact#tab-applyForJobSection", URL);
    }

    @Test
    void testSignUp() throws InterruptedException {
        webDriver.get(baseUrl);

        WebElement signIn = webDriver.findElement(By.xpath("/html/body/div[1]/nav/div[9]"));
        signIn.click();
        WebElement signUp = webDriver.findElement(By.xpath("//*[@id=\"loginpopup\"]/a"));
        signUp.click();
        WebElement username = webDriver.findElement(By.xpath("//*[@id=\"usernameInput\"]"));
        WebElement password = webDriver.findElement(By.xpath("//*[@id=\"passwordInput\"]"));
        WebElement email = webDriver.findElement(By.xpath("//*[@id=\"emailInput\"]"));
        WebElement confirmPassword = webDriver.findElement(By.xpath("//*[@id=\"confirmPasswordInput\"]"));
        WebElement confirmEmail = webDriver.findElement(By.xpath("//*[@id=\"confirmEmailInput\"]"));

        username.sendKeys("abc123");
        password.sendKeys("password123");
        email.sendKeys("your-emailemail.com");
        confirmPassword.sendKeys("password123");
        confirmEmail.sendKeys("your-emailemail.com");

        Thread.sleep(2000);

        Select selectFlag = new Select(webDriver.findElement(By.cssSelector("body > div.bgPadding > div > div.colCon > div.contentCol >" +
                " div.signup.standard-box > form > div > div:nth-child(8) > div > div > select")));
        selectFlag.selectByVisibleText("Bosnia and Herzegovina");
        Thread.sleep(2000);

        WebElement theme = webDriver.findElement(By.cssSelector("body > div.bgPadding > div > div.colCon > div.contentCol > div.signup.standard-box > form > div > div:nth-child(11) > div > div:nth-child(2) > label > img"));
        theme.click();
        Thread.sleep(2000);

        WebElement createAccount = webDriver.findElement(By.xpath("//*[@id=\"signup-validate-button\"]"));
        createAccount.click();

        Thread.sleep(2000);

    }

    @Test
    void testStats() throws InterruptedException {
        webDriver.get(baseUrl);

        WebElement stats = webDriver.findElement(By.xpath("/html/body/div[1]/nav/a[6]"));
        stats.click();

        Select matchFilter = new Select(webDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/div[2]/div[1]/div/div/div[1]/form/select")));
        matchFilter.selectByVisibleText("Majors");
        Thread.sleep(2000);

        Select timeFilter = new Select(webDriver.findElement(By.cssSelector("body > div.bgPadding > div > div.colCon > div.contentCol > " +
                "div.stats-section > div.stats-top-menu > div > div > div:nth-child(2) > form > select")));
        timeFilter.selectByVisibleText("2015");
        Thread.sleep(2000);

        WebElement bestPlayer = webDriver.findElement(By.cssSelector("body > div.bgPadding > div > div.colCon > div.contentCol > " +
                "div.stats-section > div.columns > div:nth-child(1) > div:nth-child(2) > a"));
        assertEquals("flusha", bestPlayer.getText());
        Thread.sleep(2000);

        WebElement bestTeam = webDriver.findElement(By.cssSelector("body > div.bgPadding > div > div.colCon > div.contentCol > " +
                "div.stats-section > div.columns > div:nth-child(2) > div:nth-child(2) > a"));
        assertEquals("fnatic", bestTeam.getText());
        Thread.sleep(2000);



    }

    @Test
    void test(){

    }


}
