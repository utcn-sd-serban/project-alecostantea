import React, { Component } from "react";
import CareTaker from "./CareTaker.js"
import userModel from "../model/UserModel.js";
import loginPresenter from "../presenter/LoginPresenter.js";
const mapModelStateToComponentState = (modelState) => ({
    currentUser: modelState.currentUser
})

export default class SmartCareTaker extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(userModel.state);

        this.listener = userModelState => this.setState(mapModelStateToComponentState(userModelState));
        userModel.addListener("change",this.listener);
    }

    componentWillUnmount() {
        userModel.removeAllListeners("change", this.listener);
    }

    render() {
        return (
            <CareTaker currentUser={this.state.currentUser}
                onChange={loginPresenter.onLoginBarChange}
            />
        )
    }
}