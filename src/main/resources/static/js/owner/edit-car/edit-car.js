document.addEventListener('DOMContentLoaded', () => {
    let activeTab = 'basicinformation';
    const tabs = document.querySelectorAll('.edit-car-tabs button');

    const setActiveTab = (tab) => {
        activeTab = tab;

        // Cập nhật lớp active
        tabs.forEach(button => {
            button.classList.remove('active'); // Xóa lớp active khỏi tất cả các nút
        });
        const activeButton = Array.from(tabs).find(button => button.dataset.tab === activeTab);
        if (activeButton) {
            activeButton.classList.add('active'); // Thêm lớp active vào nút hiện tại
        }

        // Ẩn/hiện nội dung
        const contents = document.querySelectorAll('.tab-content');
        contents.forEach(content => {
            content.style.display = 'none'; // Ẩn tất cả các nội dung
        });
        document.querySelector(`.${activeTab}`).style.display = 'block'; // Hiện nội dung tương ứng
    };

    // Initial render

    // Gán sự kiện cho các nút
    tabs.forEach(button => {
        button.addEventListener('click', () => {
            setActiveTab(button.dataset.tab);
        });
    });


    function setupFileInputPreview(fileInputId, imagePreviewId, containerId) {
        const fileInput = document.getElementById(fileInputId);
        const imagePreview = document.getElementById(imagePreviewId);
        const fileUploadDiv = fileInput.closest('.file-upload');
        const imageContainer = document.getElementById(containerId);

        // Thêm sự kiện onchange cho input file
        fileInput.addEventListener('change', function (event) {
            const file = event.target.files[0]; // Lấy file đầu tiên

            if (file) {
                const reader = new FileReader(); // Tạo FileReader

                // Khi file được đọc
                reader.onload = function (e) {
                    imagePreview.src = e.target.result;
                    imageContainer.style.display = 'block';
                    fileUploadDiv.style.display = 'none';
                };

                reader.readAsDataURL(file); // Đọc file dưới dạng URL
            }
        });
    }

    setupFileInputPreview('file-upload-front', 'image-preview-front', 'front-image-container');
    setupFileInputPreview('file-upload-back', 'image-preview-back', 'back-image-container');
    setupFileInputPreview('file-upload-left', 'image-preview-left', 'left-image-container');
    setupFileInputPreview('file-upload-right', 'image-preview-right', 'right-image-container');
});
