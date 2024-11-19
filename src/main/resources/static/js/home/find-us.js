const array = [
    {
        id: 1,
        city: "Hanoi",
        quantity: 10,
        image: "https://scontent.ccdn.cloud/image/vivitravels-en/bafa9061-1e02-4a73-ac93-410783f5b6df/maxw-960.jpg",
    },
    {
        id: 2,
        city: "Ho Chi Minh city",
        quantity: 100,
        image: "https://images.pexels.com/photos/941195/pexels-photo-941195.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    },
    {
        id: 3,
        city: "Da Nang - Hoi An",
        quantity: 90,
        image: "https://statics.vinwonders.com/da-nang-to-hoi-an-01_1669732079.jpg",
    },
    {
        id: 4,
        city: "Nha Trang",
        quantity: 50,
        image: "https://vietnamdiscovery.com/wp-content/uploads/2020/05/Hon-Tre-Island-in-Nha-Trang-agoda.net_.jpg",
    },
    {
        id: 5,
        city: "Da Lat",
        quantity: 80,
        image: "https://th.bing.com/th/id/R.6860dd430656fbf17503f8609ea68291?rik=N8SA4%2bPp3Y2s6A&riu=http%3a%2f%2ffarm6.staticflickr.com%2f5538%2f9260455196_ef23c16799_b.jpg&ehk=dqiV11b%2bB7iFJtJHfERbX9%2f85MH71DhQBEKjxanmcig%3d&risl=&pid=ImgRaw&r=0",
    },
    {
        id: 6,
        city: "Quang Ninh",
        quantity: 70,
        image: "https://th.bing.com/th/id/R.4961b76c0af052d9b00c695eb5a5b185?rik=%2fzwWFDSRgAuEHA&riu=http%3a%2f%2fvatlieuxaydung.org.vn%2fUpload%2f48%2fNam_2016%2fCongboGiaVLXD%2fVLXD.org_Gia_VLXD_Quang_Ninh.jpeg&ehk=5ZMoBxhLN%2b%2fjW6luzwGUXhT9G1r%2bh3q1lNq7T4YqFfg%3d&risl=&pid=ImgRaw&r=0",
    },
];

export function getFindUs() {
    const container = document.getElementById("find-us-row");

    // Generate HTML for each item
    array.forEach((item) => {
        const col = document.createElement("div");
        col.className = "col-md-4 p-2";

        const cardContainer = document.createElement("div");
        cardContainer.className = "card-container";

        const imageContainer = document.createElement("div");
        imageContainer.className = "image-container";

        const img = document.createElement("img");
        img.src = item.image;
        img.className = "img-thumbnail";
        img.alt = item.city;

        const overlay = document.createElement("div");
        overlay.className = "overlay";

        const overlayText = document.createElement("h5");
        overlayText.className = "overlay-text";
        overlayText.textContent = item.city;

        const cardText = document.createElement("p");
        cardText.className = "card-text";
        cardText.textContent = `${item.quantity} + cars`;

        overlay.appendChild(overlayText);
        overlay.appendChild(cardText);
        imageContainer.appendChild(img);
        imageContainer.appendChild(overlay);
        cardContainer.appendChild(imageContainer);
        col.appendChild(cardContainer);
        container.appendChild(col);
    });
}