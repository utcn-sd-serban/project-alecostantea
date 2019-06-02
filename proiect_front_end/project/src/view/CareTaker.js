import React from "react"

const CareTaker = ({ currentUser, carePet, bookings }) => (
    <div>
        <h1 className="title is-primary">
            {currentUser.username}
        </h1>

        <br />

        <table className="table">
            <thead>
                <tr>
                    <th>PetName</th>
                    <th>roomId</th>
                </tr>
            </thead>

            <tbody>
                {bookings === [] ? null : bookings.map((booking) => (

                    <tr>
                        <td>{booking.petName}</td>
                        <td>{booking.roomId}</td>
                        <td>
                            <button className="button" onClick={() => carePet(booking.id, booking.username, booking.petName)}>
                                love
                            </button>
                        </td>
                    </tr>

                ))}
            </tbody>
        </table>
    </div>
)

export default CareTaker;