import List from "../parts/List"
import Forum from "../parts/Forum"
import Total from "../parts/Total"
import {useState} from "react";

function Expenses () {
    const [expenses, setExpense] = useState(
        [
            {   
                id: 1,
                name: "Netflix",
                amount: "10000"
            },
            {   
                id: 2,
                name: "Hot dog",
                amount: "16.5"
            }
        ]   
    )

    const addExpense = (newExpense) => {
        let newId = Math.max(...expenses.map(expense => expense.id)) + 1;

        if(newId == -Infinity){
            newExpense.id = 1;
        }else{
            newExpense.id = newId;
        }

        setExpense([...expenses, newExpense]);
    }

    const removeExpense = (id) => {
        setExpense(expenses.filter((expense) => expense.id !== id));
    }

    return (
        <div className="d-flex flex-column align-items-center">
            <h1 className="my-4" >Expenses</h1>
            <Forum onSubmit={addExpense} />
            <List Delete={removeExpense} list={expenses}/>
            <Total list={expenses} />
        </div>
    )
}

export default Expenses;