package com.xs.java8;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Java8ApplicationTests {

    @Test
    void contextLoads() {
        ChromeDriver chromeDriver = new ChromeDriver();
        Actions actions=new Actions(chromeDriver);

        //打开网址
        chromeDriver.get("www.baidu.com");
        chromeDriver.get("https://www.baidu.com");
        // 3.获取输入框，输入selenium
        chromeDriver.findElement(By.id("kw")).sendKeys("selenium");
        // 4.获取“百度一下”按钮，进行搜索
        chromeDriver.findElement(By.id("su")).click();
//        actions.click(element).perform();
        // 5.退出浏览器
//        chromeDriver.quit();
    }

}
