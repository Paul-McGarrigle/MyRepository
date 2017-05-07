var usernameFree = false;
var passwordIsStrong = false;
var passwordsMatch = false;
console.log("HERE SCRIPT");
function checkUsername(username) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/ericssongroupproject/front/rest/users/" + username,
        dataType: "json",
        success: function (data) {
            if (data == false) {
                $("#username").text("Username exists.");

                $("#status-name").removeClass("icon-check");
                $("#status-name").addClass("icon-close");

                $("#signup-name").addClass("error-input");
                $(".signup-name").addClass("error-placeholder");
				
				usernameFree = false;

            }else{
                $("#username").text("");
				
                $("#status-name").removeClass("icon-close");
                $("#status-name").addClass("icon-check");

                $(".signup-name").removeClass("error-placeholder");
                $("#signup-name").removeClass("error-input");
				
				usernameFree = true;
            }
        }
    });
}

function checkStrength(password){
    //initial strength
    var strength = 0;
    //if the password length is less than 6, return message
    if(password.length < 6){
        $("#result").removeClass();
        $("#result").addClass("short");
        return "Too short"
    }
    //if length is 8 characters or more, increase strength value
    if(password.length > 7) strength += 1;
    //if password contains both lower and uppercase characters, increase strength value
    if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) strength += 1;
    //if it has numbers and characters, increase strength value
    if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)) strength += 1 ;
    //if it has one special character, increase strength value#
    if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1;

    //if value is less than 2
    if (strength < 2 ) {
        $('#result').removeClass();
        $('#result').addClass('weak');
        return 'Weak' 
		passwordIsStrong = false;
	} else if (strength == 2 ) {
        $('#result').removeClass();
        $('#result').addClass('good') ;
		passwordIsStrong = true;
        return 'Good' 
	} else {
        $('#result').removeClass() ;
        $('#result').addClass('strong') ;
		passwordIsStrong = true;
        return 'Strong' 
	}
}

$(document).ready(function () {
    /*$("#signup-form").submit(function(event){
        event.preventDefault();//Default action will not happen
    });
    $("#roleType").on("change", function(){
        $("#signup-role").val($("#roleType option:selected").text());
    });

    $("#signup-name").on("keyup", function () {// when key is released
        var input = $(this);
        var is_uname = input.val();
        checkUsername(is_uname);
    });
	
    $("#signup-password").on("keyup", function () {
        $("#result").html(checkStrength($("#signup-password").val()));

    });
    $("#signup-repassword").on("input", function () {
        var pwd = $("#signup-password").val();
        var rpwd = $("#signup-repassword").val();
        if (pwd != rpwd || rpwd == "")
        {
			$("#checkPass").text("Passwords must match.");
			
            $("#status-repassword").removeClass("icon-check");
            $("#status-repassword").addClass("icon-close");
            $(".signup-repassword").addClass("error-placeholder");
            $("#signup-repassword").addClass("error-input");
			
			passwordsMatch = false;
        }

        // If repassword is ok
        else if (pwd === rpwd && rpwd!== "")
        {
			$("#checkPass").text("");
			
            $("#status-repassword").addClass("icon-check");
            $("#status-repassword").removeClass("icon-close");
            $(".signup-repassword").removeClass("error-placeholder");
            $("#signup-repassword").removeClass("error-input");
			passwordsMatch = true;
        }

    });*/

    var register = function(username, password) {
        this.username=username;
        this.password = password;
        //this.role = role;
    }

    $("#regBtn").click(function () {
        var username = $("#name_text").val();
        var password = $("#pw_text").val();
        //var rpassword = $("#signup-repassword").val();
        //var role = $("#signup-role").val();
		
		//var successAlert = '<div class="alert alert-success alert-dismissable" id="success"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>User successfully created.</div>';
		//var failureAlert = '<div class="alert alert-danger alert-dismissable" id="success"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>Error. Please try again.</div>';
		
        if(username !== "" && password !== ""){
            var user = new register(username,password);
            $.ajax({
                url:"http://localhost:8080/DistSysAssignment-0.0.1-SNAPSHOT/rest/tracks/register",
                type:"POST",
                contentType: "application/json",
                success:function () {
                    //document.getElementById("alerts").innerHTML = successAlert;
                	console.log("HERE SUCCESS");
					$('#name_text').val('');
					$('#pw_text').val('');
					//$('#signup-repassword').val('');
					//$('#signup-role').val('');
					//$('#result').text('');
                },
                error:function () {
                    // document.getElementById("alerts").innerHTML = failureAlert;
                	console.log("HERE FAIL");
                },
                data:JSON.stringify(user)
            });
			
        }// If login is empty
        else {
        	console.log("HERE EMPTY");
        }

    });


});