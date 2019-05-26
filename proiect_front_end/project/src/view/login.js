import React from "react"

const LoginComponent  = ({currentUser, onChange, onClick}) => (
    <div>
        <h1 className="title is-primary">
            Login
        </h1>
        <input value={currentUser.username} onChange={e =>  onChange("username",e.target.value)}>
        
        </input>
        <br></br>
        <input value={currentUser.password} onChange={e => onChange("password", e.target.value)}>
        
        </input>
        <br></br>
        <button className="button" onClick={onClick}> Login</button>
    </div>
)

export default LoginComponent;