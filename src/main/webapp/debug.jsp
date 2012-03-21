<script type="text/javascript" src="jquery.js"></script>
<script>
    $("body")
        .ready(
            function(){
                //alert("body is ready");

                $("#save")
                    .click(
                        function(){
                            $.ajax({
                            type:"POST",
                            url: "data/debug",
                            success:  function(){
                                alert ("!!!");
                            },
                            error: function(){
                            alert("fail!");
                            });
                        }
                    );
            }
        );
</script>
<input type="button" value="SAVE" id="save"/>

