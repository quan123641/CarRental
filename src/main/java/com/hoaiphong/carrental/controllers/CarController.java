package com.hoaiphong.carrental.controllers;

import com.hoaiphong.carrental.controllers.utils.ImageUploadUtil;
import com.hoaiphong.carrental.dtos.car.CarCreateDTO;
import com.hoaiphong.carrental.dtos.car.CarUpdateDetailDTO;
import com.hoaiphong.carrental.dtos.car.CarUpdatePricingDTO;
import com.hoaiphong.carrental.dtos.car.CarUpdateStatusDTO;
import com.hoaiphong.carrental.dtos.messages.Message;
import com.hoaiphong.carrental.entities.Car;
import com.hoaiphong.carrental.entities.User;
import com.hoaiphong.carrental.repositories.CarRepository;
import com.hoaiphong.carrental.repositories.UserRepository;
import com.hoaiphong.carrental.services.CarService;
import com.hoaiphong.carrental.services.UserService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/car")
public class CarController {
    private final CarService carService;
    private final ImageUploadUtil imageUploadUtil;
    private final UserRepository userRepository;
    private final UserService userService;
    private final CarRepository carRepository;

    public CarController(CarService carService, ImageUploadUtil imageUploadUtil, UserRepository userRepository, UserService userService, CarRepository carRepository) {
        this.carRepository = carRepository;
        this.userService = userService;
        this.imageUploadUtil = imageUploadUtil;
        this.carService = carService;
        this.userRepository = userRepository;
    }

    @RequestMapping
    public String index() {
        return "car/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        var carCreateDTO = new CarCreateDTO();
        model.addAttribute("carCreateDTO", carCreateDTO);
        return "car/add-car/add-car";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute CarCreateDTO carCreateDTO,
                         BindingResult bindingResult,
                         String email,
                         @RequestParam("registrationPaperFile") MultipartFile registrationPaperFile,
                         @RequestParam("certificateOfInspectionFile") MultipartFile certificateOfInspectionFile,
                         @RequestParam("insuranceFile") MultipartFile insuranceFile,
                         @RequestParam("imageFrontFile") MultipartFile imageFrontFile,
                         @RequestParam("imageBackFile") MultipartFile imageBackFile,
                         @RequestParam("imageLeftFile") MultipartFile imageLeftFile,
                         @RequestParam("imageRightFile") MultipartFile imageRightFile,
                         RedirectAttributes redirectAttributes,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("carCreateDTO", carCreateDTO);
            return "car/add-car/add-car";
        }

        if (registrationPaperFile != null && !registrationPaperFile.isEmpty()) {
            try {
                byte[] bytes = registrationPaperFile.getBytes();
                // Create folder if not exist following format:
                // src/main/resources/static/documents/year/month/day
                LocalDateTime date = LocalDateTime.now();
                Path folder = Paths.get("src/main/resources/static/documents/" + date.getYear() + "/"
                        + date.getMonthValue() + "/" + date.getDayOfMonth());
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }
                // Create file name following format: originalFileName + epochTime + extension
                String originalFileName = registrationPaperFile.getOriginalFilename();
                // Convert date to string epoch time
                Long epochTime = Instant.now().getEpochSecond();
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-" + epochTime
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                Path path = Paths.get(folder.toString(), fileName);
                Files.write(path, bytes);
                carCreateDTO.setRegistrationPaper(folder.toString().replace("src\\main\\resources\\static", "") + "/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                Message errorMessage = new Message("error", "Failed to upload file");
                model.addAttribute("message", errorMessage);
                var cars = carService.findAll();
                model.addAttribute("cars", cars);

                bindingResult.rejectValue("image", "image", "Failed to upload image");
                return "car/add-car/add-car";
            }
        }

        if (certificateOfInspectionFile != null && !certificateOfInspectionFile.isEmpty()) {
            try {
                byte[] bytes = certificateOfInspectionFile.getBytes();
                // Create folder if not exist following format:
                // src/main/resources/static/documents/year/month/day
                LocalDateTime date = LocalDateTime.now();
                Path folder = Paths.get("src/main/resources/static/documents/" + date.getYear() + "/"
                        + date.getMonthValue() + "/" + date.getDayOfMonth());
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }
                // Create file name following format: originalFileName + epochTime + extension
                String originalFileName = certificateOfInspectionFile.getOriginalFilename();
                // Convert date to string epoch time
                Long epochTime = Instant.now().getEpochSecond();
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-" + epochTime
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                Path path = Paths.get(folder.toString(), fileName);
                Files.write(path, bytes);
                carCreateDTO.setCertificateOfInspection(folder.toString().replace("src\\main\\resources\\static", "") + "/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                Message errorMessage = new Message("error", "Failed to upload file");
                model.addAttribute("message", errorMessage);
                var cars = carService.findAll();
                model.addAttribute("cars", cars);

                bindingResult.rejectValue("image", "image", "Failed to upload image");
                return "car/add-car/add-car";
            }
        }

        if (insuranceFile != null && !insuranceFile.isEmpty()) {
            try {
                byte[] bytes = insuranceFile.getBytes();
                // Create folder if not exist following format:
                // src/main/resources/static/documents/year/month/day
                LocalDateTime date = LocalDateTime.now();
                Path folder = Paths.get("src/main/resources/static/documents/" + date.getYear() + "/"
                        + date.getMonthValue() + "/" + date.getDayOfMonth());
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }
                // Create file name following format: originalFileName + epochTime + extension
                String originalFileName = insuranceFile.getOriginalFilename();
                // Convert date to string epoch time
                Long epochTime = Instant.now().getEpochSecond();
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-" + epochTime
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                Path path = Paths.get(folder.toString(), fileName);
                Files.write(path, bytes);
                carCreateDTO.setInsurance(folder.toString().replace("src\\main\\resources\\static", "") + "/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                Message errorMessage = new Message("error", "Failed to upload file");
                model.addAttribute("message", errorMessage);
                var cars = carService.findAll();
                model.addAttribute("cars", cars);

                bindingResult.rejectValue("image", "image", "Failed to upload image");
                return "car/add-car/add-car";
            }
        }

        if (imageFrontFile != null && !imageFrontFile.isEmpty()) {
            try {
                byte[] bytes = imageFrontFile.getBytes();
                // Create folder if not exist following format:
                // src/main/resources/static/images/recipes/year/month/day
                LocalDateTime date = LocalDateTime.now();
                Path folder = Paths.get("src/main/resources/static/images/cars/" + date.getYear() + "/"
                        + date.getMonthValue() + "/" + date.getDayOfMonth());
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }
                // Create file name following format: originalFileName + epochTime + extension
                String originalFileName = imageFrontFile.getOriginalFilename();
                // Convert date to string epoch time
                Long epochTime = Instant.now().getEpochSecond();
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-" + epochTime
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                Path path = Paths.get(folder.toString(), fileName);
                Files.write(path, bytes);
                carCreateDTO.setImageFront(folder.toString().replace("src\\main\\resources\\static", "") + "/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                Message errorMessage = new Message("error", "Failed to upload image");
                model.addAttribute("message", errorMessage);
                var cars = carService.findAll();
                model.addAttribute("cars", cars);

                bindingResult.rejectValue("image", "image", "Failed to upload image");
                return "car/add-car/add-car";
            }
        }

        if (imageBackFile != null && !imageBackFile.isEmpty()) {
            try {
                byte[] bytes = imageBackFile.getBytes();
                // Create folder if not exist following format:
                // src/main/resources/static/images/recipes/year/month/day
                LocalDateTime date = LocalDateTime.now();
                Path folder = Paths.get("src/main/resources/static/images/cars/" + date.getYear() + "/"
                        + date.getMonthValue() + "/" + date.getDayOfMonth());
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }
                // Create file name following format: originalFileName + epochTime + extension
                String originalFileName = imageBackFile.getOriginalFilename();
                // Convert date to string epoch time
                Long epochTime = Instant.now().getEpochSecond();
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-" + epochTime
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                Path path = Paths.get(folder.toString(), fileName);
                Files.write(path, bytes);
                carCreateDTO.setImageBack(folder.toString().replace("src\\main\\resources\\static", "") + "/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                Message errorMessage = new Message("error", "Failed to upload image");
                model.addAttribute("message", errorMessage);
                var cars = carService.findAll();
                model.addAttribute("cars", cars);

                bindingResult.rejectValue("image", "image", "Failed to upload image");
                return "car/add-car/add-car";
            }
        }

        if (imageLeftFile != null && !imageLeftFile.isEmpty()) {
            try {
                byte[] bytes = imageLeftFile.getBytes();
                // Create folder if not exist following format:
                // src/main/resources/static/images/recipes/year/month/day
                LocalDateTime date = LocalDateTime.now();
                Path folder = Paths.get("src/main/resources/static/images/cars/" + date.getYear() + "/"
                        + date.getMonthValue() + "/" + date.getDayOfMonth());
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }
                // Create file name following format: originalFileName + epochTime + extension
                String originalFileName = imageLeftFile.getOriginalFilename();
                // Convert date to string epoch time
                Long epochTime = Instant.now().getEpochSecond();
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-" + epochTime
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                Path path = Paths.get(folder.toString(), fileName);
                Files.write(path, bytes);
                carCreateDTO.setImageLeft(folder.toString().replace("src\\main\\resources\\static", "") + "/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                Message errorMessage = new Message("error", "Failed to upload image");
                model.addAttribute("message", errorMessage);
                var cars = carService.findAll();
                model.addAttribute("cars", cars);

                bindingResult.rejectValue("image", "image", "Failed to upload image");
                return "car/add-car/add-car";
            }
        }

        if (imageRightFile != null && !imageRightFile.isEmpty()) {
            try {
                byte[] bytes = imageRightFile.getBytes();
                // Create folder if not exist following format:
                // src/main/resources/static/images/recipes/year/month/day
                LocalDateTime date = LocalDateTime.now();
                Path folder = Paths.get("src/main/resources/static/images/cars/" + date.getYear() + "/"
                        + date.getMonthValue() + "/" + date.getDayOfMonth());
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }
                // Create file name following format: originalFileName + epochTime + extension
                String originalFileName = imageRightFile.getOriginalFilename();
                // Convert date to string epoch time
                Long epochTime = Instant.now().getEpochSecond();
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-" + epochTime
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                Path path = Paths.get(folder.toString(), fileName);
                Files.write(path, bytes);
                carCreateDTO.setImageRight(folder.toString().replace("src\\main\\resources\\static", "") + "/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
                Message errorMessage = new Message("error", "Failed to upload image");
                model.addAttribute("message", errorMessage);
                var cars = carService.findAll();
                model.addAttribute("cars", cars);

                bindingResult.rejectValue("image", "image", "Failed to upload image");
                return "car/add-car/add-car";
            }
        }
        var user = userRepository.findByEmail(email);
        carCreateDTO.setUser(user);
        var result = carService.create(carCreateDTO);

        if (result == null) {
            var errorMessage = new Message("error", "Failed to create car");
            model.addAttribute("message", errorMessage);
            return "car/add-car/add-car";
        }
        var successMessage = new Message("success", "Category created successfully");
        redirectAttributes.addFlashAttribute("message", successMessage);
        return "redirect:/car/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable UUID id, Model model) {
        var carDTO = carService.findById(id);
        var carUpdateDetailDTO = carService.findById(id);
        var carUpdatePricingDTO = carService.findById(id);
        var carUpdateStatusDTO = carService.findById(id);

        model.addAttribute("carUpdateStatusDTO", carUpdateStatusDTO);
        model.addAttribute("carUpdatePricingDTO", carUpdatePricingDTO);
        model.addAttribute("carUpdateDetailDTO", carUpdateDetailDTO);
        model.addAttribute("carDTO", carDTO);
        return "car/edit-car";
    }

    @PostMapping("/edit-detail/{id}")
    public String edit(@PathVariable UUID id,
                       @ModelAttribute @Valid CarUpdateDetailDTO carUpdateDetailDTO,
                       RedirectAttributes redirectAttributes,
                       @RequestParam("imageFrontFile") MultipartFile imageFrontFile,
                       @RequestParam("imageBackFile") MultipartFile imageBackFile,
                       @RequestParam("imageLeftFile") MultipartFile imageLeftFile,
                       @RequestParam("imageRightFile") MultipartFile imageRightFile,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("carUpdateDetailDTO", carUpdateDetailDTO);
            return "car/edit";
        }

        var oldcar = carService.findById(id);
        // Upload từng hình ảnh
        String imageFrontPath = imageUploadUtil.uploadImage(imageFrontFile, oldcar.getImageFront(), model, bindingResult);
        if (imageFrontPath == null) {
            return "car/add-car/add-car"; // Upload thất bại
        } else {
            carUpdateDetailDTO.setImageFront(imageFrontPath);
        }

        String imageBackPath = imageUploadUtil.uploadImage(imageBackFile, oldcar.getImageBack(), model, bindingResult);
        if (imageBackPath == null) {
            return "car/add-car/add-car"; // Upload thất bại
        } else {
            carUpdateDetailDTO.setImageBack(imageBackPath);
        }

        String imageLeftPath = imageUploadUtil.uploadImage(imageLeftFile, oldcar.getImageLeft(), model, bindingResult);
        if (imageLeftPath == null) {
            return "car/add-car/add-car"; // Upload thất bại
        } else {
            carUpdateDetailDTO.setImageLeft(imageLeftPath);
        }

        String imageRightPath = imageUploadUtil.uploadImage(imageRightFile, oldcar.getImageRight(), model, bindingResult);
        if (imageRightPath == null) {
            return "car/add-car/add-car"; // Upload thất bại
        } else {
            carUpdateDetailDTO.setImageRight(imageRightPath);
        }

        var result = carService.update(id, carUpdateDetailDTO);
        if (result == null) {
            var errorMessage = new Message("error", "Failed to update car");
            model.addAttribute("message", errorMessage);
            return "car/edit-car";
        }
        var successMessage = new Message("success", "Car updated successfully");
        redirectAttributes.addFlashAttribute("message", successMessage);
        return "redirect:/car/list";
    }

    @PostMapping("/edit-pricing/{id}")
    public String edit(@PathVariable UUID id,
                       @ModelAttribute @Valid CarUpdatePricingDTO carUpdatePricingDTO,
                       RedirectAttributes redirectAttributes,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("carUpdatePricingDTO", carUpdatePricingDTO);
            return "car/edit-car";
        }

        var result = carService.update(id, carUpdatePricingDTO);
        if (result == null) {
            var errorMessage = new Message("error", "Failed to update car");
            model.addAttribute("message", errorMessage);
            return "car/edit-car";
        }
        var successMessage = new Message("success", "Car updated successfully");
        redirectAttributes.addFlashAttribute("message", successMessage);
        return "redirect:/car/list";
    }

    @PostMapping("/edit-status/{id}")
    public String edit(@PathVariable UUID id,
                       @ModelAttribute @Valid CarUpdateStatusDTO carUpdateStatusDTO,
                       RedirectAttributes redirectAttributes,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("carUpdateStatusDTO", carUpdateStatusDTO);
            return "car/edit-car";
        }
        var result = carService.update(id, carUpdateStatusDTO);
        if (result == null) {
            var errorMessage = new Message("error", "Failed to update car");
            model.addAttribute("message", errorMessage);
            return "car/edit-car";
        }
        var successMessage = new Message("success", "Car updated successfully");
        redirectAttributes.addFlashAttribute("message", successMessage);
        return "redirect:/car/list";
    }
    
   @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "0") int page,   // Trang hiện tại
            @RequestParam(defaultValue = "6") int size,  // Số bản ghi trên mỗi trang
            Model model) {
        
        // Lấy tên đăng nhập của người dùng hiện tại
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByUsername(currentUsername);
        
        // Kiểm tra nếu currentUser không null
        if (currentUser == null) {
            model.addAttribute("error", "User not found");
            return "home/error";
        }

        // Thiết lập phân trang (không sắp xếp)
        PageRequest pageable = PageRequest.of(page, size);

        // Lấy danh sách xe của người dùng phân trang
        Page<Car> cars = carRepository.findByUser(currentUser, pageable);

        long bookedCount = carRepository.countByStatus("Booked");
        model.addAttribute("bookedCount", bookedCount);

        // Thêm các thuộc tính vào model
        model.addAttribute("cars", cars);
        model.addAttribute("totalPages", cars.getTotalPages());
        model.addAttribute("totalElements", cars.getTotalElements());
        model.addAttribute("pageLimit", 3);  // Giới hạn số trang hiển thị
        model.addAttribute("page", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("pageSizes", new Integer[]{6, 12, 18, 24, 30});

        return "car/list";
    }


    @PostMapping("/listconfirm")
    public String confirm(@ModelAttribute CarUpdateStatusDTO carUpdateStatusDTO, RedirectAttributes redirectAttributes) {
        UUID carId;
        
        try {
            carId = UUID.fromString(carUpdateStatusDTO.getId().toString());
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("message", new Message("error", "Invalid car ID format."));
            return "redirect:/car/list";  // Redirect to the GET /list if UUID format is invalid
        }
        
        // Tiếp tục với logic cập nhật nếu ID hợp lệ
        var result = carService.update(carId, carUpdateStatusDTO);
        
        if (result == null) {
            redirectAttributes.addFlashAttribute("message", new Message("error", "Failed to update car"));
            return "redirect:/car/list";  // Redirect to the GET /list
        }
        
        redirectAttributes.addFlashAttribute("message", new Message("success", "Car updated successfully"));
        return "redirect:/car/list";  // Redirect to the GET /list
    }
}

