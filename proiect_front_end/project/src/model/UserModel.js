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
            newPet: {
                petName: "",
                petType: "",
            }

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
                ...this.addListener.state.newPet,
                [property]: value
            }
        };
        this.emit("change",this.state);
    }

    login() {
        const client = new RestClient(this.state.currentUser.username, this.state.currentUser.password);
        return client.login(this.state.currentUser.username, this.state.currentUser.password).then( response =>{
            this.state = {
                ...this.state,
                userType:response.response
            };
            console.log("user type:" + this.state.userType)
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
            console.log("loaded pets" + this.state.pets);
            this.emit("change", this.state);
        })
    }


}

const userModel = new UserModel()

export default userModel;