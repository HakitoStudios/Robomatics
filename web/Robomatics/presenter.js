var currentSketch;
var titleField;
var codeField;

function displaySketches(sketches) {
    clearDetails();
    if (sketches == undefined) {
        alert("No data");
        return;
    }
    for (s in sketches) {
        if (!sketches.hasOwnProperty(s)) {
            return;
        }
        var sketch = sketches[s];
        addListItem(sketch, s);
    }
}
$(function () {
    titleField = $("#title");
    codeField=$("#code");
    getAllSketches(displaySketches);
    $("#add").click(function () {
        add({title: "Custom", code: "Codik"}, function (r) {
            getSketch(r.name, function (sketch) {
                addListItem(sketch);
                showSketchDetails(r.name);
            })
        })
    });
    $("#save").click(function () {
        var c = currentSketch;
        update(currentSketch, {title:titleField.val(), code:codeField.val()}, function (sketch) {
            $("div[id^="+c+"]").find("#sketch_title").text(sketch.title);
          //TODO change list item
        })
    });
});

function addListItem(sketch, s) {
    var item = $("#sketch").clone();
    item.show();
    item.attr("id", s);
    item.find("#sketch_title").text(sketch.title);
    item.find("#delete_sketch").click(function (e) {
        e.stopPropagation();
        remove(s, function () {
            item.detach();
            clearDetails();
        })
    });
    item.click(function () {
        showSketchDetails(s);
    });
    $("#list").append(item);
}

function showSketchDetails(s) {    
    currentSketch = s;
    getSketch(s, function (sketch) {
        titleField.val(sketch.title);
        codeField.val(sketch.code);
        $("#details").show();    
    });    
}

function clearDetails() {
    s = null;
    titleField.text("");
    codeField.text("");
    $("#details").hide();
}

