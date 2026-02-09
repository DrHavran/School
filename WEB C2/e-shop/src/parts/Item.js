function Item ({Name, Amount, Img, Id, Add}) {
    return (
        <div className="border border-primary rounded-3 p-3 mb-3 mt-3 d-flex align-items-center justify-content-between" style={{ width: "200%" }}>
            <div>
                <h3>{Name}</h3>
                <p>{Amount} czk</p>
            </div>
            <img src={Img} width={"100px"} height={"100px"}></img>
            <button className="btn btn-success" onClick={() => Add(Id)}>Add to cart</button>
        </div>
    )
}

export default Item;