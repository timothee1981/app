class API {
    constructor(url) {
        this.url = url;
    }


    POST_CODE_API_TOKEN = "ccf855f3-4bd0-4cd6-8f12-25c9e254efd2";

    responseApi;

    fetch(url) {

        return fetch(url, {
            method: 'GET',
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Response error");
                }
                return response.json();
            })
            .then((data) => {
                this.responseApi = data;
                return this.responseApi
            })
            .catch((error) => {
                console.log(error);
            });
    }

    cityAddress(postCode, houseNumber){

            const header = new Headers();
            let url = `https://postcode.tech/api/v1/postcode?postcode=${postCode}&number=${houseNumber}`;
            header.append('Authorization', 'Bearer ' + this.POST_CODE_API_TOKEN);

            return fetch(url, {
                method: 'GET',
                headers: header,
            })
                .then((response) => {
                    if (!response.ok) {

                        throw new Error("Response error");
                    }
                    return response.json();
                })
                .then((data) => {
                    return data
                })
                .catch((error) => {
                    console.log(error);
                });
    }
}