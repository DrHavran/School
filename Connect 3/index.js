let boardState = [
    [0, 0, 0],
    [0, 0, 0],
    [0, 0, 0]
];
let turn = "x";
let countTurns = 0;
let win = false;

window.onload = function() {
    let board = document.getElementById("board")
    let turnShower = document.getElementById("turnShower")


    for(let i = 0; i<3; i++){
        for(let l = 0; l<3; l++){
            let div = document.createElement("div");
            div.id = i + "-" + l;
            div.addEventListener("click", () => {
                if(div.innerHTML === "" && win===false ){
                    if(turn === "x"){
                        turn = "o"
                        div.innerHTML = "❌"
                        boardState[i][l] = "x"
                        turnShower.innerHTML = "⭕"
                    }else{
                        turn = "x"
                        turnShower.innerHTML = "❌"
                        boardState[i][l] = "o"
                        div.innerHTML = "⭕"
                    }
                    console.log(boardState)
                    countTurns++
                    if(countTurns>=5){
                        checkWin(div)
                    }
                    document.getElementById("count").innerHTML = "Tah: " + countTurns
                }
            });
            board.appendChild(div)
        }
    }
    
    function checkWin(div){
        let check = false

        let row = Number(div.id.split("-")[0])
        let col = Number(div.id.split("-")[1])

        let player = boardState[row][col]


        if(boardState[row][0] === player && boardState[row][1] === player && boardState[row][2] === player){
            document.getElementById(row + "-" + 0).style.backgroundColor = "rgb(11, 158, 97)"
            document.getElementById(row + "-" + 1).style.backgroundColor = "rgb(11, 158, 97)"
            document.getElementById(row + "-" + 2).style.backgroundColor = "rgb(11, 158, 97)"
            check = true;
        }
            
        if(boardState[0][col] === player && boardState[1][col] === player && boardState[2][col] === player){
            document.getElementById(0 + "-" + col).style.backgroundColor = "rgb(11, 158, 97)"
            document.getElementById(1 + "-" + col).style.backgroundColor = "rgb(11, 158, 97)"
            document.getElementById(2 + "-" + col).style.backgroundColor = "rgb(11, 158, 97)"
            check = true;
        }
    
        if(boardState[0][0] === player && boardState[1][1] === player && boardState[2][2] === player){
            document.getElementById(0 + "-" + 0).style.backgroundColor = "rgb(11, 158, 97)"
            document.getElementById(1 + "-" + 1).style.backgroundColor = "rgb(11, 158, 97)"
            document.getElementById(2 + "-" + 2).style.backgroundColor = "rgb(11, 158, 97)"
            check = true;
        }
        
        if(boardState[0][2] === player && boardState[1][1] === player && boardState[2][0] === player){
            document.getElementById(0 + "-" + 2).style.backgroundColor = "rgb(11, 158, 97)"
            document.getElementById(1 + "-" + 1).style.backgroundColor = "rgb(11, 158, 97)"
            document.getElementById(2 + "-" + 0).style.backgroundColor = "rgb(11, 158, 97)"
            check = true;
        }

        if(check == true){
            alert(player + " won")
            turnShower.innerHTML = ""
            win = true
        }
    }

};
function reset(){
    boardState = [
        [0, 0, 0],
        [0, 0, 0],
        [0, 0, 0]
    ];
    for(let slot of board.children){
        slot.innerHTML = ""
        slot.style.backgroundColor = ""
    }
    turn = "x"
    turnShower.innerHTML = "❌"
    countTurns = 0
    document.getElementById("count").innerHTML = "Tah: 0"
    win = false
  }