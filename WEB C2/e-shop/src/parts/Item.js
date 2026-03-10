function add(id) {
    fetch("http://localhost:4000/api/addToCart", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ id: id }),
      })
}

function Item ({Name, Amount, Img, id,}) {
    return (
        <div className="border border-primary rounded-3 p-3 mb-3 mt-3 d-flex align-items-center justify-content-between" style={{ width: "200%" }}>
            <div>
                <h3>{Name}</h3>
                <p>{Amount} czk</p>
            </div>
            <img src={Img} width={"100px"} height={"100px"} alt="Product"></img>
            <button className="btn btn-success" onClick={() => add(id)}>Add to cart</button>
        </div>
    )
}

export default Item;