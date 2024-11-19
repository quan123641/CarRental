import {updateNavLinks} from "./utils/updateNavLinks.js";
import {showFinish} from "./utils/showFinish.js";
import {resetNavLinks} from "./utils/resetNavLinks.js";
import {triggerFileInput} from "./utils/triggerFileInput.js";
import {showPreview} from "./utils/showPreview.js";

document.addEventListener('DOMContentLoaded', function () {
    // Lấy các nút
    const cancelButtons = document.querySelectorAll('.cancelBtn');
    const nextButtons = document.querySelectorAll('.nextBtn');

    // Nút Cancel quay về bước đầu tiên
    cancelButtons.forEach((button) => {
        button.addEventListener('click', function () {
            // Ẩn tất cả các bước
            const allSteps = document.querySelectorAll('.tab-pane');
            allSteps.forEach(step => {
                step.classList.remove('show', 'active', 'active-step', 'inactive-step');
            });

            // Hiện bước đầu tiên
            const firstStep = document.getElementById('step1');
            firstStep.classList.add('show', 'active', 'active-step');

            resetNavLinks();
        });
    });

    // Nút Next chuyển đến bước tiếp theo
    nextButtons.forEach((button) => {
        button.addEventListener('click', function () {
            const currentStep = document.querySelector('.tab-pane.active');
            const nextStep = currentStep.nextElementSibling;

            if (nextStep) { // Kiểm tra xem có bước tiếp theo không
                currentStep.classList.remove('show', 'active', 'active-step');
                nextStep.classList.add('show', 'active', 'active-step');

                // Cập nhật thông điệp
                const stepNumber = nextStep.id.replace('step', '');
                if (stepNumber === '4') {
                    showFinish()
                }
                // Cập nhật màu cho nav-link
                updateNavLinks(currentStep.id, nextStep.id);
            }

            // Thêm lớp inactive-step cho các bước không hoạt động
            const allSteps = document.querySelectorAll('.tab-pane');
            allSteps.forEach(step => {
                if (!step.classList.contains('active')) {
                    step.classList.add('inactive-step');
                }
            });
        });
    });


    document.getElementById("finish-step").addEventListener("click", function () {
        showFinish()
    })

    document.getElementById("container-registration").addEventListener("click", function () {
        triggerFileInput("registrationPaperFile");
    });
    document.getElementById("container-inspection").addEventListener("click", function () {
        triggerFileInput("inspection");
    });
    document.getElementById("container-insurance").addEventListener("click", function () {
        triggerFileInput("insurance");
    });
    document.getElementById("front-image-container").addEventListener("click", function () {
        triggerFileInput("file-upload-front")
    })
    document.getElementById("back-image-container").addEventListener("click", function () {
        triggerFileInput("file-upload-back")
    })
    document.getElementById("left-image-container").addEventListener("click", function () {
        triggerFileInput("file-upload-left")
    })
    document.getElementById("right-image-container").addEventListener("click", function () {
        triggerFileInput("file-upload-right")
    })
// Gán sự kiện change cho input file
    document.getElementById("registrationPaperFile").addEventListener("change", function () {
        showPreview("registrationPaperFile", "registration-preview", "container-registration");
    });
    document.getElementById("inspection").addEventListener("change", function () {
        showPreview("inspection", "inspection-preview", "container-inspection");
    });
    document.getElementById("insurance").addEventListener("change", function () {
        showPreview("insurance", "insurance-preview", "container-insurance");
    });

    document.getElementById("file-upload-front").addEventListener("change", function () {
        showPreview("file-upload-front", "front-image-preview", "front-image-container");

    });
    document.getElementById("file-upload-back").addEventListener("change", function () {
        showPreview("file-upload-back", "back-image-preview", "back-image-container");


    });
    document.getElementById("file-upload-left").addEventListener("change", function () {
        showPreview("file-upload-left", "left-image-preview", "left-image-container");
    });
    document.getElementById("file-upload-right").addEventListener("change", function () {
        showPreview("file-upload-right", "right-image-preview", "right-image-container");
    });

});
