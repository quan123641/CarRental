document.addEventListener('DOMContentLoaded', function () {
    const menuToggle = document.getElementById("show-menu");
    const header = document.querySelector('header');
    const menu = document.querySelector('.mini-action-123');
    const logoutPopup = document.getElementById('logout-popup');
    const confirmLogout = document.getElementById('confirm-logout');
    const cancelLogout = document.getElementById('cancel-logout');

    if (menuToggle && header && menu) {
        menuToggle.addEventListener('click', function () {
            menu.classList.toggle('show-menu');
            document.body.classList.toggle('overlay-active');
        });

        document.addEventListener('click', function (event) {
            if (!menu.contains(event.target) && !menuToggle.contains(event.target)) {
                menu.classList.remove('show-menu');
                document.body.classList.remove('overlay-active');
            }
        });

        // Hiển thị popup khi click vào Logout
        menu.addEventListener('click', function (event) {
            if (event.target.closest('#logout')) {
                event.preventDefault(); // Ngăn chặn hành động mặc định
                logoutPopup.style.display = 'flex'; // Hiện popup
            }
        });

        // Xử lý xác nhận logout
        confirmLogout.addEventListener('click', function () {
            logoutPopup.style.display = 'none'; // Ẩn popup
            window.location.href = '/logout'; // Điều hướng đến URL logout
        });

        // Hủy bỏ logout
        cancelLogout.addEventListener('click', function () {
            logoutPopup.style.display = 'none'; // Ẩn popup
        });
    }
});
