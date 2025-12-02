package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void loginExitoso() {
        LoginPage login = new LoginPage(driver);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        login.esperarLoginVisible();
        login.escribirEmail("ale@test.com");
        login.escribirPassword("12345678");
        login.clickIngresar();
    }
}
