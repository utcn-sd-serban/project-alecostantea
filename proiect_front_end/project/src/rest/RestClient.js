const BASE_URL = "http://localhost:8080";

export default class RestClient{
    constructor(username, password){
        this.authorization = "Basic " + btoa(username, + ":" + password);
    }

    login(username,password){
        return fetch(BASE_URL + "/login",{
            method: "PUT",
            body: JSON.stringify({
                username:username,
                password: password
            }),
            headers:{
                'Content-Type' : 'application/json',
                // "Authorization":this.authorization
            }
        }).then(response => {
            if(!response.ok) return false;
            else return response.json();
        })
    }

    getPets(username,password){
        return fetch(BASE_URL + "/user/pets",{
            method: "PUT",
            body: JSON.stringify({
                username:username,
                password: password
            }),
            headers:{
                'Content-Type': 'application/json'
            }
        }).then(response => {
            if(!response.ok) return false;
            else return response.json();
        })
    }

    addPet(username, petName,petType){
        return fetch(BASE_URL + "/user/pet", {
            method: "POST",
            body: JSON.stringify({
                username:username,
                petName: petName,
                petType: petType
            }),
            headers:{
                'Content-Type': 'application/json'
            }
        }).then(response => {
            if(!response.ok) return false;
            else return response.json();
        })
    }
}