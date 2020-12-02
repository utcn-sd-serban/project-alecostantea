import React from "react"

const User = ({ currentUser, onChange, pets, newPet, addPet, bookPet, bookings, checkout }) => (
    <div>
        <h1 className="title is-primary">
            {currentUser.username}
        </h1>
        <label>
            Pet name:
        </label>
        <input className="input" value={newPet.petName} onChange={e => onChange("petName", e.target.value)}></input>
        <br />
        <label>Pet type:</label>
        <input className="input" value={newPet.petType} onChange={e => onChange("petType", e.target.value)}></input>
        <br />
        <button className="button" onClick={addPet}> Add pet</button>
        <button className="button" onClick={bookPet}> book pet</button>
        <table className="table">
            <thead>
                <tr>
                    <th>PetName</th>
                    <th>Pet Type</th>
                </tr>
            </thead>

            <tbody>
                {pets === [] ? null : pets.map((pet, index) => (
                    <tr key={index}>
                        <td>{pet.petName}</td>
                        <td>{pet.petType}</td>
                        
                    </tr>
                ))}
            </tbody>
        </table>
        <h1>
            Bookings
        </h1>
        <table className="table">
            <thead>
                <tr>
                    <th>PetName</th>
                    <th>room</th>
                    <th>price</th>
                </tr>
            </thead>

            <tbody>
                {bookings === [] ? null : bookings.map((booking) => (
                    <tr key={booking.id}>
                        <td>{booking.petName}</td>
                        <td>{booking.roomId}</td>
                        <td>{booking.price}</td>
                        <td>

                            <button className="button" onClick={() => checkout(booking.id,booking.username,booking.petName)}> checkout pet</button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>



    </div>
)

export default User;