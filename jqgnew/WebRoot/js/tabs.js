function secBoard(elementID,listName,n) {
 var elem = document.getElementById(elementID);
 var elemlist = elem.getElementsByTagName("li");
 for (var i=0; i<elemlist.length; i++) {
  elemlist[i].className = "normal";
  var m = i+1;
  document.getElementById(listName+"_"+m).className = "normal";
 }
  elemlist[n-1].className = "current";
  document.getElementById(listName+"_"+n).className = "current";
}