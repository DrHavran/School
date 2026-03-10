import express from "express"
import cors from "cors";

const app = express();
app.use(express.json());
app.use(cors());

let cart = [];

let listings = [
    {   
        id: 1,
        name: "C4",
        amount: "99",
        img: "https://www.shutterstock.com/image-photo/bomb-timer-on-white-background-600nw-1999656191.jpg"
    },
    {   
        id: 2,
        name: "Defuse kit",
        amount: "400",
        img: "https://purepng.com/public/uploads/large/purepng.com-hammerhammerscoldstrike-agairon-hammer-1701527869920y9pcy.png"
    }
]  

app.get("/api/listings", (request, response) => {
    response.json(listings);
});

app.get("/api/cart", (request, response) => {
    response.json(cart);
});

app.post("/api/addToCart", (req, res) => {
    let id = req.body.id;
    let item = { ...listings.find(i => i.id === id) };

    let newId = Math.max(...cart.map(item => item.id)) + 1;

    if(newId === -Infinity){
        item.id = 1;
    }else{
        item.id = newId;
    }

    cart.push(item)
    res.send("Added item")
});

app.post("/api/delete", (req, res) => {
    let id = req.body.id;
    cart = cart.filter(item => item.id !== id);
    res.send("Removed item")
});

app.listen("4000")