$(function (){
    $(".nav-item a").click(function () {
        if ($(this).attr("href")) {
            window.location.href = ($(this).attr("href"))
        }
    })
})
