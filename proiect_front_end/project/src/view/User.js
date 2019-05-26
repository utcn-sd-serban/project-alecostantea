import React from "react"

const User  = ({currentUser, onChange,pets, newPet}) => (
    <div>
        <h1 className="title is-primary">
            {currentUser.username}
        </h1>
        <label>
            Pet name:
        </label>
        <input className="input" value={newPet.petName} onChange={e => onChange("petName", e.target.value)}></input>
        <br/>
        <label>Pet type:</label>
        <input className="input" value={newPet.petType} onChange={e => onChange("petType", e.target.value)}></input>
        <br/>
        <table className="table">
            
                <th>PetName</th>
                <th>Pet Type</th>
            
            <tbody>
            {pets.map((pet) => (
                <tr>
                    <td>pet.name</td>
                    <td>pet.type</td>
                </tr>
            ))}
            </tbody>
        </table>




        
    </div>
)

export default User;