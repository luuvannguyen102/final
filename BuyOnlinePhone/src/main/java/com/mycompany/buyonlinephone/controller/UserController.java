/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buyonlinephone.controller;

import com.google.gson.Gson;
import com.mycompany.buyonlinephone.entities.AccountEntity;
import com.mycompany.buyonlinephone.entities.CreditCardEntity;
import com.mycompany.buyonlinephone.entities.OrdersDetailEntity;
import com.mycompany.buyonlinephone.entities.OrdersEntity;
import com.mycompany.buyonlinephone.entities.PaymentEntity;
import com.mycompany.buyonlinephone.entities.ProductEntity;
import com.mycompany.buyonlinephone.entities.VoteAndCommentEntity;
import com.mycompany.buyonlinephone.enums.CommonStatus;
import com.mycompany.buyonlinephone.enums.Gender;
import com.mycompany.buyonlinephone.enums.OrderStatus;
import com.mycompany.buyonlinephone.enums.PaymentStatus;
import com.mycompany.buyonlinephone.enums.ProductStatus;
import com.mycompany.buyonlinephone.service.AccountRoleService;
import com.mycompany.buyonlinephone.service.AccountService;
import com.mycompany.buyonlinephone.service.CategoryService;
import com.mycompany.buyonlinephone.service.CreditCardService;
import com.mycompany.buyonlinephone.service.OrderService;
import com.mycompany.buyonlinephone.service.OrdersDetailService;
import com.mycompany.buyonlinephone.service.PaymentService;
import com.mycompany.buyonlinephone.service.ProductService;
import com.mycompany.buyonlinephone.service.VoteAndCommentService;
import com.mycompany.buyonlinephone.utils.SecurityUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

@Controller
@RequestMapping("/user")
public class UserController {

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

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String viewHome(Model model, HttpSession session) {
        AccountEntity accountEntity = accountService.getAccountInfo(accountService.getUserName());
        session.setAttribute("userName", accountEntity.getFullName());
        model.addAttribute("newproduct", productService.findByNewProductInThirTyDay());
        model.addAttribute("topselling", ordersDetailService.getTopSelling(PageRequest.of(0, 10)));
        return "user/home";
    }

    @RequestMapping(value = "/mypage", method = RequestMethod.GET)
    public String myPage(Model model) {
        model.addAttribute("MALE", Gender.MALE);
        model.addAttribute("FEMALE", Gender.FEMALE);
        model.addAttribute("account", accountService.getAccountInfo(accountService.getUserName()));
        model.addAttribute("status", OrderStatus.CREATED);
        return "user/mypage";
    }

    @RequestMapping(value = "/detail/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        OrdersEntity result = orderService.findOrderByIdQuery(id);
        model.addAttribute("result", result);
        return "detail";
    }

    @RequestMapping(value = "/searchOrderCode", method = RequestMethod.POST)
    public String searchOrderCode(@RequestParam(value = "search", defaultValue = "") String search, Model model) {
        OrdersEntity orders = orderService.findOrdersByCode(search);
        AccountEntity account = accountService.getAccountInfo(accountService.getUserName());
        if (orders == null) {
            model.addAttribute("notFound", "No results found");
        } else {
            Set<OrdersEntity> ordersEntitys = new HashSet<>();
            ordersEntitys.add(orders);
            account.setOrders(ordersEntitys);
        }
        model.addAttribute("MALE", Gender.MALE);
        model.addAttribute("FEMALE", Gender.FEMALE);
        model.addAttribute("account", account);
        model.addAttribute("status", OrderStatus.CREATED);
        return "user/mypage";
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public String changePassword(Model model,
            @RequestParam("password") String password,
            @RequestParam("repassword") String repassword
    ) {
        if (password.equals(repassword)) {
            AccountEntity account = accountService.getAccountInfo(accountService.getUserName());
            account.setPassword(accountService.encrytePassword(password));
            accountService.save(account);
        } else {
            model.addAttribute("errorPass", "password not same");
        }
        return "redirect:/user/mypage";
    }

    @RequestMapping(value = "/changeinfo", method = RequestMethod.POST)
    public String changeInfo(
            @RequestParam("fullName") String fullName,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("gender") Gender gender,
            @RequestParam("birthDate") Date birthDate
    ) {
        AccountEntity account = accountService.getAccountInfo(accountService.getUserName());
        account.setFullName(fullName);
        account.setAddress(address);
        account.setGender(gender);
        account.setNumberPhone(phone);
        account.setBirthDate(birthDate);
        accountService.save(account);
        return "redirect:/user/mypage";
    }

    @RequestMapping(value = "/changeinfo", method = RequestMethod.GET)
    public String changeInfoGet() {
        return "redirect:/user/mypage";
    }

    @RequestMapping("/comment")
    public String comment(Model model) {
        AccountEntity account = accountService.getAccountInfo(accountService.getUserName());
        model.addAttribute("email", account.getEmail());
        model.addAttribute("fullName", account.getFullName());
        return "user/comment";
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String product(Model model) {
        Set<ProductEntity> products = productService.getProducts();
        model.addAttribute("products", products);
        model.addAttribute("quantity", productService.getCountProduct());
        model.addAttribute("category", categoryService.getCategory());
        return "product";
    }

//    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
//    public String category(Model model, @PathVariable("category") String category) {
//        if (category.equals("all")) {
//            model.addAttribute("products",
//                    productService.getProducts());
//        } else {
//            model.addAttribute("products",
//                    productService.getProductsByCategory(
//                            ProductStatus.ACTIVE, category));
//        }
//        return "category";
//    }
//    @RequestMapping(value = "/sort/{sort}")
//    public String sortPrice (Model model, @PathVariable("sort") String type) {
//       model.addAttribute(type, type)
//   
//    }

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

//    @RequestMapping(value = "/detailBymemory")
//    public String detailByMemory () {
//    
//    }
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

//    @RequestMapping(value = "/updateQuantity/{id}", method = RequestMethod.POST)
//    public String updateQuantity(Model model, HttpSession session, @RequestParam("quantity") int quantity,
//            @PathVariable("id") int id) {
//        OrdersEntity orders = (OrdersEntity) session.getAttribute("orders");
//        ProductEntity product = productService.findProductById(id);
//        if (orders != null) {
//            if (product.getQuantity() >= quantity) {
//                for (OrdersDetailEntity detail : orders.getOrdersDetails()) {
//                    if (detail.getProduct().getId() == id) {
//                        product.setQuantity(product.getQuantity() - (quantity - detail.getQuantity()));
//                        productService.save(product);
//                        detail.setQuantity(quantity);
//                        detail.setAmount(detail.getProduct().getPrice() * quantity
//                                - detail.getProduct().getPrice() * quantity * detail.getDiscount() / 100);
//                    }
//
//                }
//            }
//        }
//        session.setAttribute("orders", orders);
//        return "redirect:/order";
//    }
////
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
        session.setAttribute("orders", orders);
        return "redirect:/creditCard";
    }

    @RequestMapping(value = "/creditCard")
    public String creditCard(Model model) {
        model.addAttribute("creditCard", new CreditCardEntity());
        return "creditCard";
    }

    @RequestMapping(value = "/checkCreditCard", method = RequestMethod.POST)
    public String checkCreditCard(Model model, @RequestParam("cardNumber") int cardNumber,
            @RequestParam("cvcCode") String cvcCode) {
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
    public String pay(@PathVariable("id") int id, HttpSession session) {
        OrdersEntity orders = (OrdersEntity) session.getAttribute("orders");
        if (orders == null) {
            return "addcart";
        }
        Gson gson = new Gson();
        CreditCardEntity creditCard = creditCardService.findById(id);
        if (creditCard.getBalance() < orders.getTotalPrice()) {
            return gson.toJson(String.valueOf(""));
        } else {
//            deduction
            creditCard.setBalance(creditCard.getBalance() - orders.getTotalPrice());
//            add new object payment
            PaymentEntity payment = new PaymentEntity();
            payment.setAmount(orders.getTotalPrice());
            payment.setCreateDate(new Date());
            payment.setCreditCard(creditCard);
            payment.setOrder(orders);
            payment.setStatus(PaymentStatus.PAID);
            orders.setPayment(payment);
            orders.setCreateDate(new Date());
            creditCardService.save(creditCard);
            orderService.save(orders);
            paymentService.save(payment);
            ordersDetailService.save(orders.getOrdersDetails());
            //                send bill to email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(orders.getEmail());
            message.setSubject("Bill");
            message.setText("code of orders are: " + orders.getCode() + " your bill in "
                    + "http://localhost:8080/BuyOnlinePhone/checkOrder");
            mailSender.send(message);
            session.removeAttribute("orders");
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

//    @RequestMapping(value = "/payment", method = RequestMethod.POST)
//    public String payment(Model model, HttpSession session,
//            @RequestParam("cvcCode") String cvcCode,
//            @RequestParam("cardNumber") int cardNumber
//    ) throws MessagingException {
//        OrdersEntity orders = (OrdersEntity) session.getAttribute("orders");
//        CreditCardEntity cce = creditCardService.findByCardNumberAndStatusAndCvcCode(cardNumber, CommonStatus.ACTIVE, cvcCode);
//        if (cce == null) {
//            model.addAttribute("errorCreditCard", "The creditCard is incorrect");
//            return "orderinfo";
//        } else {
//            if (cce.getBalance() > orders.getTotalPrice()) {
//                cce.setBalance(cce.getBalance() - orders.getTotalPrice());
//                creditCardService.save(cce);
//                PaymentEntity payment = new PaymentEntity();
//                payment.setAmount(orders.getTotalPrice());
//                payment.setCreateDate(new Date());
//                payment.setCreditCard(cce);
//                payment.setOrders(orders);
//                //                    save ordersEntity
//                Set<PaymentEntity> listPayment = new HashSet<>();
//                listPayment.add(payment);
//                orders.setCreateDate(new Date());
//                orders.setPayments(listPayment);
//                orderService.save(orders);
//                ordersDetailService.save(orders.getOrdersDetails());
//                paymentService.save(listPayment);
////                send bill to email
//                SimpleMailMessage message = new SimpleMailMessage();
//                message.setTo(orders.getEmail());
//                message.setSubject("Bill");
//                message.setText("code of orders are: " + orders.getCode() + " your bill in "
//                        + "http://localhost:8080/BuyOnlinePhone/checkOrder");
//                mailSender.send(message);
//                session.removeAttribute("orders");
//                session.removeAttribute("count");
//                List<String> roles = SecurityUtils.getRolesOfUser();
//                if (!CollectionUtils.isEmpty(roles) && (roles.contains("ROLE_USER"))) {
//                    return "redirect:/user/comment";
//                }
//                model.addAttribute("oderedBill", orderService.findOrderById(orders.getId()));
//                return "bill";
//            } else {
//                model.addAttribute("errorCreditCard", "the balance not enough");
//                return "orderinfo";
//            }
//        }
//
//    }
    @RequestMapping(value = "bill/{id}", method = RequestMethod.GET)
    public String billPage(
            Model model,
            @PathVariable("id") int id
    ) {
        model.addAttribute("oderedBill", orderService.findOrderById(id));
        return "bill";
    }
//    @RequestMapping("getMemoryByAjax/{memory}/{color}/{numberGroup}")
//    @ResponseBody
//    public String getPriceByMemoryAjax(
//            @PathVariable("memory") String memory,
//            @PathVariable("color") String color,
//            @PathVariable("numberGroup") int numberGroup
//    ) {
//        Gson gson = new Gson();
//        Set<ProductEntity> product = productService.findProductByNumberGroupAndMemory(numberGroup, 
//                ProductStatus.ACTIVE, memory);
//                
//        return gson.toJson(String.valueOf(product.getPrice()));
//    }
////    
//     @RequestMapping("get price by quantity/{memoryId}/{colorId}/{productId}/{quantityId}")
//    @ResponseBody
//    public String arletQuantityAjax(
//            @PathVariable("memoryId") int memoryId,
//            @PathVariable("colorId") int colorId,
//            @PathVariable("productId") int productId,
//            @PathVariable("quantityId") int quantityId,
//            HttpSession session
//        
//    ) {
//        OrdersEntity orders = (OrdersEntity) session.getAttribute("orders");
//        ProductDetailEntity productDetail = productDetailService.findByProductAndColorAndMemory(productId, colorId, memoryId);  
//        Gson gson = new Gson();
//        int quantity = 0;
//        if(orders != null) {
//            quantity = orders.getOrdersDetailEntities()
//        }
//             
//        return gson.toJson(String.valueOf(productDetail.getQuantity()));
//    }

    @RequestMapping("/checkOrder")
    public String showOrder(Model model) {
        return "searchCodeOrder";
    }

    @RequestMapping(value = "/searchOrder", method = RequestMethod.POST)
    public String searchOrder(Model model,
            @RequestParam("code") String code
    ) {
        OrdersEntity orders = orderService.findOrdersByCode(code);
        if (orders == null) {
            return "redirect:/checkOrder";
        } else {
            model.addAttribute("order", orders);
            return "bill";
        }

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
            message.setText("Cancel order with code : " + orders.getCode() + " success access link : " + "http://localhost:8080/BuyOnlinePhone/checkOrder");
            mailSender.send(message);
        }
        
            return "redirect:/user/mypage";             
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

//    @RequestMapping(value = "/checkDiscount/{codeDiscount}/{productId}")
//    @ResponseBody
//    public String checkDiscount(@PathVariable("codeDiscount") String code,
//            @PathVariable("productId") int id) {
//        ProductEntity product = productService.findProductById(id);
//        Gson gson = new Gson();
//        for (PromotionEntity promotion : product.getPromotions()) {
//            if (productService.checkDiscount(code, promotion.getCodeVoucher())) {
//                if (promotion.getStatus().equals(PromotionStatus.ACTIVE)) {
//                    int discount = promotion.getDiscountPercent();
//                    return gson.toJson(String.valueOf(discount));
//                } else {
//                    return gson.toJson(String.valueOf("expired"));
//                }
//            }
//        }
//        return gson.toJson(String.valueOf(""));
//    }
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
