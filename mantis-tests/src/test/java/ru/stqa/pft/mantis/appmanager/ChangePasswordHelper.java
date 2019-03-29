package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {
    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void login (String username, String password){
        wd.get(app.getProperty("web.baseUrl")+ "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void resetPassword(String username){
        wd.findElement(By.linkText("Manage Users")).click();
        type(By.name("username"), username);
        click(By.cssSelector("input[value='Manage User']"));
        click(By.cssSelector("input[value='Reset Password']"));
    }
}
