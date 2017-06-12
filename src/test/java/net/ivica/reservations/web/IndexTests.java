package net.ivica.reservations.web;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import net.ivica.reservations.AbstractTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

public class IndexTests extends AbstractTests {

    @Autowired
    private WebApplicationContext _context;

    private WebClient _webClient;

    @After
    public void cleanup() {
        getWebClient().close();
    }

    private WebClient getWebClient() {
        return _webClient;
    }

    @Test
    public void indexTest() throws Exception {
        HtmlPage indexPage = getWebClient().getPage("http://localhost:8080/index.html");

        System.out.println(indexPage.asXml());
    }

    @Before
    public void setUp() throws Exception {
        _webClient = MockMvcWebClientBuilder.webAppContextSetup(_context).build();
    }

}
