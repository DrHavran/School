function Item ({Name, Amount, Img, id, Remove}) {
    return (
        <div className="border border-primary rounded-3 p-3 mb-3 mt-3 d-flex align-items-center justify-content-between" style={{ width: "30%" }}>
            <div>
                <h3>{Name}</h3>
                <p>{Amount} czk</p>
            </div>
            <img src={Img} width={"100px"} height={"100px"} alt="Produt"></img>
            <button className="btn btn-danger" onClick={() => Remove(id)}>Remove from cart</button>
        </div>
    )
}

export default Item;