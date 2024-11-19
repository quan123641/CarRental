export  function showPreview(inputId, previewId, oldContainerId) {
    const input = document.getElementById(inputId);
    const preview = document.createElement("p");
    const file = input.files[0];

    if (file) {
        const oldContainer = document.getElementById(oldContainerId);
        oldContainer.style.display = "none";
        const previewContainer = document.getElementById(previewId);
        if (file.type.startsWith("image/")) {
            // Nếu file là ảnh, tạo thẻ img để hiển thị ảnh
            const img = document.createElement("img");
            img.src = URL.createObjectURL(file);
            img.style.maxWidth = "100%";
            previewContainer.innerHTML = '';
            previewContainer.appendChild(img);
            previewContainer.style.display = "block";
        } else {
            preview.textContent = `Selected file: ${file.name}`;
            previewContainer.appendChild(preview);
        }
    }
}
