import List from "../parts/List"

function Expenses () {
    return (
        <div className="d-flex flex-column align-items-center">
            <h1 className="my-4" >Expenses</h1>
            <List />
        </div>
    )
}

export default Expenses;