function headerScroll(){
  var posicionDeCambio = $('.navbar').height() * 2;
  if($(window).scrollTop() > posicionDeCambio){
      $('#header').addClass("headerA");
      $('#header').removeClass("header");
  }else{
      $('#header').addClass("header");
      $('#header').removeClass("headerA");

  }
}

$( window ).scroll(function() {
  $(window).on("Cargar cada vez que se haga scroll", function(){
      headerScroll();
  });
});
