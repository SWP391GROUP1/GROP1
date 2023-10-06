const checkboxes = document.querySelectorAll('.checkbox');
const quantities = document.querySelectorAll('.quantity');
const unitPrices = document.querySelectorAll('.unit-price');
const totalElement = document.getElementById('total');
const tmpElement = document.getElementById('tempTotal');
const shipElement = document.getElementById('shipTotal');
let atLeastOneChecked = false;
checkboxes.forEach((checkbox, index) => {
    checkbox.addEventListener('change', () => {
        updateShipElement();
        updateTotal();
    });
});

quantities.forEach((quantityInput, index) => {
    quantityInput.addEventListener('input', () => {
        updateTotal();
    });
});


function updateShipElement() {
    let atLeastOneChecked = false;

    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            atLeastOneChecked = true;
            break; // Nếu có ít nhất một checkbox đã được chọn, không cần kiểm tra tiếp
        }
    }

    if (atLeastOneChecked) {
        shipElement.textContent = '15000.00';
    } else {
        shipElement.textContent = '0.00';
    }
}

function updateTotal() {
    let tmpTotal = 0;
    let total = 0;
    let i = 0;
    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            const quantity = parseInt(quantities[i].value);
            const unitPrice = parseFloat(unitPrices[i].textContent);
            tmpTotal += quantity * unitPrice;

        }
    }



    tmpElement.textContent = ` ${tmpTotal.toFixed(2)}`;
    total = parseFloat(tmpElement.textContent) + parseFloat(shipElement.textContent);
    totalElement.textContent = ` ${total.toFixed(2)}`;}


    function updateProductDetails() {
        const productDetails = document.getElementById("productDetails");
        const checkboxes = document.querySelectorAll(".checkbox");
        let selectedProducts = [];

        checkboxes.forEach((checkbox, index) => {
            const productName = document.querySelectorAll(".product_name")[index].textContent;
            const quantityInput = document.querySelectorAll(".quantity")[index];
            const quantity = quantityInput.value;

            if (checkbox.checked) {
                selectedProducts.push({ name: productName, quantity: quantity });
            }
        });

        if (selectedProducts.length > 0) {
            productDetails.style.display = "block";
            productDetails.innerHTML = createProductTable(selectedProducts);
        } else {
            productDetails.style.display = "none";
        }
    }

    // Function to create an HTML table from the selected products
    function createProductTable(products) {
        let tableHTML = '<table><thead><tr><th>Product</th><th style="padding-left: 100px;">Quantity</th></tr></thead><tbody>';

        products.forEach(product => {
            if (product.name && product.quantity) {
                tableHTML += `<tr><td>${product.name}</td><td style="padding-left: 100px;">${product.quantity}</td></tr>`;
            }
        });

        tableHTML += '</tbody></table>';

        return tableHTML;
    }

    // Attach event listeners to checkboxes and quantity input fields

    checkboxes.forEach((checkbox) => {
        checkbox.addEventListener("change", updateProductDetails);
    });

    const quantityInputs = document.querySelectorAll(".quantity");
    quantityInputs.forEach((quantityInput) => {
        quantityInput.addEventListener("input", updateProductDetails);
    });

    // Initial call to set the initial state
    updateProductDetails();
