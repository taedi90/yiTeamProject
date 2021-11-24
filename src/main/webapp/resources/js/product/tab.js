$(function(){
    $(".tab_product_information").click(function(){
        $("#tab_product_information").show();
        $("#tab_product_review").hide();
        $("#tab_product_question").hide();
    });
    
    $(".tab_product_review").click(function(){
        $("#tab_product_information").hide();
        $("#tab_product_review").show();
        $("#tab_product_question").hide();
    });

    $(".tab_product_question").click(function(){
        $("#tab_product_information").hide();
        $("#tab_product_review").hide();
        $("#tab_product_question").show();
    });
});