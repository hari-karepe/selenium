package browser;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LaunchFirefox {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (System.getProperty("os.name").contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", ".//src/test/resources/drivers/geckodriver.exe");
		} else if (System.getProperty("os.name").contains("Mac")) {

			String home = System.getProperty("user.home");
			File f = new File(home + "/Downloads/geckodriver");

			if (f.exists()) {
				System.out.println("gecko driver Exists");
				System.setProperty("webdriver.gecko.driver", home + "/Downloads/geckodriver");
			} else {
				System.err.println("gecko driver does not Exists in folder /Downloads/");
				System.exit(0);
			}

		} else {
			System.err.println("os is not compatible");
			System.exit(0);
		}

		WebDriver drv = new FirefoxDriver();

		drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		drv.manage().window().maximize();

		drv.get("https://www.seleniumhq.org/");

		String pageTitle = drv.getTitle();

		System.out.println("pageTitle: " + pageTitle);

		// initial configuration
		System.out.println(
				"Browser Name \t\t: " + ((RemoteWebDriver) drv).getCapabilities().getBrowserName().toLowerCase());
		System.out.println("Browser Version \t: " + ((RemoteWebDriver) drv).getCapabilities().getVersion().toString());
		System.out.println("Platform Name \t\t: " + ((RemoteWebDriver) drv).getCapabilities().getPlatform().toString());

		drv.close(); // closes active window/tab
		drv.quit(); // closes all windows/tabs

	}

}
