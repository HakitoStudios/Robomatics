var baseUrl = "https://robomatics-964e2.firebaseio.com/";
var sketchesUrl = "sketches";
var jsonExt = ".json";
var divider = "/";

function getSketchesUrl() {
    return baseUrl + sketchesUrl + jsonExt;
}

function getSketchUrl(sketchId) {
    return baseUrl + sketchesUrl + divider + sketchId + jsonExt;
}

function showProgress() {
    $("#progress").show();
}

function hideProgress() {
    $("#progress").hide();
}

function getSketch(sketchId, callback) {
    showProgress();
    $.ajax({
        method: "GET",
        url: getSketchUrl(sketchId)
    }).done(callback).always(hideProgress);
}

function getAllSketches(callback) {
    showProgress();
    $.ajax({
        method: "GET",
        url: getSketchesUrl()
    }).done(callback).always(hideProgress);
}

function remove(sketchId, callback) {
    showProgress();
    $.ajax({
        method: "DELETE",
        url: getSketchUrl(sketchId)
    }).done(callback).always(hideProgress);
}

function update(sketchId, sketch, callback) {
    showProgress();
    $.ajax({
        method: "PATCH",
        url: getSketchUrl(sketchId),
        data: JSON.stringify(sketch)
    }).done(callback).always(hideProgress);
}

function add(sketch, callback) {
    showProgress();
    $.ajax({
        method: "POST",
        url: getSketchesUrl(),
        data: JSON.stringify(sketch)
    }).done(callback).always(hideProgress);
}
