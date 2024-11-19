const array = [
    {
        id: 1,
        icon: '<i class="fa-solid fa-dollar-sign fs-2"></i>',
        title: "How the insurance works",
        description:
            "From the minute you hand the keys over till the second you get them back you are covered. Your private insurance is not affected",
    },
    {
        id: 2,
        icon: '<i class="fa-solid fa-shield-halved fs-2"></i>',
        title: "It's completely free",
        description:
            "We offer both owners renters free sign ups. It's only once a vehicle is rented out that a share is deducted to cover admin and insurance",
    },
    {
        id: 3,
        icon: '<i class="fa-regular fa-circle-check fs-2"></i>',
        title: "You decide the price",
        description:
            "When you list a car you decide the price. We can help with recommendations as to price, but ultimately you decide!",
    },
    {
        id: 4,
        icon: '<i class="fa-solid fa-car fs-2"></i>',
        title: "Handling over your vehicle",
        description:
            "You arrange the time and location for the exchange of your vehicle with the renter. Both parties will need to agree and sign the vehicle rental sheet before and after key handover.",
    },
    {
        id: 5,
        icon: '<i class="fa-solid fa-triangle-exclamation fs-2"></i>',
        title: "You are in charge",
        description:
            "All renters are pre-screened by us to ensure safety and get your approval. If you do not feel comfortable with someone you are able to decline a booking",
    },
    {
        id: 6,
        icon: '<i class="fa-regular fa-credit-card fs-2"></i>',
        title: "Set payment",
        description:
            "We pay you once a month and you can always view how much your car has earned under your user profile.",
    },
];

const cardContainer = document.getElementById("cardContainer");

array.forEach((item) => {
    const card = document.createElement("div");
    card.className = "text-center d-flex flex-column bg-body-secondary";
    card.innerHTML = `
        <div class="card-body text-start my-4 ">
            <div class="d-flex mb-2">
                <p class="card-icon">${item.icon}</p>
                <h4 class="card-title">${item.title}</h4>
            </div>
            <p class="card-text text-dark">${item.description}</p>
        </div>
    `;
    cardContainer.appendChild(card);
});