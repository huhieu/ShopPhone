package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Catalog;
import model.Product;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServicesImpl;
import service.impl.ProductServiceImpl;

public class ProductListOfClassifyClientController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServicesImpl();
    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String indexPage = req.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);

        List<Catalog> cateList = cateService.getAll();
        req.setAttribute("catelist", cateList);
        String id = req.getParameter("classify");
        List<Product> product_paging;
        int sumProducts;
        switch (Integer.parseInt(id)) {
            case 1:
                product_paging = productService.pagingProductsNew(index);
                sumProducts = productService.getTotalProductNew();
                break;
            case 3:
                product_paging = productService.pagingProductsSale(index);
                sumProducts = productService.getTotalProductSale();
                break;
            case 2 :
                product_paging = productService.pagingProductsSelling(index);
                sumProducts = productService.getTotalProductSelling();
                break;
            default :
                product_paging = null;
                sumProducts = 0;
        }
        //Gi?? gi???m
        List<Product> productsList1 = new ArrayList<Product>();
        for (Product product : product_paging) {
            Product product1 = productService.get(Integer.parseInt(product.getId()));
            String y = product1.getPrice().replace(".", "");
            int price = Integer.parseInt(y);  // Gi?? c?? s???n ph???m
            int percent = Integer.parseInt(product1.getDiscount());
            int a = 100;
            double balance = (double) percent / 100;
            double percentBalance = 1 - balance;
            product1.setPrice(String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(price * percentBalance)));
            productsList1.add(product1);

        }
        req.setAttribute("productlist", product_paging);
        req.setAttribute("productlist1", productsList1);
        
        // Ph??n trang t??? ?????ng
        int maxPage = sumProducts / 12;
        if (sumProducts % 12 != 0) {
            maxPage++;
        }
        int endPage;
        if (maxPage > 7) {
            endPage = 7;
        } else {
            endPage = maxPage;
        }
        int beginPage = 1;
        int midPage = (endPage + beginPage) / 2;
        int sumPage = 0;
        if (index > midPage) {
            sumPage = index - midPage;
        }
        int check = endPage + sumPage;
        if (check <= maxPage) {
            beginPage = beginPage + sumPage;
            endPage = endPage + sumPage;
        } else {
            beginPage = maxPage - (endPage - 1);
            endPage = maxPage;
        }
        req.setAttribute("beginP", beginPage);
        req.setAttribute("endP", endPage);
        req.setAttribute("maxP", endPage);
        req.setAttribute("tag", index);
        req.setAttribute("classifyID", id);
        // Product b??n ch???y
        List<Product> product_banchay = productService.getProductById(6);
        req.setAttribute("product_banchay", product_banchay);
        req.setAttribute("id_banner", id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/product-classify.jsp");
        dispatcher.forward(req, resp);
    }

}
