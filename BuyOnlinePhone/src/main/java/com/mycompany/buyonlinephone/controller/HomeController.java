/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.controller;

import com.google.gson.Gson;
import com.mycompany.buyonlinephone.entities.AccountEntity;
import com.mycompany.buyonlinephone.entities.AccountRoleEntity;
import com.mycompany.buyonlinephone.entities.CodeVoucherEntity;
import com.mycompany.buyonlinephone.entities.CreditCardEntity;
import com.mycompany.buyonlinephone.entities.OrdersDetailEntity;
import com.mycompany.buyonlinephone.entities.OrdersEntity;
import com.mycompany.buyonlinephone.entities.PaymentEntity;
import com.mycompany.buyonlinephone.entities.ProductEntity;
import com.mycompany.buyonlinephone.entities.PromotionEntity;
import com.mycompany.buyonlinephone.entities.VoteAndCommentEntity;
import com.mycompany.buyonlinephone.enums.CommonStatus;
import com.mycompany.buyonlinephone.enums.Gender;
import com.mycompany.buyonlinephone.enums.OrderStatus;
import com.mycompany.buyonlinephone.enums.PaymentStatus;
import com.mycompany.buyonlinephone.enums.ProductStatus;
import com.mycompany.buyonlinephone.enums.PromotionStatus;
import com.mycompany.buyonlinephone.service.AccountRoleService;
import com.mycompany.buyonlinephone.service.AccountService;
import com.mycompany.buyonlinephone.service.CategoryService;
import com.mycompany.buyonlinephone.service.CodeVoucherService;
import com.mycompany.buyonlinephone.service.CreditCardService;
import com.mycompany.buyonlinephone.service.OrderService;
import com.mycompany.buyonlinephone.service.OrdersDetailService;
import com.mycompany.buyonlinephone.service.PaymentService;
import com.mycompany.buyonlinephone.service.ProductService;
import com.mycompany.buyonlinephone.service.PromotionService;
import com.mycompany.buyonlinephone.service.VoteAndCommentService;
import com.mycompany.buyonlinephone.utils.SecurityUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Admin
 */
@Controller
public class HomeController {

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private OrdersDetailService ordersDetailService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AccountRoleService accountRoleService;

    @Autowired
    private VoteAndCommentService voteAndCommentService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    CodeVoucherService codeVoucherService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String welcomePage(Model model, HttpSession session) {
        session.setAttribute("status", PromotionStatus.ACTIVE);
        Set<ProductEntity> product = productService.findByNewProductInThirTyDay();
        model.addAttribute("newproduct", product);
        model.addAttribute("topselling", ordersDetailService.getTopSelling(PageRequest.of(0, 10)));
        List<String> roles = SecurityUtils.getRolesOfUser();
        if (!CollectionUtils.isEmpty(roles) && (roles.contains("ROLE_ADMIN"))) {
            return "redirect:/admin/home";
        }
        if (!CollectionUtils.isEmpty(roles) && (roles.contains("ROLE_USER"))) {
            return "redirect:/user/home";
        }

        return "/home2";
    }

    @RequestMapping("/login")
    public String loginPage(HttpServletRequest request, Model model, @RequestParam(value = "error", required = false) boolean error) {
        if (error) {
            model.addAttribute("message", "fail ");
        }
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("url_prior_login", referrer);
        return "login";
    }

    @RequestMapping("/403")
    public String accessDenied(Model model) {
        return "403Page";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("account", new AccountEntity());
        model.addAttribute("MALE", Gender.MALE);
        model.addAttribute("FEMALE", Gender.FEMALE);
        return "register";
    }
//    @RequestMapping(value = "/newAccount", method = RequestMethod.POST)
//    public String newAccount(Model model, @ModelAttribute("account") AccountEntity account,
//            @RequestParam("repassword") String password) {
//        if (!password.equals(account.getPassword())) {
//            model.addAttribute("error", "password not true");
//            return "register";
//        } else {
//            if (accountService.getAccountInfo(account.getEmail()) != null) {
//                model.addAttribute("error", "email already");
//                return "register";
//            } else {
//                account.setPassword(accountService.encrytePassword(account.getPassword()));
//                AccountRoleEntity accountRole = new AccountRoleEntity();
//                Set<AccountRoleEntity> accountRoleEntitys = new HashSet<>();
//                accountRoleEntitys.add(accountRole);
//                account.setAccountRoleEntities(accountRoleEntitys);
//                accountService.save(account);
//                Set<AccountEntity> accountEntitys = new HashSet<>();
//                accountRole.setAccountEntities(accountEntitys);
//                accountRoleService.save(accountRole);
//                model.addAttribute("arlet", account.getFullName());
//                return "arlet";
//            }
//        }
//    }
    
    @RequestMapping(value = "/test-json")
    @ResponseBody
    public String testJson () {
        
        Gson gson = new Gson();
        ProductEntity product = productService.findProductById(3);
        return gson.toJson(String.valueOf(product));
    }
    @RequestMapping(value = "/test")
    public String test () {
        return "test-json";
    }
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String product(Model model) {
        Set<ProductEntity> products = productService.getProducts();
        model.addAttribute("products", products);
        model.addAttribute("quantity", productService.getCountProduct());
        model.addAttribute("category", categoryService.getCategory());
        return "product";
    }

    @RequestMapping(value = "/category/{category}")
    public String category(Model model, @PathVariable("category") String category) {
        Set<ProductEntity> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);

        return "category";
    }

    @RequestMapping(value = "/searchPrice/{category}/{min}/{max}")
    public String sortPrice(Model model, @PathVariable("category") String category,
            @PathVariable("min") double min,
            @PathVariable("max") double max) {
        if (category.equals("all")) {
            Set<ProductEntity> products = productService.getProductsByPrices(min, max);
            model.addAttribute("products", products);
        } else {
            Set<ProductEntity> products = productService.getProductsByPrice(category, min, max);
            model.addAttribute("products", products);
        }

        return "category";
    }


    @RequestMapping(value = "/productdetail/{id}", method = RequestMethod.GET)
    public String productDetail(Model model, @PathVariable("id") int id
    ) {
        ProductEntity product = productService.findProductById(id);
        Set<ProductEntity> listProduct = productService.getProductByNumberGroup(id);
        Set<ProductEntity> a = productService.getProductInGroupAndMemory(listProduct, product);
        model.addAttribute("memory", productService.getMemorys(listProduct));
        model.addAttribute("color", productService.getProductInGroupAndMemory(listProduct, product));
        model.addAttribute("listProduct", productService.getNumberGroupProduct(id));
        model.addAttribute("product", product);
        return "productdetail";
    }


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Model model, @RequestParam("search") String search) {
        List<ProductEntity> product = productService.searchName(search);
        model.addAttribute("products", product);
        model.addAttribute("quantity", productService.getCountProduct());
        model.addAttribute("category", categoryService.getCategory());
        return "product";
    }

    @RequestMapping("/order")
    public String order() {
        return "addcart";
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public String order(HttpSession session,
            Model model,
            @RequestParam("id") int id,
            @RequestParam(value = "quantity", defaultValue = "1") int quantity
    ) {
        OrdersEntity orders = (OrdersEntity) session.getAttribute("orders");
        ProductEntity product = productService.findProductById(id);
        if (orders != null) {
            for (OrdersDetailEntity orderDetail : orders.getOrdersDetails()) {
                if (orderDetail.getProduct().getId() == product.getId()) {
                    product.setQuantity(product.getQuantity() - quantity);
                    productService.save(product);
                    orderDetail.setQuantity(orderDetail.getQuantity() + quantity);
                    orderDetail.setAmount(product.getSalePrice() * orderDetail.getQuantity());
                    session.setAttribute("orders", orders);
                    return "redirect:/order";
                }
            }
            OrdersDetailEntity detail = new OrdersDetailEntity();
            detail.setProduct(product);
            detail.setAmount(product.getSalePrice() * quantity);
            detail.setQuantity(quantity);
            detail.setOrders(orders);
            Set<OrdersDetailEntity> ordersDetails = orders.getOrdersDetails();
            ordersDetails.add(detail);
            orders.setOrdersDetails(ordersDetails);
        } else {
            orders = new OrdersEntity();
            OrdersDetailEntity detail = new OrdersDetailEntity();
            detail.setProduct(product);
            detail.setAmount(product.getSalePrice() * quantity);
            detail.setQuantity(quantity);
            detail.setOrders(orders);
            Set<OrdersDetailEntity> ordersDetails = new HashSet<>();
            ordersDetails.add(detail);
            orders.setOrdersDetails(ordersDetails);
        }
        product.setQuantity(product.getQuantity() - quantity);
        productService.save(product);

        session.setAttribute("orders", orders);
        return "redirect:/order";

    }


    @RequestMapping(value = "/removeOrdersDetail/{id}")
    public String removeOrdersDetail(Model model, HttpSession session,
            @PathVariable("id") int id
    ) {
        OrdersEntity orders = (OrdersEntity) session.getAttribute("orders");
        List<OrdersDetailEntity> toRemove = new ArrayList<>();
        if (orders != null) {
            if (CollectionUtils.isEmpty(orders.getOrdersDetails())) {
                return "redirect:/order";
            }
            if (!CollectionUtils.isEmpty(orders.getOrdersDetails())) {
                for (OrdersDetailEntity detail : orders.getOrdersDetails()) {
                    if (detail.getProduct().getId() == id) {
                        ProductEntity product = productService.findProductById(id);
                        product.setQuantity(product.getQuantity() + detail.getQuantity());
                        productService.save(product);
                        toRemove.add(detail);
                    }
                }
                orders.getOrdersDetails().removeAll(toRemove);
            }
            session.setAttribute("orders", orders);
        } else {
            session.removeAttribute("orders");
        }
        return "redirect:/order";
    }

    @RequestMapping("/customerInfo")
    public String customerInfo(Model model, HttpSession session) {
        List<String> roles = SecurityUtils.getRolesOfUser();
        if (!CollectionUtils.isEmpty(roles) && (roles.contains("ROLE_USER"))) {
            model.addAttribute("orderInfo", accountService.getAccountInfo(accountService.getUserName()));
        } else {
            model.addAttribute("orderInfo", new OrdersEntity());
        }
        return "customerInfo";
    }

    @RequestMapping(value = "/credit", method = RequestMethod.POST)
    public String paymentInfo(Model model,
            @Valid @ModelAttribute("orderInfo") OrdersEntity ordersInfo, BindingResult result,
            HttpSession session) {
        if (result.hasErrors()) {
            return "customerInfo";
        }
        OrdersEntity orders = (OrdersEntity) session.getAttribute("orders");
        orders.setEmail(ordersInfo.getEmail());
        orders.setFullName(ordersInfo.getFullName());
        orders.setBirthDate(ordersInfo.getBirthDate());
        orders.setGender(ordersInfo.getGender());
        orders.setAddress(ordersInfo.getAddress());
        orders.setNumberPhone(ordersInfo.getNumberPhone());
        orders.setNote(ordersInfo.getNote());
        session.setAttribute("orders", orders);
        return "redirect:/creditCard";
    }

    @RequestMapping(value = "/creditCard")
    public String creditCard(Model model) {
        model.addAttribute("creditCard", new CreditCardEntity());
        return "creditCard";
    }

    @RequestMapping(value = "/checkCreditCard", method = RequestMethod.POST)
    public String checkCreditCard(Model model, @RequestParam(value = "cardNumber") int cardNumber,
            @RequestParam(value = "cvcCode") String cvcCode) {
        CreditCardEntity creditCard = creditCardService
                .findByCreditCardByCardNumberAndCvcCode(cardNumber, cvcCode);
        if (creditCard == null) {
            if (creditCardService.findByCardNumber(cardNumber) == null) {
                model.addAttribute("cardNumberError", "Card Number is not true");
            }
            if (creditCardService.findByCvcCode(cvcCode) == null) {
                model.addAttribute("cvcCodeError", "cvcCode is not true");
            }
            return "creditCard";
        }
        if (!creditCard.getStatus().equals(CommonStatus.ACTIVE)) {
            model.addAttribute("statusError", "Expired credit card. "
                    + "Please enter another credit card or return to the product page");
            return "creditCard";
        }
        model.addAttribute("creditCard", creditCard);
        return "payment";
    }

    @RequestMapping(value = "/pay/{id}")
    @ResponseBody
    public String pay(@PathVariable("id") int id, HttpSession session) throws MessagingException {
        OrdersEntity orders = (OrdersEntity) session.getAttribute("orders");
        CodeVoucherEntity codeVoucher = (CodeVoucherEntity) session.getAttribute("codeVoucher");
        if (orders == null) {
            return "addcart";
        }
        AccountEntity account = accountService.getAccountInfo(accountService.getUserName());
        Gson gson = new Gson();
        CreditCardEntity creditCard = creditCardService.findById(id);
        if (creditCard.getBalance() < orders.getTotalPrice()) {
            return gson.toJson(String.valueOf(""));
        } else {
//            deduction
            creditCard.setBalance(creditCard.getBalance() - orders.getTotalPrice());
//            add new object payment
            PaymentEntity payment = new PaymentEntity();
            payment.setAmount(orders.getTotalPrice() - orders.getDiscount() * orders.getTotalPrice() / 100);
            payment.setCreateDate(new Date());
            payment.setCreditCard(creditCard);
            payment.setOrder(orders);
            payment.setStatus(PaymentStatus.PAID);
            orders.setPayment(payment);
            orders.setCreateDate(new Date());
            creditCardService.save(creditCard);
            if (codeVoucher != null) {
                codeVoucherService.save(codeVoucher);
            }

            if (account != null) {
                Set<OrdersEntity> entitys = new HashSet<>();
                entitys.add(orders);
                account.setOrders(entitys);
                orders.setAccount(account);
                orderService.save(orders);
                accountService.save(account);
                paymentService.save(payment);
                ordersDetailService.save(orders.getOrdersDetails());
            } else {
                orderService.save(orders);
                paymentService.save(payment);
                ordersDetailService.save(orders.getOrdersDetails());
            }

  
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            String htmlMsg
                    = "<!DOCTYPE html>"
                    + "<html>\n"
                    + "    <head>\n"
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                    + "        <jsp:include page=\"include/css-page.jsp\" />\n"
                    + "        <title>bill</title>\n"
                    + "    </head>\n"
                    + "    <body>"
                    + "<div class=\"container\">           \n"
                    + "            <div class=\"row\">\n"
                    + "                <div class=\"col-md-12 col-lg-12\">                \n"
                    + "                    <h1 class=\"colorTitle\">Bill info</h1>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "\n"
                    + "            <div class=\"container\">\n"
                    + "                <div class=\"row\">\n"
                    + "                    <div class=\"col-md-12 col-lg-12\">\n"
                    + "                        <div class=\"table-responsive\">\n"
                    + "                            <table style=\"border: 2px solid #269abc\" class=\"table table-bordered table-hover\">\n"
                    + "                                <tr>\n"
                    + "                                    <th colspan=\"3\">\n"
                    + "                                        Code\n"
                    + "                                    </th>\n"
                    + "\n"
                    + "                                    <td colspan=\"6\">\n"
                    + orders.getCode()
                    + "                                    </td>\n"
                    + "                                </tr>\n"
                    + "                                <tr>\n"
                    + "                                    <th colspan=\"3\">\n"
                    + "                                        Name\n"
                    + "                                    </th>\n"
                    + "\n"
                    + "                                    <td colspan=\"6\">\n"
                    + orders.getFullName()
                    + "                                    </td>\n"
                    + "                                </tr>\n"
                    + "\n"
                    + "                                <tr>\n"
                    + "                                    <th colspan=\"3\">\n"
                    + "                                        Phone number\n"
                    + "                                    </th>\n"
                    + "\n"
                    + "                                    <td colspan=\"6\">\n"
                    + orders.getNumberPhone()
                    + "                                    </td>\n"
                    + "                                </tr>\n"
                    + "\n"
                    + "                                <tr>\n"
                    + "                                    <th colspan=\"3\">\n"
                    + "                                        Address\n"
                    + "                                    </th>\n"
                    + "\n"
                    + "                                    <td colspan=\"6\">\n"
                    + orders.getAddress()
                    + "                                    </td>\n"
                    + "                                </tr>\n"
                    + "\n"
                    + "                                <tr>\n"
                    + "                                    <th colspan=\"3\">\n"
                    + "                                        Gender\n"
                    + "                                    </th>\n"
                    + "\n"
                    + "                                    <td colspan=\"6\">\n"
                    + orders.getGender()
                    + "                                    </td>\n"
                    + "                                </tr>\n"
                    + "                                <tr>\n"
                    + "                                    <th colspan=\"3\">\n"
                    + "                                        birthDate\n"
                    + "                                    </th>\n"
                    + "\n"
                    + "                                    <td colspan=\"6\">\n"
                    + orders.getBirthDate()
                    + "                                    </td>\n"
                    + "                                </tr>\n"
                    + "                                <tr>\n"
                    + "                                    <th colspan=\"3\">\n"
                    + "                                        Email\n"
                    + "                                    </th>\n"
                    + "\n"
                    + "                                    <td colspan=\"6\">\n"
                    + orders.getEmail()
                    + "                                    </td>\n"
                    + "                                </tr>\n"
                    + "                                <tr>\n"
                    + "<th style=\"color: #269abc\" colspan=\"9\" class=\"centerTh\">\n"
                    + "                                        Orders Info\n"
                    + "                                    </th>"
                    + "\n"
                    + "                                    \n"
                    + "                                </tr>\n"
                    + "\n"
                    + "                                <tr>\n"
                    + "                                    <th colspan=\"3\"> Product </th>\n"
                    + "                                    <th> Image </th>\n"
                    + "                                    <th> Memory </th>\n"
                    + "                                    \n"
                    + "                                    <th> Color </th>\n"
                    + "                                    <th>Unit Price </th>\n"
                    + "                                    <th> Quantity </th>\n"
                    + "                                    <th> Total price </th>\n"
                    + "                                </tr>\n";
            for (OrdersDetailEntity detail : orders.getOrdersDetails()) {
                htmlMsg = htmlMsg + "         <tr>\n"
                        + "                                        <td colspan=\"3\">\n" + detail.getProduct().getName() + "</td>\n"
                        + "                                        \n"
                        + "                                            <td> \n"
                        + "                                                <img style=\"width: 30px; height: 40px \" class=\"miniImage\" src=\"http://localhost:8080/BuyOnlinePhone/resources/img/" + detail.getProduct().getImage() + "alt=\"photo\"/>\n"
                        + "                                        </td>\n"
                        + "                                      \n"
                        + "                                        <td> \n"
                        + detail.getProduct().getMemory()
                        + "                                        </td>\n"
                        + "                                        <td> \n"
                        + detail.getProduct().getColor()
                        + "                                        </td>\n"
                        + "                                        <td> \n"
                        + detail.getProduct().getSalePrice()
                        + "                                        </td>\n"
                        + "\n"
                        + "                                        <td> \n"
                        + +detail.getQuantity() + " \n"
                        + "                                        </td>\n"
                        + "\n"
                        + "                                        <td> \n"
                        + detail.getAmount() + "\n"
                        + "                                        </td>\n"
                        + "                                    </tr>\n"
                        + "\n";

            }

            htmlMsg = htmlMsg + "                                <tr>\n"
                    + "                                    <th colspan=\"3\">\n"
                    + "                                        Discount\n"
                    + "                                    </th>\n"
                    + "                                    <td colspan=\"6\">\n"
                    + orders.getDiscount() + "\n"
                    + "                                    </td>\n"
                    + "                                </tr>\n"
                    + "                                <tr>\n"
                    + "                                    <th colspan=\"3\">\n"
                    + "                                        Bill price\n"
                    + "                                    </th>\n"
                    + "\n"
                    + "                                    <td colspan=\"6\">\n"
                    + orders.getTotalPrice() * (100 - orders.getDiscount()) / 100 + "\n"
                    + "                                    </td>\n"
                    + "                                </tr>\n"
                    + "\n"
                    + "                                <tr>\n"
                    + "                                    <th colspan=\"3\">\n"
                    + "                                        Time order\n"
                    + "                                    </th>\n"
                    + "\n"
                    + "                                    <td colspan=\"6\n"
                    + "                                        \">\n"
                    + orders.getCreateDate() + "\n"
                    + "                                    </td>\n"
                    + "                                </tr>\n"
                    + "                                <tr>\n"
                    + "                                    <th colspan=\"3\">\n"
                    + "                                        Status\n"
                    + "                                    </th>\n"
                    + "\n"
                    + "                                    <td class=\"statusColor\" colspan=\"6\n"
                    + "                                        \">\n"
                    + orders.getOrderStatus() + "\n"
                    + "                                    </td>\n"
                    + "                                </tr>\n"
                    + "                            </table>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </div>                  \n"
                    + "            </div>\n"
                    + "        </div>"
                    + "    </body>\n"
                    + "</html>";

            mimeMessage.setContent(htmlMsg, "text/html");
            helper.setTo(orders.getEmail());
            helper.setSubject("This is the test message for testing gmail smtp server using spring mail");
//            helper.setFrom("abc@gmail.com");
            mailSender.send(mimeMessage);
            session.removeAttribute("orders");
            session.removeAttribute("codeVoucher");
            return gson.toJson(String.valueOf("susccess"));
        }
    }

    @RequestMapping("/voteAndCommet")
    public String voteAndComment(Model model) {
        AccountEntity account = accountService.getAccountInfo(accountService.getUserName());
        if (account != null) {
            model.addAttribute("account", account);
        }
        return "voteAndCommet";
    }

    @RequestMapping(value = "creditCardInfo")
    public String creditCardInfo(Model model,
            @ModelAttribute("orderInfo") OrdersEntity ordersInfo, HttpSession session) {
        OrdersEntity orders = (OrdersEntity) session.getAttribute("orders");
        orders.setEmail(ordersInfo.getEmail());
        orders.setFullName(ordersInfo.getFullName());
        orders.setBirthDate(ordersInfo.getBirthDate());
        orders.setGender(ordersInfo.getGender());
        orders.setAddress(ordersInfo.getAddress());
        orders.setNumberPhone(ordersInfo.getNumberPhone());
        session.setAttribute("orders", orders);
        model.addAttribute("creditCard", new CreditCardEntity());
        return "orderinfo";
    }

    @RequestMapping("/orderInfo")
    public String orderInfo(Model model, HttpSession session) {
        OrdersEntity orders = (OrdersEntity) session.getAttribute(("orders"));
        List<String> roles = SecurityUtils.getRolesOfUser();
        if (orders != null) {
            if (!CollectionUtils.isEmpty(roles) && (roles.contains("ROLE_USER"))) {
                AccountEntity account = accountService.getAccountInfo(accountService.getUserName());
                orders.setEmail(account.getEmail());
                orders.setFullName(account.getFullName());
                orders.setBirthDate(account.getBirthDate());
                orders.setGender(account.getGender());
                orders.setAddress(account.getAddress());
                orders.setNumberPhone(account.getNumberPhone());
                session.setAttribute("orders", orders);
                model.addAttribute("creditCard", new CreditCardEntity());
            }
            return "orderinfo";
        } else {
            return "redirect:/home";
        }
    }


    @RequestMapping(value = "bill/{id}", method = RequestMethod.GET)
    public String billPage(
            Model model,
            @PathVariable("id") int id
    ) {
        model.addAttribute("oderedBill", orderService.findOrderById(id));
        return "bill";
    }


    @RequestMapping("/checkOrder")
    public String showOrder(Model model) {
        return "searchCodeOrder";
    }

    @RequestMapping(value = "/searchOrder/{code}")
    @ResponseBody
    public String searchOrder(Model model,
            @PathVariable("code") String code,
            HttpSession session
    ) {
        OrdersEntity orders = orderService.findOrdersByCode(code);
        Gson gson = new Gson();
        if (orders == null) {
            return gson.toJson(String.valueOf("null"));
        } else {
            session.setAttribute("status", OrderStatus.CREATED);
            session.setAttribute("result", orders);
            return gson.toJson(String.valueOf("susccess"));
        }

    }

    @RequestMapping(value = "/resultOrder")
    public String resultOrder() {
        return "resultOrder";
    }

    @RequestMapping("/cancelOrder/{id}")
    public String cancelOrder(Model model, @PathVariable("id") int id) {
        OrdersEntity orders = orderService.findOrderByIdQuery(id);
        if (orders.getOrderStatus().equals(OrderStatus.CREATED)) {
            orders.getPayment().getCreditCard().setBalance(
                    orders.getPayment().getCreditCard().getBalance()
                    + orders.getPayment().getAmount());
            orders.getPayment().setStatus(PaymentStatus.REFUND);
            paymentService.save(orders.getPayment());
            creditCardService.save(orders.getPayment().getCreditCard());
            for (OrdersDetailEntity detail : orders.getOrdersDetails()) {
                detail.getProduct().setQuantity(detail.getQuantity());
                productService.save(detail.getProduct());
            }
            orders.setOrderStatus(OrderStatus.CANCEL);
            orderService.save(orders);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(orders.getEmail());
            message.setSubject("Bill");
            message.setText("Cancel order with code : " 
                    + orders.getCode() + " cancel success .access link : " 
                            + "http://localhost:8080/BuyOnlinePhone/checkOrder");
            mailSender.send(message);
        }
        model.addAttribute("message", "You have successfully canceled, please check your email again");
        return "redirect:/checkOrder";
    }

    @RequestMapping(value = "/checkQuantityByAjax/{productId}/{quantity}")
    @ResponseBody
    public String checkQuantityByAjax(
            @PathVariable("productId") int productId,
            @PathVariable("quantity") int quantity
    ) {
        Gson gson = new Gson();
        ProductEntity product = productService.findProductById(productId);
        if (product.getQuantity() < quantity) {
            return gson.toJson(String.valueOf("error"));
        } else {
            return gson.toJson(String.valueOf(""));
        }
    }

    @RequestMapping(value = "/checkQuantityCardByAjax/{productId}/{quantity}")
    @ResponseBody
    public String checkQuantityCardByAjax(
            @PathVariable("productId") int productId,
            @PathVariable("quantity") int quantity,
            HttpSession session
    ) {
        OrdersEntity orders = (OrdersEntity) session.getAttribute("orders");
        Gson gson = new Gson();
        ProductEntity product = productService.findProductById(productId);
        double amount = 0;
        for (OrdersDetailEntity detail : orders.getOrdersDetails()) {
            if (detail.getProduct().getId() == productId) {
                if (product.getQuantity() < (quantity - detail.getQuantity())) {
                    return gson.toJson(String.valueOf("error"));
                }
                product.setQuantity(product.getQuantity() - (quantity - detail.getQuantity()));
                detail.setQuantity(quantity);
                detail.setAmount(detail.getQuantity() * product.getSalePrice());
                amount = detail.getAmount();
                productService.save(product);
            }
        }
        session.setAttribute("orders", orders);
        return gson.toJson(String.valueOf(amount));

    }

    @RequestMapping(value = "/updateQuantityByAjax")
    public String updateQuantityByAjax() {
        return "updateQuantity";
    }

    @RequestMapping(value = "/checkDiscount/{discount}")
    @ResponseBody
    public String checkDiscount(@PathVariable("discount") String code,
            HttpSession session
    ) {
        OrdersEntity orders = (OrdersEntity) session.getAttribute("orders");
        CodeVoucherEntity voucher = codeVoucherService.findCodeVoucherByName(code);
        CodeVoucherEntity codeVoucher = (CodeVoucherEntity) session.getAttribute("codeVoucher");
        Gson gson = new Gson();
//        if (codeVoucher.getName().equals(code) ) {
//            return gson.toJson(String.valueOf(""));
//        }       
        if (voucher == null) {
            return gson.toJson(String.valueOf("not true"));
        }
        if (voucher.isCheckUsed() == false) {
            return gson.toJson(String.valueOf("code used"));
        }
        if (!voucher.getPromotion().getStatus().equals(PromotionStatus.ACTIVE)) {
            return gson.toJson(String.valueOf("expired"));
        }
        orders.setDiscount(voucher.getPromotion().getDiscountPercent());
        voucher.setCheckUsed(false);
        session.setAttribute("codeVoucher", voucher);
        session.setAttribute("orders", orders);
        return gson.toJson(String.valueOf(orders.getDiscount()));
    }

    @RequestMapping(value = "/discount")
    public String discount() {
        return "discount";
    }

    @RequestMapping(value = "/rating/{comment}/{rating}/{productId}")
    @ResponseBody
    public String rating(@PathVariable("comment") String comment,
            @PathVariable("rating") int rating,
            @PathVariable("productId") int id) {
        Gson gson = new Gson();
        AccountEntity account = accountService.getAccountInfo(accountService.getUserName());
        if (account == null) {
            return gson.toJson(String.valueOf("error account"));
        }
        for (OrdersEntity order : account.getOrders()) {
            for (OrdersDetailEntity detail : order.getOrdersDetails()) {
                if (detail.getProduct().getId() == id) {
                    for (VoteAndCommentEntity vote : account.getVoteAndComments()) {
                        if (vote.getProduct().getId() == id) {
                            return gson.toJson(String.valueOf("error rating"));
                        }
                    }
                    ProductEntity product = productService.findProductById(id);
                    VoteAndCommentEntity voteAndComment = new VoteAndCommentEntity();
                    voteAndComment.setAccount(account);
                    voteAndComment.setProduct(product);
                    voteAndComment.setContent(comment);
                    voteAndComment.setVote(id);
                    voteAndComment.setCreateDate(new Date());
                    voteAndCommentService.save(voteAndComment);
                    Set<VoteAndCommentEntity> vc = product.getVoteAndComments();
                    vc.add(voteAndComment);
                    product.setVoteAndComments(vc);
                    productService.save(product);
                    Set<VoteAndCommentEntity> vca = account.getVoteAndComments();
                    vca.add(voteAndComment);
                    account.setVoteAndComments(vca);
                    accountService.save(account);
                    return gson.toJson(String.valueOf("susccess"));
                }
            }

        }
        return gson.toJson(String.valueOf("error product"));
    }
}
