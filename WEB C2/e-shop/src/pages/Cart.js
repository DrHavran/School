import Total from "../parts/Total"
import CartItem from "../parts/CartItem"
import {useState, useEffect} from "react";

function Cart () {
    const [list, setItems] = useState([]);

    useEffect(() => {
        fetchCart();
      }, []);
    
      function fetchCart() {
        fetch("http://localhost:4000/api/cart")
          .then(res => res.json())
          .then(data => setItems(data));
      }
    
      function remove(id) {
        fetch("http://localhost:4000/api/delete", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ id }),
        })
        .then(() => fetchCart());
      }

    return (
        <div className="d-flex flex-column align-items-center">
            <h1 className="my-4">Cart</h1>
            <p>This is what you have in your cart:</p>
            {list.map((item) => (
                    <CartItem key={item.id} Name={item.name} Amount={item.amount} id={item.id} Img={item.img} Remove={remove}/>
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