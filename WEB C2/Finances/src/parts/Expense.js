function Expense ({Name, Amount, Delete, Id}) {
    return (
        <div className="border border-primary rounded-3 p-3 mb-3 mt-3 shadow-sm row" style={{ width: "200%" }}>
            <div className="col-9">
                <h3>{Name}</h3>
                <p>{Amount} czk</p>
            </div>
            <div className="col-3">
               <button className="btn btn-danger btn-lg" onClick={() => Delete(Id)} >🗑️</button>
            </div>
        </div>
    )
}

export default Expense;