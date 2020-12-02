import React, { Component } from "react";
import User from "./User.js"
import userModel from "../model/UserModel.js";
import userPresenter from "../presenter/UserPresenter.js";
const mapModelStateToComponentState = (modelState) => ({
    currentUser: modelState.currentUser,
    pets: modelState.pets,
    newPet: modelState.newPet,
    bookings: modelState.bookings
})

export default class SmartUser extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(userModel.state);

        this.listener = userModelState => 
            this.setState(mapModelStateToComponentState(userModelState));
            
        userModel.addListener("user_change",this.listener);
    }

    componentDidMount(){
        if(this.state.currentUser.username === "")
        {
            userPresenter.logOut();
        }
    }

    componentWillUnmount() {
        userModel.removeAllListeners("user_change", this.listener);
    }

    render() {
        return (
            <User currentUser={this.state.currentUser}
                onChange={userPresenter.onNewPetChange}
                newPet={this.state.newPet}
                pets={this.state.pets}
                addPet={userPresenter.addPet}
                bookPet={userPresenter.book}
                bookings={this.state.bookings}
                checkout={userPresenter.checkout}
            />
        )
    }
}