import Expense from "./Expense"

function List ({list}) {
    if (list.length !== 0) {
        return (
            <div className="rounded-3 p-3">
                 {list.map((expense) => (
                    <Expense key={expense.id} Name={expense.name} Amount={expense.amount} Id={expense.id}/>
                ))}
            </div>
        )
    }else {
        return (<h3>Žádné úlohy k zobrazení.</h3>)
    }
}

export default List;