// Sample data
const items = [1, 2, 3, 4];
export function getPeopleSay() {
    const container = document.getElementById("people-say-container");

    items.forEach(() => {
        const card = document.createElement("div");
        card.className = "card mb-3 w-50 border border-0 bg-body-secondary";

        card.innerHTML = `
            <div class="row g-0">
                <div class="col-md-4">
                    <i class="fa-solid fa-user w-100 fs-1 text-center d-flex justify-content-center align-items-center h-100"></i>
                </div>
                <div class="col-md-8">
                    <div class="card-body d-flex flex-column gap-1">
                        <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
                            <div class="progress-bar w-25 bg-1"></div>
                        </div>
                        <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">
                            <div class="progress-bar w-75 bg-1"></div>
                        </div>
                        <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100">
                            <div class="progress-bar w-100 bg-1"></div>
                        </div>
                        <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">
                            <div class="progress-bar w-75 bg-1"></div>
                        </div>
                    </div>
                </div>
            </div>
        `;

        container.appendChild(card);
    });
}