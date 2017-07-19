package net.ivica.reservations.web;

import com.gargoylesoftware.htmlunit.WebClient;
import net.ivica.reservations.AbstractTests;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class AbstractHtmlTests extends AbstractTests {

    private WebApplicationContext _context;

    private MockMvc _mockMvc;

    private FilterChainProxy _filterChainProxy;

    private WebClient _webClient;

    @After
    public void cleanup() {
        getWebClient().close();
    }

    protected WebClient getWebClient() {
        return _webClient;
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

    protected void waitForBackgroundJavaScript() {
        _webClient.waitForBackgroundJavaScript(2000);
    }

}
