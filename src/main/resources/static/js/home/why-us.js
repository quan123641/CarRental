const array = [
    {
        id: 1,
        title: "Save money",
        description:
            "We have no setup or registration fees. You are only charged when you rent a car. So get started for FREE!",
        i: "fa-regular fa-money-bill-1",
    },
    {
        id: 2,
        title: "Convenient",
        description:
            "We have a large selection of privately owned cars to suit your needs throughout the country",
        i: "fa-solid fa-person",
    },
    {
        id: 3,
        title: "Legal and insurance",
        description:
            "We fully cover all rentals and even provide roadside assistance. Our rating system and extended member profile checks provide safety.",
        i: "fa-solid fa-hammer",
    },
    {
        id: 4,
        title: "24/7 support",
        description:
            "Our team is ready to support you all along the way with our 24/7 hotline and services.",
        i: "fa-solid fa-headphones",
    },
];
export function getWhyUs() {
    const container = document.getElementById("why-us-container");

    array.forEach((item) => {
        const card = document.createElement("div");
        card.className = " card col d-flex flex-column align-items-center";

        card.innerHTML = `
            <div class=" why-us-container d-flex justify-content-center align-items-center">
                <i class="${item.i} fs-1 border border-2 rounded-circle text-center d-flex justify-content-center align-items-center"></i>
            </div>
            <div class="card-body text-start my-4">
                <h3 class="card-title">${item.title}</h3>
                <p class="card-text text-dark">${item.description}</p>
            </div>
        `;

        container.appendChild(card);
    });
}