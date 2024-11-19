document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("loginForm");
    const signupForm = document.getElementById("signupForm");
    const closeBtn = document.getElementById("closeBtn");
    const agreementCheckbox = document.getElementById("agreement");
    const submitButton = document.getElementById("sign-up-btn");
    const messageDiv = document.createElement("div");

    messageDiv.classList.add("text-danger", "mt-2");
    messageDiv.innerText = "Please read the Term and Conditions";

    // Hàm chuyển đến trang đăng nhập
    function navigateToLogin() {
        loginForm.classList.remove("inactive");
        loginForm.classList.add("active");
        signupForm.classList.remove("active");
        signupForm.classList.add("inactive");
    }

    // Hàm chuyển đến trang đăng ký
    function navigateToSignup() {
        signupForm.classList.remove("inactive");
        signupForm.classList.add("active");
        loginForm.classList.remove("active");
        loginForm.classList.add("inactive");
    }

    // Hàm chuyển về trang chính
    function navigateToHome() {
        window.location.href = "/";
    }

    // Gán sự kiện click cho nút chuyển đổi giữa các form
    loginForm.addEventListener("click", navigateToLogin);
    signupForm.addEventListener("click", navigateToSignup);
    closeBtn.addEventListener("click", navigateToHome);

    // Đặt trạng thái ban đầu cho form
    navigateToLogin();

    // Xử lý sự kiện khi form được submit
    signupForm.addEventListener("submit", function (event) {
        if (!agreementCheckbox.checked) {
            event.preventDefault(); // Ngăn submit form
            submitButton.disabled = true; // Disable button submit

            // Hiển thị thông báo
            if (!agreementCheckbox.parentElement.contains(messageDiv)) {
                agreementCheckbox.parentElement.appendChild(messageDiv);
            }
        }
    });

    // Xử lý sự kiện khi checkbox thay đổi
    agreementCheckbox.addEventListener("change", function () {
        if (agreementCheckbox.checked) {
            submitButton.disabled = false; // Kích hoạt nút submit
            if (agreementCheckbox.parentElement.contains(messageDiv)) {
                messageDiv.remove(); // Ẩn thông báo
            }
        } else {
            submitButton.disabled = true; // Disable nút submit
        }
    });
});
