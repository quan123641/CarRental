package com.hoaiphong.carrental.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.hoaiphong.carrental.dtos.transaction.TransactionCreateDTO;
import com.hoaiphong.carrental.dtos.transaction.TransactionDTO;
import com.hoaiphong.carrental.dtos.transaction.TransactionUpdateWalletDTO;
import com.hoaiphong.carrental.dtos.user.UserUpdateDTO;
import com.hoaiphong.carrental.dtos.user.UserUpdatePasswordDTO;
import com.hoaiphong.carrental.dtos.user.UserUpdateWalletDTO;
import com.hoaiphong.carrental.entities.User;
import com.hoaiphong.carrental.services.TransactionService;
import com.hoaiphong.carrental.services.UserService;

import jakarta.validation.Valid;

@Controller
public class HomeController {

    private final UserService userService;
    private final TransactionService transactionService;

    public HomeController(UserService userService, TransactionService transactionService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping
    public String index( ) {
        return "home/index";
    }
    @GetMapping("about")
    public String aboutus(){
        return "home/about";
    }

    @GetMapping("customer")
    public String customer() {
        return "customer/customer";
    }

    @GetMapping("owner")
    public String owner() {
        return "owner/owner";
    }

    @GetMapping("myprofile")
    public String myProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // Lấy thông tin người dùng từ username
        String currentUsername = userDetails.getUsername();
        User currentUser = userService.findByUsername(currentUsername);

        if (currentUser != null) {
            UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
            userUpdateDTO.setFullName(currentUser.getName());
            userUpdateDTO.setPhone(currentUser.getPhone());
            userUpdateDTO.setAddress(currentUser.getAddress());
            userUpdateDTO.setDateOfBirth(currentUser.getDateOfBirth());
            userUpdateDTO.setNationalId(currentUser.getNationalId());
            // Thêm các trường khác nếu cần

            model.addAttribute("userUpdateDTO", userUpdateDTO);
        }

        return "home/myprofile";
    }

    @PostMapping("myprofile")
    public String myProfile(@ModelAttribute @Valid UserUpdateDTO userUpdateDTO, BindingResult bindingResult,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (bindingResult.hasErrors()) {
        return "home/userprofile";
        }
        var updatemember = userService.update(userUpdateDTO, userDetails.getUsername());
        if (updatemember == null) {
            model.addAttribute("message", "create content failed");
            return "home/userprofile";
        }
        return "redirect:/";
    }

    @PostMapping("myprofile-updatepassword")
    public String updatePassword(@ModelAttribute @Valid UserUpdatePasswordDTO userUpdatePasswordDTO, BindingResult bindingResult,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors()) {
        return "home/userprofile";
        }
        var updatemember = userService.update(userUpdatePasswordDTO, userDetails.getUsername());

        if (updatemember == null) {
            model.addAttribute("message", "create content failed");
            return "home/userprofile";
        }
        return "redirect:/";
    }

    @GetMapping("mywallet")
public String myWallet(
        @RequestParam(value = "from", required = false) String from, 
        @RequestParam(value = "to", required = false) String to,
        @RequestParam(defaultValue = "0") int page,   // Trang hiện tại
        @RequestParam(defaultValue = "10") int size,  // Số bản ghi trên mỗi trang
        Model model) {

    // Lấy username của người dùng đang đăng nhập
    String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
    System.out.println(currentUsername);

    User currentUser = userService.findByUsername(currentUsername);

    // Kiểm tra nếu currentUser không null
    if (currentUser == null) {
        model.addAttribute("error", "User not found");
        return "home/error";
    }

    // Lấy thông tin ví của người dùng
    Double currentUserWallet = currentUser.getWallet();
    model.addAttribute("currentUserWallet", currentUserWallet);

    model.addAttribute("userupdatewalletDTO", new UserUpdateWalletDTO());
    model.addAttribute("transactionUpdateWalletDTO", new TransactionUpdateWalletDTO());

    // Thiết lập phân trang
    Pageable pageable = PageRequest.of(page, size);

    Page<TransactionDTO> transactions;

    // Nếu có từ ngày và đến ngày, gọi phương thức findByUserAndDate
    if (from != null && to != null && !from.isEmpty() && !to.isEmpty()) {
        LocalDate startDate = LocalDate.parse(from);
        LocalDate endDate = LocalDate.parse(to);
        transactions = transactionService.findByUserAndDate(currentUser, startDate, endDate, pageable);
    } else {
        // Nếu không có ngày, lấy tất cả giao dịch của người dùng
        transactions = transactionService.findByUser(currentUser, pageable);
    }

    // Thêm các thuộc tính vào model
    model.addAttribute("transactions", transactions);
    model.addAttribute("totalPages", transactions.getTotalPages());
    model.addAttribute("totalElements", transactions.getTotalElements());
    model.addAttribute("pageLimit", 3);  // Giới hạn số trang hiển thị
    model.addAttribute("page", page);
    model.addAttribute("pageSize", size);
    model.addAttribute("pageSizes", new Integer[]{10, 20, 30, 50, 100});

    return "home/mywallet";
}

    



    @PostMapping("mywallet")
public String create(Model model,
    @ModelAttribute @Valid TransactionUpdateWalletDTO transactionUpdateWalletDTO,
    BindingResult bindingResult,
    @AuthenticationPrincipal UserDetails userDetails) {

    // Kiểm tra lỗi dữ liệu
    if (bindingResult.hasErrors()) {
        return "home/mywallet";
    }

    // Lấy username của người dùng đang đăng nhập
    String currentUsername = userDetails.getUsername();
    User currentUser = userService.findByUsername(currentUsername); // Lấy người dùng từ username

    if (currentUser == null) {
        model.addAttribute("error", "User not found");
        return "home/mywallet"; // Trả về trang nếu không tìm thấy người dùng
    }

    // Thiết lập thời gian giao dịch
    transactionUpdateWalletDTO.setDateTime(LocalDateTime.now());
    transactionUpdateWalletDTO.setUserId(currentUser.getId());

    // Kiểm tra loại giao dịch
    if ("withdraw".equals(transactionUpdateWalletDTO.getType())) {
        // Kiểm tra số dư ví trước khi cho phép rút tiền
        if (currentUser.getWallet() < transactionUpdateWalletDTO.getAmount()) {
            model.addAttribute("error", "Insufficient balance to withdraw.");
            return "home/mywallet"; // Trả về trang với thông báo lỗi
        }
    }

    // Tạo giao dịch
    var transaction = transactionService.create(transactionUpdateWalletDTO);
    
    // Kiểm tra nếu giao dịch không thành công
    if (transaction == null) {
        return "home/mywallet";
    }

    // Cập nhật số dư ví dựa trên loại giao dịch
    if ("top-up".equals(transactionUpdateWalletDTO.getType())) {
        // Nếu là top-up, cộng số tiền vào số dư ví
        currentUser.setWallet(currentUser.getWallet() + transactionUpdateWalletDTO.getAmount());
    } else if ("withdraw".equals(transactionUpdateWalletDTO.getType())) {
        // Nếu là withdraw, trừ số tiền từ số dư ví
        currentUser.setWallet(currentUser.getWallet() - transactionUpdateWalletDTO.getAmount());
    }

    // Cập nhật người dùng với số dư mới
    userService.update(currentUser, currentUsername);

    // Trả về trang mywallet
    return "redirect:/mywallet";
}


}
