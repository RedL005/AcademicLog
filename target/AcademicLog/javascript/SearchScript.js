var tables = document.getElementsByTagName('table');
console.log(tables.length);

for (var i = 0; i < tables.length; i++){
    var cur = tables[i];
    var LR = tables[i].getElementsByClassName('LR');
    var KR = tables[i].getElementsByClassName('KR');
    var CW = tables[i].getElementsByClassName('CW');
    var exam = tables[i].getElementsByClassName('Exam');
    console.log(LR.length);

    if (LR.length == 0 && KR.length == 0 && CW.length == 0 && exam.length == 0){
        cur.parentNode.removeChild(cur);
        i--;
    }
}


var subjects = document.getElementsByClassName('subject');

for (var i = 0; i < subjects.length; i++){
    var entries = subjects[i].getElementsByTagName('table');
    if (entries.length == 0){
        var para = document.createElement("P");
        var t = document.createTextNode("No entries that much search parameters");
        para.appendChild(t);
        subjects[i].appendChild(para);
    }
}