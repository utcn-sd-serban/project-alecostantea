import userModel from "../model/UserModel";

class UserPresenter {
   onNewPetChange(property,value){
       userModel.changeNewPetProperty(property,value);
   }
}

const userPresenter = new UserPresenter();
export default userPresenter