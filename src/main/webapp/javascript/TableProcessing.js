function validateGrade(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
    if ( (charCode < 50 || charCode > 53) && charCode != 8 && charCode != 48 && charCode != 96)
        return false;

    return true;

}

function validateName(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 32 || charCode == 8)
        return true;
    else
        return false;
}

function addRow(){

    var table = document.getElementById('table');

    var deleteButton = document.createElement("button");
    deleteButton.innerHTML = "[-]";
    deleteButton.setAttribute('style', 'width: 20px; height: 20px; padding: 0; border: 5px; margin: 2px;' +
        ' text-align: center;');

    var nameInput = document.createElement("input");
    nameInput.type = "text";
    nameInput.name = "name";

    var newRow = table.insertRow(table.rows.length - 1);

    var newCell = newRow.insertCell(0);
    newCell.appendChild(deleteButton);
    newCell.appendChild(nameInput);
    deleteButton.setAttribute("onclick", "deleteRow(this)");
    nameInput.setAttribute("onkeypress", "return validateName(event)");

    var len = table.rows[0].cells.length;
    var gradeInput = document.createElement("input");
    gradeInput.type = "text";
    gradeInput.setAttribute("onkeypress", "return validateGrade(event)");
    gradeInput.setAttribute("maxlength", "1");
    gradeInput.value = "0";


    var LW = table.rows[0].getElementsByClassName("LR");
    var Test = table.rows[0].getElementsByClassName("KR");
    var CW = table.rows[0].getElementsByClassName("CW");

    var i = 0;
    var n = 1;
    while (i < LW.length){
        var newCell = newRow.insertCell(n++);
        var curInput = gradeInput.cloneNode(true);
        curInput.setAttribute('name', "LR" + i++);
        newCell.appendChild(curInput);
    }
    i = 0;
    while (i < Test.length){
        var newCell = newRow.insertCell(n++);
        var curInput = gradeInput.cloneNode(true);
        curInput.setAttribute('name', "KR" + i++);
        newCell.appendChild(curInput);
    }
    i = 0;
    if (CW.length == 1){
        var newCell = newRow.insertCell(n++);
        var curInput = gradeInput.cloneNode(true);
        curInput.setAttribute('name', "CW");
        newCell.appendChild(curInput);
    }
    var newCell = newRow.insertCell(n++);
    var curInput = gradeInput.cloneNode(true);
    curInput.name = "Exam";
    newCell.appendChild(curInput);

    newCell = newRow.insertCell(-1);
    newCell.innerHTML = "0";

//    tmp = table.rows[1].cloneNode(true);
//    var len = tmp.cells.length;
//    //newRow = tmp;
//
//    for (var i = 0; i < len; i++){
//        var clone = tmp.cells[i].cloneNode(true);
//        newRow.appendChild(clone);
//    }
//
//    for (var i = 0; i < len - 1; i++){
//        if (i == 0) var inp = newRow.cells[i].getElementsByTagName('input')[1];
//        else var inp = newRow.cells[i].getElementsByTagName('input')[0];
//        inp.value = '';
//    }
//    var inp = newRow.cells[len - 1];
//    inp.innerHTML = '';

}

function deleteRow(element){

    var row = element.parentNode.parentNode;
    var table = document.getElementById('table');
    for (var i = 0; i < table.rows.length; i++){
        var cur = table.rows[i];
        if (row == cur)
            table.deleteRow(i);
    }

}

function deleteColumn(element){

    var cell = element.parentNode;
    var table = document.getElementById('table');
    var LW = table.rows[0].getElementsByClassName('LR');
    var Test = table.rows[0].getElementsByClassName('KR');
    var CW = table.rows[0].getElementsByClassName('CW');

    var curClass = cell.getAttribute('class');
    var task;
    if (curClass == 'LR') task = 'LW';
    else if (curClass == 'KR') task = 'Test';
    else task = 'CW';
    var curNumber = parseInt(cell.getAttribute('name'));
    var len = table.rows[0].cells.length;
    var targetColumn;

    for (var i = 1; i < len - 1; i++){
        var curCell = table.rows[0].cells[i];
        if (curCell == cell){
            targetColumn = i;
        }
    }

    console.log(curClass + ' ' + cell.getAttribute('name') + '; ' + task);
    console.log('length: ' + LW.length);
    console.log('curNumber: ' + curNumber + '; targetColumn: ' + targetColumn);


    for (var i = 0; i < table.rows.length; i++){
        table.rows[i].deleteCell(targetColumn);
    }

    var curColumn = targetColumn;

    while(table.rows[0].cells[curColumn].getAttribute('class') == curClass){

        var curCell = table.rows[0].cells[curColumn];
        curCell.setAttribute("name", curNumber);
        curCell.childNodes[2].nodeValue = String(task) + String(curNumber);
        console.log(curCell.childNodes[0].nodeName + ' ' + curCell.childNodes[0].nodeValue);
        console.log(curCell.childNodes[1].nodeName + ' ' + curCell.childNodes[1].nodeValue);
        console.log(curCell.childNodes[2].nodeName + ' ' + curCell.childNodes[2].nodeValue);
        console.log(curCell.childNodes.length);

        for (var i = 1; i < table.rows.length - 1; i++){
            var inps = table.rows[i].cells[curColumn].getElementsByTagName('input');
            inps[0].setAttribute('name', curClass + (parseInt(curNumber) - 1));
        }

        curNumber++;
        curColumn++;
    }


}


function addColumn(){
    var table = document.getElementById('table');
    var choice = document.getElementById('taskToAdd');
    var task = choice.options[choice.selectedIndex].text;
    var modelTask = choice.options[choice.selectedIndex].value;
    //var style = table.rows[0].

    var deleteButton = document.createElement("button");
    deleteButton.innerHTML = "[-]";
    deleteButton.setAttribute("onclick", "deleteColumn(this)");
    deleteButton.setAttribute('style', 'width: 20px; height: 20px; padding: 0; border: 5px; margin: 2px;' +
        ' text-align: center;');

    var gradeInput = document.createElement("input");
    gradeInput.type = "text";
    gradeInput.setAttribute("onkeypress", "return validateGrade(event)");
    gradeInput.setAttribute("maxlength", "1");
    gradeInput.value = "0";
    console.log('gradeInput: ' + gradeInput.value);

    var LW = table.rows[0].getElementsByClassName('LR');
    var Test = table.rows[0].getElementsByClassName('KR');
    var CW = table.rows[0].getElementsByClassName('CW');
    var index = LW.length;
    var taskIndex = index;

    if (task == 'Test'){
        index += Test.length;
        taskIndex = Test.length;
        //modelTask = 'KR';
    }

    if (task == 'CW'){

        if(CW.length == 1){
            alert('There can only be 1 CW per subject!');
            return;
        }
        index += Test.length + CW.length;
        taskIndex = CW.length;
        //modelTask = 'CW';

    }

    var newCell = table.rows[0].insertCell(index + 1);
    var text = document.createTextNode(task + parseInt(taskIndex + 1));
    if (task == 'CW'){
        text = document.createTextNode('CW');
    }
    newCell.appendChild(document.createTextNode(''));
    newCell.appendChild(deleteButton);
    newCell.appendChild(text);

    newCell.setAttribute('name', parseInt(taskIndex + 1));
    newCell.setAttribute('class', modelTask);


    for (var i = 1; i < table.rows.length - 1; i++){
        var row = table.rows[i];
        var newCell = row.insertCell(index + 1);
        var curInput = gradeInput.cloneNode(true);
        console.log('curInput' + i +': ' + curInput.value);
        console.log('index' + i + ': ' + index + 'task ' + task);
        if (task == 'CW') index = '';
        curInput.setAttribute('name', modelTask + taskIndex);
        newCell.appendChild(curInput);
    }
    table.rows[table.rows.length - 1].insertCell(index + 1);

}