export function resetNavLinks() {
    const allNavLinks = document.querySelectorAll('.nav-link');
    allNavLinks.forEach(navLink => {
        navLink.classList.remove('active', 'completed-step');
    });

    const firstNavLink = document.querySelector('a[href="#step1"]');
    if (firstNavLink) {
        firstNavLink.classList.add('active');
    }
}