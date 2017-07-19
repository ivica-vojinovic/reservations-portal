package net.ivica.reservations.web.controller;

import net.ivica.reservations.api.Product;
import net.ivica.reservations.api.UserProfile;
import net.ivica.reservations.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Controller
public class IndexController {

    private ProductService _productService;

    private ProductService getProductService() {
        return _productService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        _productService = productService;
    }

    @RequestMapping("/index.html")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAnonymous = (authentication == null || authentication instanceof AnonymousAuthenticationToken);

        if (!isAnonymous) {
            UserProfile authUser = (UserProfile) authentication.getPrincipal();

            model.addAttribute("authUser", authUser);
        }


        List<Product> productList = getProductService().findAll();

        List<List<Product>> modelList = new LinkedList<>();
        List<Product> modelRowList = new LinkedList<>();
        int counter = 0;
        for (Product product : productList) {
            modelRowList.add(product);
            counter++;

            if (counter >= 2) {
                counter = 0;

                modelList.add(modelRowList);
                modelRowList = new LinkedList<>();
            }
        }

        model.addAttribute("allProducts", modelList);

        return "index";
    }

    @RequestMapping("/")
    public String root(Locale locale) {
        return "redirect:/index.html";
    }

}
