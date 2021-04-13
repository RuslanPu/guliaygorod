//add offer
$('#addFinishOfferSubmit').click(function (e) {
    e.preventDefault();
    var form = $('#addOffer')[0];
    var data = new FormData(form);

    $.ajax({
        url: '/offer/add',
        // datatype: 'json',
        type: 'post',
        // contentType: "application/json",
        enctype: 'multipart/form-data',
        processData : false,  // <----required to upload
        contentType : false,  // <----required to upload
        cache : false,
        timeout: 600000,
        data: data,
        success: function (data) {
            console.log(data);
            window.location.href = "/admin";
            // $('.rowForAddOffer').append('<div class="col-sm-4" >'
            //     +'<div class="card">'
            //     +'<img src="files/' +
            //     data.imageId +
            //     '" class="card-img-top" alt="...">'
            //     +'<div class="card-body">'
            //     +'<h5 id="offerName" class="card-title">'+
            //     'Название услуги :' +data.nameOffer
            //     +'</h5>'
            //     +'<p id="offerDisc" class="card-text" >'+
            //     'Описание услуги :'+data.descOffer
            //     +'</p>'
            //     +'<p id="offerCost" class="card-text" >'+
            //     'Стоимость услуги :' + data.priceOffer
            //     +'</p>'
            //     +'<p id="offerCategory" class="card-text" >'+
            //     'Категория услуги :' + data.offerCategory
            //     +'</p>'
            //     +'<div class="btn-group" role="group"><a href="#" class="btn_edit_offer btn btn-primary" data-toggle="modal" data-target="#editOffer"' +
            //     ' data="'+
            //     data.id+
            //     '">Редактирование</a></div><div class="btn-group" role="group"><a href="#" class="btn btn-danger btnDeleteOffer" data-toggle="modal" data-target="#deleteOffer" th:data="' +
            //     data.id +
            //     '">Удалить</a></div>'
            //     +'</div></div></div>'
            // );
            // $("#addOfferModal").modal('hide');
            // alert(data.id);
            // elementParentByID.children('#offerName').text(data.nameOffer);
            //
            // elementParentByID.children('#offerDisc').text(data.descOffer);
            // elementParentByID.children('#offerCost').text(data.priceOffer);
            // elementParentByID.children('#offerCategory').text(data.offerCategory);
            // elementParentByID.parent().children('.card-img-top').attr('src','files/'+data.imageId);
        }


    })

});
//action click button edit offer
var offerDataAfterEdit = new Object();
//click edit offer to open modal window
$('.btn_edit_offer').click(function() {

    var id = $(this).attr('data');
    getOfferById(id);

});

//update offer
$('#editFinishOfferSubmit').click(function() {

    var form = $('#uploadFormDataOffer')[0];
    var data = new FormData(form);
    $.ajax({
        url: '/offer/update',
        // datatype: 'json',
        type: 'post',
        // contentType: "application/json",
        enctype: 'multipart/form-data',
        processData : false,  // <----required to upload
        contentType : false,  // <----required to upload
        cache : false,
        timeout: 600000,
        data: data,
        success: function (data) {

            $("#editOffer").modal('hide');
            var elementParentByID = $('.btn_edit_offer').filter(function () {
                return $(this).attr('data') == data.id;
            }).parent().parent();
            elementParentByID.children('#offerName').text('Название: '+ data.nameOffer);

            elementParentByID.children('#offerDisc').text('Описание: ' + data.descOffer);
            elementParentByID.children('#offerCost').text('Стоимость: ' + data.priceOffer);
            elementParentByID.children('#offerCategory').text('Категория: ' + data.offerCategory);
            elementParentByID.parent().children('.card-img-top').attr('src','files/'+data.imageId);
        }


    });

});

//get offer
function getOfferById(id) {
    $.ajax({
        url: '/offer/requestById',
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            id: id
        }),
        success: function (data) {
            console.log(data);
            $(".formEditOffer .form-group .id").val(data.offer.id);
            $(".formEditOffer .form-group .name").val(data.offer.nameOffer);
            $(".formEditOffer .form-group .category").val(data.offer.offerCategory);
            $(".formEditOffer .form-group .cost").val(data.offer.priceOffer);
            $('.formEditOffer .form-group .descOfferTextarea').val(data.offer.descOffer);
            $(".formEditOffer .form-group .imageOffer").attr("src","files/"+ data.offer.imageId );

        }
    });
}
//delete offer
$('.btnDeleteOffer').click(function() {
    var elementId = $(this).attr('data');
    $('.buttonAfterDeleteOffer').click(function () {
        var id = elementId;
        $.ajax({
            url: 'offer/delete',
            datatype: 'json',
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                id: id
            }),
            success: function (data) {
                console.log(data);
                var elementParentByID = $('.btn_edit_offer').filter(function () {
                    return $(this).attr('data') == id;
                }).parent().parent().parent().remove();


                $('#deleteOffer').modal('hide');
            }
        });
    });
})