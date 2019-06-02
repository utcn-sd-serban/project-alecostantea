import userModel from "../model/UserModel";

class LoginPresenter {
    onLoginBarChange(property, value) {
        userModel.changeCurrentUserProperty(property, value);
    }

    login() {
        userModel.login().then(() => {
             if (userModel.state.userType === "caretaker") {
                console.log("change to caretaker");
                userModel.loadPetsToCare();
                window.location.assign("#/caretaker");
            } else if (userModel.state.userType === "user") {
                console.log("change to user");
                userModel.loadPets();
                userModel.loadBookings();
                window.location.assign("#/user");
            }else {
                userModel.changeCurrentUserProperty("username", userModel.state.userType);

            }
        });
        console.log("logged in")
    }

    logOut(){
        userModel.changeNewPetProperty("petName","");
        userModel.changeNewPetProperty("petType","");
        userModel.changeCurrentUserProperty("username","");
        userModel.changeCurrentUserProperty("password","");
        window.location.assign("#/");
    
        
    
       }
}

const loginPresenter = new LoginPresenter();
export default loginPresenter