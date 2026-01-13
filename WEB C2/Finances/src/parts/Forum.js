function Forum ({onSubmit}) {

    const handleSubmit = (e) => {
        e.preventDefault();

        const name = e.target.elements.name.value;
        const amount = e.target.elements.amount.value;

        onSubmit({name, amount})
    };  

    return (
        <form className="form d-flex justify-content-around border py-4" onSubmit={handleSubmit}>
            <input name="name" type="text" className="form-control w-75 mx-5" placeholder="Name of the expense" />
            <input name="amount" type="number" className="form-control w-75 mx-5" placeholder="How much?" min={1}/>
            <input type="submit" value="Přidat úkol" className="btn btn-success mx-5"/>
        </form>
    )
}

export default Forum;