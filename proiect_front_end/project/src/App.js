import React from 'react';
import './App.css';
import SmartLogin from './view/SmartLogin';
import {HashRouter, Switch, Route} from "react-router-dom"; 
import SmartCareTaker from './view/SmartCareTaker';
import SmartUser from './view/SmartUser';
import loginPresenter from './presenter/LoginPresenter';
const App = () => (
  <div className="App">
    <HashRouter>
      <Switch>
        <Route exact={true} component={SmartLogin} path="/"></Route>
        <Route exact={true} component={SmartCareTaker} path="/caretaker"/>
        <Route exact={true} component={SmartUser} path="/user"/>
      </Switch>
    </HashRouter>
    <button className="button is-primary" onClick={loginPresenter.logOut}> log out</button>
  </div>
);



export default App;
