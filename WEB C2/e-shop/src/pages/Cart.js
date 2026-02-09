import Total from "../parts/Total"
import CartItem from "../parts/CartItem"

function Cart ({list, Remove}) {
    return (
        <div className="d-flex flex-column align-items-center">
            <h1 className="my-4">Cart</h1>
            <p>This is what you have in your cart:</p>
            {list.map((item) => (
                    <CartItem key={item.id} Name={item.name} Amount={item.amount} Id={item.id} Img={item.img} Remove={Remove}/>
            ))}
            <Total list={list} />
            {list.length !== 0 ? (
                <button className="btn btn-success" onClick={() => alert("Payed, expect your order to come in 10 years")}> Pay </button>) : (
                <button className="btn btn-success disabled" disabled onClick={() => alert("Payed, expect your order to come in 10 years")}> Pay </button>
            )}
        </div>
    )
}

export default Cart;