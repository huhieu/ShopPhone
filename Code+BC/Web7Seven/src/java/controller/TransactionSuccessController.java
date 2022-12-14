package controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardnewService;
import service.OrderedService;
import service.ProductService;
import service.TransactionService;
import service.impl.BoardnewServicesImpl;
import service.impl.OrderedServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.TransactionServicesImpl;

public class TransactionSuccessController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    TransactionService transactionService = new TransactionServicesImpl();
    OrderedService orderedService = new OrderedServiceImpl();
    ProductService productService = new ProductServiceImpl();
    BoardnewService boardnewService = new BoardnewServicesImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/checkoutsuccess.jsp");
        dispatcher.forward(req, resp);
    }
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
        super.doPost(request, response);
    }
}
