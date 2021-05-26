$(document).ready(function(){
    $("#inputfile").click(function(){
        $("#inputfile3").click();
        return false;
    })
    $("#inputfile3").change(function(){
        $("#inputfile2").val($("#inputfile3").val());
    })
});