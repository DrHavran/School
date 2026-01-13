import List from "../parts/List"
import Forum from "../parts/Forum"
import {useState} from "react";

function Expenses () {
    const [expenses, setTasks] = useState(
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

    const addTask = (newExpense) => {
        let newId = Math.max(...expenses.map(task => task.id)) + 1;
        newExpense.id = newId;
        setTasks([...expenses, newExpense]);
    }

    const removeTask = (id) => {
        setTasks(tasks.filter((task) => task.id !== id));
    }

    return (
        <div className="d-flex flex-column align-items-center">
            <h1 className="my-4" >Expenses</h1>
            <Forum onSubmit={addTask} />
            <List Delete={removeTask} iop-.,,m />
        </div>
    )
}

export default Expenses;