package educanet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter fw;
        fw = new FileWriter("output.txt", true);


        WebDriver driver = new FirefoxDriver();

        driver.get("https://praha.educanet.cz/teachers/pedagogicky-sbor/");

        for(WebElement e : driver.findElements(By.className("post-box-bellow"))) {

            WebElement nameDiv = e.findElement(By.cssSelector(".post-box-title-inner"));
            String name = nameDiv.findElement(By.tagName("a")).getText();
            System.out.println(name);
            fw.write("Name: " + name + "\n");

            if(!e.findElements(By.cssSelector(".contact-mail")).isEmpty()){
                WebElement emailDiv = e.findElement(By.cssSelector(".contact-mail"));
                String email = emailDiv.findElement(By.tagName("a")).getText();
                System.out.println(email);
                fw.write("Email: " + email + "\n");
            }else{
                System.out.println("No email");
                fw.write("No Email" + "\n");
            }

            System.out.println();
            fw.write("\n");
        }
        fw.flush();
        fw.close();

        driver.quit();
    }
}