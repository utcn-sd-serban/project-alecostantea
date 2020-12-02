import userModel from "../model/UserModel";

class UserPresenter {
   onNewPetChange(property,value){
       userModel.changeNewPetProperty(property,value);
   }

   addPet(){
       userModel.addPet();
   }
   logOut(){
    userModel.changeNewPetProperty("petName","");
    userModel.changeNewPetProperty("petType","");
    userModel.changeCurrentUserProperty("username","");
    userModel.changeCurrentUserProperty("password","");
    window.location.assign("#/");

    

   }

   checkout(id,username,petName){
       userModel.checkout(id,username,petName);
   }

   book(){
       userModel.book();
   }

   getBookings(){
       userModel.loadBookings();
   }
}

const userPresenter = new UserPresenter();
export default userPresenter