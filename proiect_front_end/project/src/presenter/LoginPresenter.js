import userModel from "../model/UserModel";

class LoginPresenter {
    onLoginBarChange(property, value) {
        userModel.changeCurrentUserProperty(property, value);
    }

    login() {
        userModel.login().then(() => {
             if (userModel.state.userType === "caretaker") {
                console.log("change to caretaker");
                window.location.assign("#/caretaker");
            } else if (userModel.state.userType === "user") {
                console.log("change to user");
                userModel.loadPets();
                window.location.assign("#/user");
            }else {
                userModel.changeCurrentUserProperty("username", userModel.state.userType);

            }
        });
        console.log("logged in")
    }
}

const loginPresenter = new LoginPresenter();
export default loginPresenter