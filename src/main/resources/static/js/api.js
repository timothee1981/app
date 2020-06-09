class API {
    constructor() {
    }

    fetch(url, header) {
        return fetch(url, {
            method: 'GET',
            headers : header
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Response error");
                }
                return response.json();
            })
            .then((data) => {
                return data;
            })
            .catch(() => {
            });
    }

    isUnique(url) {
        return this.fetch(url);
    }

    cityAddress(){
        const header = new Headers;
        const TOKEN = "ccf855f3-4bd0-4cd6-8f12-25c9e254efd2";
        header.append('Authorization', 'Bearer ' + TOKEN);
        let url = `https://postcode.tech/api/v1/postcode?postcode=${document.getElementById("postalCode").value}&number=${document.getElementById("houseNumber").value}`;

        return this.fetch(url, header);
    }
}