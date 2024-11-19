// Mở modal và đặt giá trị carId vào input ẩn
function openModal(carId) {
    document.getElementById("carIdInput").value = carId; // Gán carId vào input ẩn
    document.getElementById("confirmModal").style.display = "flex"; // Hiển thị modal
}

// Mở modal và đặt giá trị carId vào input ẩn cho ConfirmPayment
function openPaymentModal(carId) {
    document.getElementById("carIdInput").value = carId; // Gán carId vào input ẩn
    document.querySelector('input[name="status"]').value = 'AVAILABLE'; // Thiết lập giá trị trạng thái
    document.getElementById("confirmModal").style.display = "flex"; // Hiển thị modal
}

// Đóng modal
function closeModal() {
    document.getElementById("confirmModal").style.display = "none";
}

// Gửi form khi người dùng chọn "Có"
function submitForm() {
    console.log(document.getElementById("carIdInput").value);
    document.getElementById("confirmForm").submit();
}