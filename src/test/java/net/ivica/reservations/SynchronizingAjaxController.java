package net.ivica.reservations;

import com.gargoylesoftware.htmlunit.AjaxController;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class SynchronizingAjaxController extends AjaxController {

    @Override
    public boolean processSynchron(final HtmlPage page, final WebRequest settings, final boolean async) {
        return true;
    }

}
