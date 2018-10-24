


$(document).ready(function(){
    $(".prog").click(function() {
      $("#titulo").text($(this).find("h3").text());
      $("#horario").text($(this).find("span").text());
      $("#articulo").text($(this).find(".descripcion").text());
    });
});
