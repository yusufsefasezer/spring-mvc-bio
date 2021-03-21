<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@tag description="footer" pageEncoding="UTF-8" %>
<footer class="container">
    <div class="row">
        <div class="col-md-12 text-center p-3">
            <p><spring:message code="site.admin.footer.copyright" /> &copy; <fmt:formatDate pattern="dd/MM/yyyy" value="<%=new java.util.Date()%>" /> 
                <a href="https://www.yusufsezer.com" target="_blank">Yusuf SEZER</a>
            </p>
        </div>
    </div>
</footer>

<!--script-->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    (function ($) {
        "use strict";
        var RESIZE_IMAGE = true;

        $(".image").on('click', function () {
            $("#image").click();
        });

        $("#image").on('change', function () {

            if (this.files.length !== 1) {
                return false;
            }

            var selectedFile = this.files[0];
            if (!selectedFile.type.match('image.*')) {
                return false;
            }

            var reader = new FileReader();
            reader.addEventListener('load', function (e) {

                var newImage = new Image(),
                        canvas = document.createElement('canvas'),
                        ctx = canvas.getContext('2d'),
                        MAX = 300;

                newImage.src = e.target.result;

                newImage.addEventListener('load', function () {
                    var ratio = MAX / newImage.height;

                    newImage.width *= ratio;
                    newImage.height = MAX;

                    ctx.clearRect(0, 0, newImage.width, newImage.height);
                    canvas.width = newImage.width;
                    canvas.height = newImage.height;
                    ctx.drawImage(newImage, 0, 0, newImage.width, newImage.height);

                    var newVal = RESIZE_IMAGE ? canvas.toDataURL() : newImage.src;
                    $(".image").html('<img src=' + (newVal) + ' height="100" />');
                    $("#photo").val(newVal);

                });

            });
            reader.readAsDataURL(selectedFile);
            return true;
        });

        $("#featureSelect").on('change', function () {

            if (this.selectedIndex > 0) {
                var keyValue = this.selectedOptions[0].text.split('-');
                var key = keyValue[0].trim();
                var value = keyValue[1].trim();
                $("#key").val(key);
                $("#value").val(value);
                $("#btnAdd").addClass("d-none");
                $("#btnUpdate").removeClass("d-none");
                $("#btnRemove").removeClass("d-none");
            } else {
                $("#key").val(null);
                $("#value").val(null);
                $("#btnAdd").removeClass("d-none");
                $("#btnUpdate").addClass("d-none");
                $("#btnRemove").addClass("d-none");
            }

        });

        $("#btnAdd").on('click', function (e) {
            e.preventDefault();
            var key = $("#key").val();
            var value = $("#value").val();
            var featureSelect = $("#featureSelect");

            if ($("#features input[name='features[" + key + "]']").length > 0) {
                $("#key").focus();
                return alert("Eklenmiş");
            }

            var input = document.createElement("input");
            input.type = "hidden";
            input.name = "features[" + key + "]";
            input.value = value;
            $("#features").append(input);

            featureSelect.append(new Option(key + " - " + value));
            var lastIndexNum = $("#featureSelect").prop('options').length - 1;
            featureSelect.prop('selectedIndex', lastIndexNum);
            featureSelect.trigger("change");
        });

        $("#btnRemove").on('click', function (e) {
            e.preventDefault();
            var key = $("#key").val();

            $("#features input[name='features[" + key + "]']").remove();
            $("#featureSelect option:selected").remove();
            $("#featureSelect").trigger("change");

        });

        $("#btnUpdate").on('click', function (e) {
            e.preventDefault();
            var featureSelect = $("#featureSelect option:selected");
            var key = featureSelect.val().split('-')[0].trim();
            var newKey = $("#key").val();
            var newValue = $("#value").val();

            var updatedInput = $("#features input[name='features[" + key + "]']");
            updatedInput.val(newValue);
            updatedInput.attr("name", "features[" + newKey + "]");
            featureSelect.text(newKey + " - " + newValue);
        });

    })(jQuery);
</script>
