function convertCurrency() {
    const amount = document.getElementById("amount").value;
    const fromCurrency = document.getElementById("from").value.toUpperCase();
    const toCurrency = document.getElementById("to").value.toUpperCase();

    const apiUrl = `http://localhost:8080/convert?amount=${amount}&from=${fromCurrency}&to=${toCurrency}`;

    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            const resultElement = document.getElementById("result");
            resultElement.textContent = `Converted amount: ${data.convertedAmount}`;
        })
        .catch(error => {
            console.error('Error:', error);
            const resultElement = document.getElementById("result");
            resultElement.textContent = 'An error occurred. Please try again.';
        });
}
