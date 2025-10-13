function addTask(){
    let task = document.getElementById("input").value
    if(task!=""){
        let div = document.createElement("div")
        div.id = "task"

        let label = document.createElement("p")
        label.innerText = task

        let button = document.createElement("button")
        button.onclick = function(){removeTask(div)}

        div.appendChild(label)
        div. appendChild(button)
        document.getElementById("list").appendChild(div)
    }
}
function removeTask(div){
    div.remove()
}