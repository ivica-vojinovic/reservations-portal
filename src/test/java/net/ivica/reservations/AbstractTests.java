package net.ivica.reservations;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("tests")
public abstract class AbstractTests {

    protected void dumpHtmlPage(String dumpPath, HtmlPage page) {
        try {
            Files.write(Paths.get(dumpPath), page.asXml().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void waitForDisplayed(HtmlElement htmlElement) {
        int retryTimes = 10;

        int time = 0;
        while (time < retryTimes) {
            if (htmlElement.isDisplayed()) {
                break;
            }
        }

        if (!htmlElement.isDisplayed()) {
            throw new RuntimeException(String.format("HtmlElement %s did not get dipslayed in reasonable amount of time.", htmlElement.getNodeName()));
        }
    }

}
