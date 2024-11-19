export function showFinish() {
    const frontImg = document.getElementById("front-img")
    const frontImgInput = document.getElementById("file-upload-front");
    const frontImgFile = frontImgInput.files[0];
    if (frontImgFile) {
        frontImg.src = URL.createObjectURL(frontImgFile);
    }

    const backImg = document.getElementById("back-img")
    const backImgInput = document.getElementById("file-upload-back");
    const backImgFile = backImgInput.files[0];
    if (backImgFile) {
        backImg.src = URL.createObjectURL(backImgFile);
    }
    const leftImg = document.getElementById("left-img")
    const leftImgInput = document.getElementById("file-upload-left");
    const leftImgFile = leftImgInput.files[0];
    if (leftImgFile) {
        leftImg.src = URL.createObjectURL(leftImgFile);
    }
    const rightImg = document.getElementById("right-img")
    const rightImgInput = document.getElementById("file-upload-right");
    const rightImgFile = rightImgInput.files[0];
    if (rightImgFile) {
        rightImg.src = URL.createObjectURL(rightImgFile);
    }
    const brand = document.getElementById("brand").value
    const model = document.getElementById("model").value
    const year = document.getElementById("year").value

    document.getElementById("title-preview").innerHTML = brand + " " + model + " " + year
    document.getElementById("no-of-rides-preview").innerHTML = document.getElementById("seats").value
    document.getElementById("price-preview").innerHTML = document.getElementById("base-price").value
    document.getElementById("location-preview").innerHTML = document.getElementById("address").value
}
