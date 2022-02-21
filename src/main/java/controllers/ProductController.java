package controllers;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IProductService;

import java.util.ArrayList;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ModelAndView showProducts() {
        ModelAndView modelAndView = new ModelAndView("list");
        ArrayList<Product> products = productService.getAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteProduct(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("list");
        Product product = productService.getProduct(id);
        if (product != null) {
            productService.deleteProduct(product);
        } else {
            modelAndView.addObject("mess", "id không hợp lệ");
        }

        ArrayList<Product> products = productService.getAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/{id}/view")
    public ModelAndView showDetail(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Product product = productService.getProduct(id);
        if (product != null) {
            modelAndView.addObject("product", product);
        } else {
            modelAndView.addObject("mess", "id không hợp lệ");
        }
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createProduct() {
        ModelAndView modelAndView = new ModelAndView("create");
        Product product = new Product();
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute Product product) {
        ModelAndView modelAndView = new ModelAndView("list");
        boolean check = false;
        ArrayList<Product> products = productService.getAll();
        for (Product product1 : products) {
            if (product1.getId() == product.getId()) {
                productService.updateProduct(product, product.getId());
                check = true;
            }
        }

        if (!check) {
            productService.createProduct(product);
        }
//        products = productService.getAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView createProduct(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("create");
        Product product = productService.getProduct(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }
}
