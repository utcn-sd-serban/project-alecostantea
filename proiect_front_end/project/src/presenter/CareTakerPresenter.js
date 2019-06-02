import userModel from "../model/UserModel";

class CareTakerPresenter {
   onNewPetChange(property,value){
       userModel.changeNewPetProperty(property,value);
   }

   
   logOut(){
    userModel.changeNewPetProperty("petName","");
    userModel.changeNewPetProperty("petType","");
    userModel.changeCurrentUserProperty("username","");
    userModel.changeCurrentUserProperty("password","");
    window.location.assign("#/");

    

   }

   carePet(id,username,petName){
       userModel.carePet(id,username,petName);
   }
   
}

const careTakerPresenter = new CareTakerPresenter();
export default careTakerPresenter