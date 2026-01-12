import Expense from "./Expense"
import {useState} from "react";

function List () {
    const [tasks, setTasks] = useState(
        [
            {
                id: 1,
                name: "Vynést koše",
                description: "Už smrdí"
            },
            {
                id: 2,
                name: "Umýt okna",
                description: "Není vidět ven"
            }
        ]
    )




    if (tasks.length !== 0) {
        return (
            <div className="rounded-3 p-3">
                 {tasks.map((task) => (
                    <Expense key={task.id} Name={task.name} Description={task.description} Id={task.id} Delete={Delete}/>
                ))}
            </div>
        )
    }else {
        return (<h3>Žádné úlohy k zobrazení.</h3>)
    }
}

export default List;