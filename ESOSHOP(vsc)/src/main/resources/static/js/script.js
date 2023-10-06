var counter = 1;
setInterval(function () {
    document.getElementById('radio' + counter).checked = true;
    counter++;
    if (counter > 4) {
        counter = 1;
    }
}, 7000)






const stars = document.querySelectorAll('.star');
const ratingInput = document.getElementById('rating');
const ratingForm = document.getElementById('ratingForm');

stars.forEach((star, index) => {
    star.addEventListener('click', () => {
        const selectedValue = index + 1;
        ratingInput.value = selectedValue;

        // Làm vàng tất cả các ngôi sao đứng trước ngôi sao được chọn
        stars.forEach((s, i) => {
            if (i <= index) {
                s.classList.add('selected');
            } else {
                s.classList.remove('selected');
            }
        });
    });
});

 ratingForm.addEventListener('submit', (event) => {
    event.preventDefault();
    // Điều này sẽ gửi giá trị đánh giá đến server.
});
