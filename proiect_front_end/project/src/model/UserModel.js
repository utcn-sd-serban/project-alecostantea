import { EventEmitter } from "events";
import RestClient from "../rest/RestClient";

class UserModel extends EventEmitter {
    constructor() {
        super();
        this.state = {
            currentUser: {
                username: "",
                password: ""
            },
            userType : false,
            pets: [],
            newPet: {
                petName: "",
                petType: "",
            },
            bookings:[]

        };
    }

    changeCurrentUserProperty(property, value) {
        this.state = {
            ...this.state,
            currentUser: {
                ...this.state.currentUser,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }

    changeNewPetProperty(property,value) {

        this.state = {
            ...this.state,
            newPet:{
                ...this.state.newPet,
                [property]: value
            }
        };
        // console.log("change pet: " + property + " " + value);
        // console.log(this.state.newPet);
        this.emit("user_change",this.state);
    }

    login() {
        const client = new RestClient(this.state.currentUser.username, this.state.currentUser.password);
        return client.login(this.state.currentUser.username, this.state.currentUser.password).then( response =>{
            this.state = {
                ...this.state,
                userType:response.response
            };
            // console.log("user type:" + this.state.userType)
            this.emit("change",this.state);
        })
        
    }

    loadPets(){
        const client = new RestClient(this.state.currentUser.username, this.state.currentUser.password);
        return client.getPets(this.state.currentUser.username,this.state.currentUser.password).then( pets => {
            this.state = {
                ...this.state,
                pets: pets
            };
            // console.log("loaded pets" + this.state.pets);
            this.emit("user_change", this.state);
        })
    }

    addPet(){
        const client = new RestClient(this.state.currentUser.username, this.state.currentUser.password);
        return client.addPet(this.state.currentUser.username,this.state.newPet.petName,this.state.newPet.petType).then( pet => {
           
            this.state = {
                ...this.state,
                pets: [pet].concat(...this.state.pets)
            }
            this.emit("user_change",this.state);
        })
    }

    loadBookings(){
        const client = new RestClient(this.state.currentUser.username, this.state.currentUser.password);
        return client.getBookings(this.state.currentUser.username,this.state.currentUser.password).then( bookings => {
            this.state = {
                ...this.state,
                bookings: bookings
            };
            // console.log("loaded pets" + this.state.pets);
            this.emit("user_change", this.state);
        })
    }

    book(){
        const client = new RestClient(this.state.currentUser.username, this.state.currentUser.password);
        return client.book(this.state.currentUser.username,this.state.newPet.petName,0).then( book => {
            this.state = {
                ...this.state,
                bookings:[book].concat(...this.state.bookings)
            };
            // console.log("loaded pets" + this.state.pets);
            this.emit("user_change", this.state);
        })
    }

    loadPetsToCare(){
        const client = new RestClient(this.state.currentUser.username, this.state.currentUser.password);
        return client.getPetsToCare(this.state.currentUser.username,this.state.currentUser.password).then( bookings => {
            this.state = {
                ...this.state,
                bookings:bookings
            };
            this.emit("care_change", this.state);
        })
    }

    carePet(id,username,petName){
        const client = new RestClient(this.state.currentUser.username, this.state.currentUser.password);
        return client.carePet(username,petName,10,id);
    }

    checkout(id,username,petName){
        const client = new RestClient(this.state.currentUser.username, this.state.currentUser.password);
        return client.checkout(id,username,petName).then( () => {
            const filteredBookings = this.state.bookings.filter(booking => {
               return booking.id !== id
            })
            this.state = {
                ...this.state,
                bookings: filteredBookings
            };
            this.emit("user_change",this.state);
        });
    }

}

const userModel = new UserModel()

export default userModel;