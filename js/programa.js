$(document).ready(function(){
    $(".prog").click(function() {
      $("#titulo").text($(this).find("h3").text());
      $("#horario").text($(this).find("span").text());
      $("#articulo").html($(this).find(".descripcion").html());
  	  $("#imagen").attr('src',$(this).find("img").attr('src'));

  	  var scrollPos =  $("html").offset().top;
   	  $(window).scrollTop(scrollPos);
    });
});
