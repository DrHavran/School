import List from "../parts/List"

function Catalog () {
    return (
        <div className="d-flex flex-column align-items-center">
            <h1 className="my-4">Catalog</h1>
            <p>Here you can find our great products:</p>
            <List/>
        </div>
    )
}

export default Catalog;