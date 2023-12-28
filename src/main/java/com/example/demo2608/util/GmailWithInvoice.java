package com.example.demo2608.util;

import com.example.demo2608.model.customer.Customer;
import com.example.demo2608.model.dto.Cart;
import com.example.demo2608.model.dto.ServiceDTO;
import com.example.demo2608.model.reservation.booking.Booking;
import com.example.demo2608.model.reservation.booking.Discount;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class GmailWithInvoice {

    public String getContentByString(Booking booking, List<Cart> carts, List<ServiceDTO> services, Customer customer){

    Double discount=booking.getDiscount()!=null?booking.getDiscount().getAmount():0;


        StringBuilder content=new StringBuilder();
        content.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns:th=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "<style>\n" +
                "      table {\n" +
                "          border-collapse: collapse;\n" +
                "          width: 100%;\n" +
                "         margin-top: 2px;\n" +
                "      }\n" +
                "      th, td {\n" +
                "          border: 1px solid #000;\n" +
                "          padding: 8px;\n" +
                "          text-align: center;\n" +
                "      }\n" +
                "  </style>" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"card\">\n" +
                "    <div class=\"card-body\">\n" +
                "        <div class=\"container mb-5 mt-3\">\n" +
                "            <div class=\"row d-flex align-items-baseline\">\n" +
                "                <div class=\"col-xl-9\">");
//        get id of booking
        content.append("<p style=\"color: #7e8d9f;font-size: 20px;\">Your booking >> <strong >ID: # ")
                .append(booking.getId()).append("</strong></p>");
        content.append("</div>\n" +
                "            </div>\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-xl-8\">\n" +
                "                    <ul class=\"list-unstyled\">");

//        get booking:
        content.append("<li class=\"text-muted\" >Name: ")
                .append(customer.getName()).append("</li>");
        content.append("<li class=\"text-muted\" >Check_in: ")
                .append(booking.getCheck_in()).append("</li>");
        content.append("<li class=\"text-muted\" >Check_out: ")
                .append(booking.getCheck_out()).append("</li>");
        content.append("<li class=\"text-muted\" >Date: ")
                .append(booking.getDate()).append("</li>");
        content.append("<li class=\"text-muted\" >Description: ")
                .append(booking.getDescription()).append("</li>");

        content.append("</ul>\n" +
                "                </div>\n" +
                "                <div class=\"row my-2 mx-1 justify-content-center\">\n" +
                "                    <table class=\"table table-striped table-borderless\">\n" +
                "                        <tr style=\"background-color: orangered;\">\n" +
                "                            <th>No</th>\n" +
                "                            <th>Name</th>\n" +
                "                            <th>Price</th>\n" +
                "                            <th>Quantity</th>\n" +
                "                            <th>Total</th>\n" +
                "                        </tr>");
//        get roomType:
        for (int i = 0; i < carts.size(); i++) {
            Cart cart = carts.get(i);
            content.append("<tr>");
            content.append("<td>").append(i + 1).append("</td>");
            content.append("<td>").append(cart.getRoomType().getName()).append("</td>");
            content.append("<td>").append(cart.getRoomType().getPrice()).append("</td>");
            content.append("<td>").append(cart.getQuantity()).append("</td>");
            content.append("<td>").append(cart.getQuantity()*cart.getRoomType().getPrice()).append("</td>");
            content.append("</tr>");
        }
        content.append("</ul>\n" +
                "                </div>\n" +
                "                <div class=\"row my-2 mx-1 justify-content-center\">\n" +
                "                    <table class=\"table table-striped table-borderless\">\n" +
                "                        <tr style=\"background-color: orangered;\">\n" +
                "                            <th>No</th>\n" +
                "                            <th>Name</th>\n" +
                "                            <th>Price</th>\n" +
                "                            <th>Quantity</th>\n" +
                "                            <th>Total</th>\n" +
                "                        </tr>");
//      get services:
        for (int i = 0; i < services.size(); i++) {
            ServiceDTO serviceDTO = services.get(i);
            content.append("<tr>");
            content.append("<td>").append(i + 1).append("</td>");
            content.append("<td>").append(serviceDTO.getService().getName()).append("</td>");
            content.append("<td>").append(serviceDTO.getService().getPrice()).append("</td>");
            content.append("<td>").append(serviceDTO.getQuantity()).append("</td>");
            content.append("<td>").append(serviceDTO.getQuantity()*serviceDTO.getService().getPrice()).append("</td>");
            content.append("</tr>");
        }

        content.append(" </table>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <div class=\"col-xl-8\">\n" +
                "                        <p class=\"ms-3\">Add additional notes and payment information</p>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-xl-2\">");
        content.append("<p class=\"text-muted\" >Discount: ")
                .append(discount)
                .append("%</p>");
        content.append("<p class=\"text-muted\" style=\"font-size: 25px;\" >Total amount: ")
                .append(booking.getAmount())
                .append("$</p>");
        content.append("</div>\n" +
                "                </div>\n" +
                "                <hr>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "<section\n" +
                "        class=\"d-flex justify-content-between p-4\"\n" +
                "        style=\"background-color: #6351ce\">\n" +
                "    <div class=\"me-5\">\n" +
                "        <span>Get connected with us on social networks:</span>\n" +
                "    </div>\n" +
                "    <div>\n" +
                "        <a href=\"https://www.facebook.com/furamaresort/\" class=\"text-white me-4\" style=\"text-decoration: none\">\n" +
                "            <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\"\n" +
                "                 class=\"bi bi-facebook\" viewBox=\"0 0 16 16\">\n" +
                "                <path d=\"M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951z\"/>\n" +
                "            </svg>\n" +
                "        </a>\n" +
                "        <a href=\"https://www.instagram.com/furama_resort_and_villas/\" class=\"text-white me-4\"\n" +
                "           style=\"text-decoration: none\">\n" +
                "            <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\"\n" +
                "                 class=\"bi bi-instagram\" viewBox=\"0 0 16 16\">\n" +
                "                <path d=\"M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z\"/>\n" +
                "            </svg>\n" +
                "        </a>\n" +
                "        <a href=\"https://www.youtube.com/user/furamaresortvietnam/featured\" class=\"text-white me-4\"\n" +
                "           style=\"text-decoration: none\">\n" +
                "            <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\"\n" +
                "                 class=\"bi bi-youtube\" viewBox=\"0 0 16 16\">\n" +
                "                <path d=\"M8.051 1.999h.089c.822.003 4.987.033 6.11.335a2.01 2.01 0 0 1 1.415 1.42c.101.38.172.883.22 1.402l.01.104.022.26.008.104c.065.914.073 1.77.074 1.957v.075c-.001.194-.01 1.108-.082 2.06l-.008.105-.009.104c-.05.572-.124 1.14-.235 1.558a2.007 2.007 0 0 1-1.415 1.42c-1.16.312-5.569.334-6.18.335h-.142c-.309 0-1.587-.006-2.927-.052l-.17-.006-.087-.004-.171-.007-.171-.007c-1.11-.049-2.167-.128-2.654-.26a2.007 2.007 0 0 1-1.415-1.419c-.111-.417-.185-.986-.235-1.558L.09 9.82l-.008-.104A31.4 31.4 0 0 1 0 7.68v-.123c.002-.215.01-.958.064-1.778l.007-.103.003-.052.008-.104.022-.26.01-.104c.048-.519.119-1.023.22-1.402a2.007 2.007 0 0 1 1.415-1.42c.487-.13 1.544-.21 2.654-.26l.17-.007.172-.006.086-.003.171-.007A99.788 99.788 0 0 1 7.858 2h.193zM6.4 5.209v4.818l4.157-2.408L6.4 5.209z\"/>\n" +
                "            </svg>\n" +
                "        </a>\n" +
                "        <a href=\"https://www.linkedin.com/company/furama-hotels-international/\" class=\"text-white me-4\"\n" +
                "           style=\"text-decoration: none\">\n" +
                "            <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\"\n" +
                "                 class=\"bi bi-linkedin\" viewBox=\"0 0 16 16\">\n" +
                "                <path d=\"M0 1.146C0 .513.526 0 1.175 0h13.65C15.474 0 16 .513 16 1.146v13.708c0 .633-.526 1.146-1.175 1.146H1.175C.526 16 0 15.487 0 14.854V1.146zm4.943 12.248V6.169H2.542v7.225h2.401zm-1.2-8.212c.837 0 1.358-.554 1.358-1.248-.015-.709-.52-1.248-1.342-1.248-.822 0-1.359.54-1.359 1.248 0 .694.521 1.248 1.327 1.248h.016zm4.908 8.212V9.359c0-.216.016-.432.08-.586.173-.431.568-.878 1.232-.878.869 0 1.216.662 1.216 1.634v3.865h2.401V9.25c0-2.22-1.184-3.252-2.764-3.252-1.274 0-1.845.7-2.165 1.193v.025h-.016a5.54 5.54 0 0 1 .016-.025V6.169h-2.4c.03.678 0 7.225 0 7.225h2.4z\"/>\n" +
                "            </svg>\n" +
                "        </a>\n" +
                "    </div>\n" +
                "</section>\n" +
                "</body>\n" +
                "</html>");

        return content.toString();
    }

    public String getPaymentReceipt(Booking booking, Customer customer){
        StringBuilder content=new StringBuilder();
        content.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <style>\n" +
                "        table {\n" +
                "              border-collapse: collapse;\n" +
                "                width: 80%;\n" +
                "                 margin-top: 2px;\n" +
                "             }\n" +
                "             th {\n" +
                "                width: 30%;\n" +
                "\n" +
                "             }\n" +
                "              th, td {\n" +
                "                  border: 1px solid #000;\n" +
                "                 padding: 8px;\n" +
                "                 text-align: left;\n" +
                "          }\n" +
                "          </style>\n" +
                "    <title>Payment Receipt</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h2>Payment Receipt</h2>\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "            <th>Trans, Date, Time</th>");
        content.append("<td>")
                .append(LocalDateTime.now()).append("</td>\n" +
                "        </tr>\n" +
                "        <tr>");
        content.append("<th>Order Number</th>\n" +
                "            <td>")
                .append(booking.getId())
                .append("</td>\n" +
                        "        </tr>\n" +
                        "        <tr>");
        content.append("<th>Credit Account</th>\n" +
                "            <td>")
                .append(customer.getAccountBanking().getNumberCard())
                .append("</td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <th>Debit Account</th>\n" +
                        "            <td>99999999999</td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <th>Beneficiary Name</th>\n" +
                        "            <td>");
        content.append(customer.getName())
                .append("</td>\n" +
        "        </tr>\n" +
                "        <tr>\n" +
                "            <th>Amount</th>\n" +
                "            <td>");
        content.append(booking.getAmount()*0.8)
                .append("</td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <th>Details of Payment</th>\n" +
                        "            <td>Furama hoan tra tien booking</td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "</body>\n" +
                        "</html>");
        return content.toString();

    }
}
