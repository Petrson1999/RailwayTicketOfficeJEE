package com.railvayticketiffice.web.command;

import com.google.gson.Gson;
import com.railvayticketiffice.entity.User;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.services.TicketService;
import com.railvayticketiffice.web.data.AjaxResponse;
import com.railvayticketiffice.web.data.Page;
import com.railvayticketiffice.web.form.request.OrderForm;
import com.railvayticketiffice.web.form.validator.OrderFormValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.railvayticketiffice.constant.PageUrlConstants.TICKETS_PAGE;

public class OrderCommand extends MultipleMethodCommand {
    private static final Logger LOG = Logger.getLogger(OrderCommand.class);
    private static Gson gson = new Gson();
    private TicketService ticketService;

    public OrderCommand(){
        this.ticketService = ServiceFactory.getTicketService();
    }


    @Override
    protected Page performGet(HttpServletRequest request) {
        return null;
    }

    @Override
    protected Page performPost(HttpServletRequest request) {

        OrderForm orderForm = getOrderForm(request);

        AjaxResponse ajaxResponse = new AjaxResponse();

        if (isOrderFormNotValid(orderForm)) {
            LOG.info("tickets order form is invalid");
            ajaxResponse.setSuccess(false);
            ajaxResponse.setUrl(TICKETS_PAGE);
            ajaxResponse.setMessage("tickets order form is invalid");
            return new Page(true ,  gson.toJson(ajaxResponse));
        }

        boolean success = ticketService.addTicket(orderForm);
        if (!success) {
            LOG.info("order unsuccessful");
            ajaxResponse.setSuccess(false);
            ajaxResponse.setUrl(TICKETS_PAGE);
            ajaxResponse.setMessage("order unsuccessful");
            return new Page(true ,  gson.toJson(ajaxResponse));
        }


        ajaxResponse.setSuccess(true);
        ajaxResponse.setMessage("order successful");
        return new Page(true ,  gson.toJson(ajaxResponse));

    }

    private OrderForm getOrderForm(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        return mapForm(request,
                req -> new OrderForm(
                        Integer.parseInt(request.getParameter("flightId")),
                        Integer.parseInt(request.getParameter("wagonId")),
                        Integer.parseInt(request.getParameter("seatId")),
                        user.getId()));
    }

    private boolean isOrderFormNotValid(OrderForm orderForm){
        return !validateForm(orderForm, new OrderFormValidator());
    }


}
