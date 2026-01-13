function Total ({list}) {
    let count = 0;
    list.map((expense) => (
        count += Number(expense.amount)
    ))

    console.log(count)

    if(list.length != 0){
        return (
            <div className="border border-primary rounded-3 p-3 mb-3 mt-3 shadow-sm row">
                <p>The total is: <b>{count}</b> 💲</p>
            </div>
        )
    }
}

export default Total;