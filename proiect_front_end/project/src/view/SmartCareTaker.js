import React, { Component } from "react";
import CareTaker from "./CareTaker.js"
import userModel from "../model/UserModel.js";
import careTakerPresenter from "../presenter/CareTakerPresenter.js";
const mapModelStateToComponentState = (modelState) => ({
    currentUser: modelState.currentUser,
    bookings: modelState.bookings,
    newPet: modelState.newPet

})

export default class SmartCareTaker extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(userModel.state);

        this.listener = userModelState => this.setState(mapModelStateToComponentState(userModelState));
        userModel.addListener("care_change", this.listener);
    }

    componentWillUnmount() {
        userModel.removeAllListeners("care_change", this.listener);
    }

    componentDidMount(){
        if(this.state.currentUser.username === "")
        {
            careTakerPresenter.logOut();
        }
    }

    render() {
        return (
            <CareTaker currentUser={this.state.currentUser}
                onChange={careTakerPresenter.onNewPetChange}
                newPet={this.state.newPet}
                bookings={this.state.bookings}
                carePet={careTakerPresenter.carePet}
            />
        )
    }
}