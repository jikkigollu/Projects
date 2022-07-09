let level=document.getElementById('level');
let generate=document.getElementById('generate_sudoku');
let solve=document.getElementById('solve_sudoku');

level.selectedIndex=0;

var arr=[[],[],[],[],[],[],[],[],[]];
var board=[[],[],[],[],[],[],[],[],[]];

for(var i=0;i<9;i++){
    for(var j=0;j<9;j++){
        arr[i][j]=document.getElementById(i*9+j);
    }
}

function resetColor(){
    for(var i=0;i<9;i++){
        for(var j=0;j<9;j++){
            arr[i][j].style.color="green";
        }
    }
}

function setColor(){
    for(var i=0;i<9;i++){
        for(var j=0;j<9;j++){
            if(board[i][j]!=0){
                arr[i][j].style.color="black";
            }
        }
    }
}

function printBoard(board){
    for(var i=0;i<9;i++){
        for(var j=0;j<9;j++){
            if(board[i][j]!=0){
                arr[i][j].innerText=board[i][j];
            }
            else{
                arr[i][j].innerText='';
            }
        }
    }
}

var row=[0,0,0,0,0,0,0,0,0];
var col=[0,0,0,0,0,0,0,0,0];
var box=[0,0,0,0,0,0,0,0,0];
var empty=[];


generate.onclick=function(){
    if(level.selectedIndex===0){
        alert("Please select difficulty level");
    }
    else{
        // console.log(level[level.selectedIndex].text);
        var req=new XMLHttpRequest();
        req.onload=function(){
            var response=JSON.parse(req.response);
            resetColor();
            board=response.board;
            setColor(board);
            printBoard(board);
        }
        var link='https://sugoku.herokuapp.com/board?difficulty='+level[level.selectedIndex].text;
        // console.log(link);
        req.open('get',link);
        req.send();
    }
}

function check(a,b){
    return (a&(1<<b));
}

function float2int(value){
    return value|0;
}

function dfs(k){
    if(k===empty.length){
        return true;
    }
    var i=empty[k][0];
    var j=empty[k][1];
    var b=(float2int(i/3))*3+float2int(j/3);
    for(var val=1;val<=9;val++){
        if(check(row[i],val) || check(col[j],val) || check(box[b],val)){
            continue;
        }
        var rv=row[i],cv=col[j],bv=box[b];
        board[i][j]=val;
        row[i]=row[i]|(1<<val);
        col[j]=col[j]|(1<<val);
        box[b]=box[b]|(1<<val);
        if(dfs(k+1)){
            return true;
        }
        row[i]=rv;
        col[j]=cv;
        box[b]=bv;
    }
    return false;
}

function solveSudoku(board,sr,sc){
    for(var i=0;i<9;i++){
        row[i]=0;
        col[i]=0;
        box[i]=0;
    }
    empty=[];
    for(var i=0;i<9;i++){
        for(var j=0;j<9;j++){
            if(board[i][j]===0){
                empty.push([i,j]);
            }
            else{
                row[i]=row[i]|(1<<board[i][j]);
                col[j]=col[j]|(1<<board[i][j]);
                var b=(float2int(i/3))*3+float2int(j/3);
                box[b]=box[b]|(1<<board[i][j]);
            }
        }
    }
    dfs(0);
    printBoard(board);
    // console.log(empty);
    // console.log(row,col,box);
}

solve.onclick=function(){
    solveSudoku(board);
}