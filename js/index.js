$( document ).ready(function() {
  var array = [1,2,3];
  $('#ponente'+array[0]).removeClass("d-none");
  $('#ponente'+array[1]).removeClass("d-none");
  $('#ponente'+array[2]).removeClass("d-none");
  $('#noticia1').removeClass("d-none");

  $('.noticia').click(function(){
      var id = $(this).data('id');
      $('#noticia1').addClass("d-none");
      $('#noticia2').addClass("d-none");
      $('#noticia3').addClass("d-none");
      $('#noticia4').addClass("d-none");
      $('#noticia'+id).removeClass("d-none");
  });


  $('div.ponentes').hover(function(){
        $(this).find(".redesSociales").removeClass("d-none");
        $(this).find(".titulo").addClass("d-none");
    }, function() {
        $(this).find(".redesSociales").addClass("d-none");
        $(this).find(".titulo").removeClass("d-none");
    });

  $('#btn-derecha').click(function(){
  //  arrayAux = array;
    $('#ponente'+array[0]).addClass("d-none");
    $('#ponente'+array[1]).addClass("d-none");
    $('#ponente'+array[2]).addClass("d-none");

    $.each(array, function (index, value) {
      if(array[index] == 6) array[index] =1;
      else array[index]++;
    });
      //console.log(arrayAux);
      console.log(array);


      $('#ponente'+array[0]).removeClass("d-none");
      $('#ponente'+array[1]).removeClass("d-none");
      $('#ponente'+array[2]).removeClass("d-none");

  });
  $('#btn-izquierda').click(function(){
  //  arrayAux = array;
    $('#ponente'+array[0]).addClass("d-none");
    $('#ponente'+array[1]).addClass("d-none");
    $('#ponente'+array[2]).addClass("d-none");

    $.each(array, function (index, value) {
      if(array[index] == 1) array[index] =6;
      else array[index]--;
    });
      //console.log(arrayAux);
      console.log(array);


      $('#ponente'+array[0]).removeClass("d-none");
      $('#ponente'+array[1]).removeClass("d-none");
      $('#ponente'+array[2]).removeClass("d-none");

  });

});
