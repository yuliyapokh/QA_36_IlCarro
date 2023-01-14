package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{


    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();

        }

    }

    @Test
    public void loginSuccess(){
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm("pokh@i2.ua","Yyp12345!");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }
    @Test
    public void loginSuccessModel(){
        User user=new User().withEmail("pokh@i2.ua").withPassword("Yyp12345!");

        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }

    @Test
    public void loginWrongEmail(){
        User user=new User().withEmail("pokhi2.ua").withPassword("Yyp12345!");

        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().IsEmailOrPassOk());

    }
    @Test
    public void loginWrongPassword(){
        User user=new User().withEmail("pokh@i2.ua").withPassword("12345!");

        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");
    }
    @Test

    public void loginUnregisterUser(){
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm("vokh@i2.ua","Yyp12345!");
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("Login or Password incorrect"));


    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeDialogContainer();
    }
}