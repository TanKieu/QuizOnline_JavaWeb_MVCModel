// cai ham nay phai tu viet ha
// cop dan dc ma
// cop o dau vay, trong thu vien k co ha
// t cung k nho
//:V
        // voi may cai nut chinh sao ch n thang hang v m
var items = $(".list-wrapper .list-item");// khai bao item giong hoi nay cua m a
// ma thay vi lay item o day -  thi lay troong trang san co
//
// . la class nha
// list-wrapper la class bao lai cai khung chuyen san pham
// list-item xac dinh 1 san pham de cat 
var numItems = items.length; //  so luong item 
var perPage = 5; // so item hien moi trang
items.slice(perPage).hide();  // ham cat cat di phan con lai cho no' an di
$('#pagination-container').pagination({ // Jquery tao ra may cai so 1 2 3 4
    items: numItems, //  so trang
    itemsOnPage: perPage, 
    prevText: "&laquo;", // Cai nut  << la hai cai nut next prev a
    nextText: "&raquo;", // Cat nut >> 
    onPageClick: function (pageNumber) { // event click vaof thi show item
        var showFrom = perPage * (pageNumber - 1);
        var showTo = showFrom + perPage;
        items.hide().slice(showFrom, showTo).show();
    }
});
