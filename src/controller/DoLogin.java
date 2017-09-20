package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CustomerService;

@WebServlet("/doLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DoLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String customerId = request.getParameter("customerId");

		// Perform business logic. Return a bean as a result		
		CustomerService service = new CustomerService();
		Customer customer = service.findCustomer(customerId);
		request.setAttribute("customer", customer);

		List<Customer> customerList = service.getAllCustomers();
		request.setAttribute("customerList", customerList);

		// Set Page
		String page;
		if (customer == null)
			page = "/view/error.jsp";
		else
			page = "/view/success.jsp";
		
		// Forward to Page
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
