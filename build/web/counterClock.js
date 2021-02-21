

var h = 0; // Giờ
var m = 60; // Phút
var s = 0; // Giây

var timeout = null; // Timeout
 function start(timeleft)
{
$('#m').text(Math.floor(timeleft / 60) + ' Phut');
$('#s').text(Math.floor(timeleft % 60) + ' Giay');

  if(timeleft > 0){
    setTimeout(function(){
            start(timeleft - 1);
    },1000);
  } else{
             $('#SubmitButton').click()
  //      stop();
  }
  
}

//function stop() {
//  location.replace('SubmitAnswerServlet');
//}
