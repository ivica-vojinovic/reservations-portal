package net.ivica.reservations.web;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import net.ivica.reservations.AbstractTests;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class IndexTests extends AbstractTests {

    private WebApplicationContext _context;

    private MockMvc _mockMvc;

    private FilterChainProxy _filterChainProxy;

    private WebClient _webClient;

    @After
    public void cleanup() {
        getWebClient().close();
    }

    @Test
    public void failedLoginTest() throws Exception {
        HtmlPage indexPage = getWebClient().getPage("http://localhost:8080/index.html");

        HtmlAnchor loginModalLink = indexPage.getAnchorByName("login-modal-link");
        indexPage = loginModalLink.click();

        HtmlForm loginForm = indexPage.getHtmlElementById("login-form");
        waitForDisplayed(loginForm);

        HtmlInput usernameInput = loginForm.getInputByName("username");
        Assert.assertTrue(usernameInput.isDisplayed());
        usernameInput.setTextContent("ivica@gmail.com");

        HtmlInput passwordInput = loginForm.getInputByName("password");
        Assert.assertTrue(passwordInput.isDisplayed());
        passwordInput.setTextContent("password");

        HtmlButton loginButton = loginForm.getButtonByName("login-button");
        Assert.assertTrue(loginButton.isDisplayed());
        HtmlPage resultPage = loginButton.click();

        _webClient.waitForBackgroundJavaScript(2000);

        HtmlElement divErrorElement = resultPage.getHtmlElementById("login-error");
        Assert.assertEquals("Bad credentials", divErrorElement.getTextContent());
    }

    private WebClient getWebClient() {
        return _webClient;
    }

    @Test
    public void indexTest() throws Exception {
        HtmlPage indexPage = getWebClient().getPage("http://localhost:8080/index.html");

        HtmlElement productNameElement = indexPage.getHtmlElementById("p-name-1");
        Assert.assertEquals("Test product", productNameElement.getTextContent());
        HtmlElement productDescriptionElement = indexPage.getHtmlElementById("p-description-1");
        Assert.assertEquals("Test description", productDescriptionElement.getTextContent());
    }

    @Autowired
    public void setContext(WebApplicationContext context) {
        _context = context;
    }

    @Autowired
    public void setFilterChainProxy(FilterChainProxy filterChainProxy) {
        _filterChainProxy = filterChainProxy;
    }

    @Before
    public void setUp() throws Exception {
        _mockMvc = MockMvcBuilders
                .webAppContextSetup(_context).addFilters(_filterChainProxy)
                .build();

        _webClient = MockMvcWebClientBuilder.mockMvcSetup(_mockMvc).build();
    }

}
