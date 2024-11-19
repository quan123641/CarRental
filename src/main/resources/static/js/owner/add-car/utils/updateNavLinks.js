export function updateNavLinks(currentStepId, nextStepId) {
    const currentNavLink = document.querySelector(`a[href="#${currentStepId}"]`);
    const nextNavLink = document.querySelector(`a[href="#${nextStepId}"]`);
    // Đánh dấu bước hiện tại là đã hoàn thành
    if (currentNavLink) {
        currentNavLink.classList.remove('active');
        currentNavLink.classList.add('completed-step');
    }

    // Đánh dấu bước tiếp theo là bước hiện tại
    if (nextNavLink) {
        nextNavLink.classList.add('active');
    }

    // Đặt lại màu cho các nav-link không phải là bước hiện tại hoặc đã hoàn thành
    const allNavLinks = document.querySelectorAll('.nav-link');
    allNavLinks.forEach(navLink => {
        if (navLink !== nextNavLink && navLink !== currentNavLink) {
            navLink.classList.remove('active', 'completed-step');
        }
    });
}