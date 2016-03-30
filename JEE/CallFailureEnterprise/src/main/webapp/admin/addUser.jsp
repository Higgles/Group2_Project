<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">

<title>Add User</title>

	<link href="http://fonts.googleapis.com/css?family=Lato:100italic,100,300italic,300,400italic,400,700italic,700,900italic,900" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="../media/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="../resources/syntax/shCore.css">
	<link rel="stylesheet" type="text/css" href="../media/css/select2.min.css">
	<link rel="stylesheet" type="text/css" href="../resources/demo.css">
	<link rel="stylesheet" type="text/css" href="../media/css/mainPage.css">
	<link rel="stylesheet" type="text/css" href="../media/css/user.css" />
<!--
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/syntax/shCore.css">
<link rel="stylesheet" type="text/css" href="resources/demo.css">
<link rel="stylesheet" href="media/css/bootstrap-table.css">
<style type="text/css" class="init">
 -->

</style>
<script type="text/javascript" language="javascript" src="../media/js/jquery.js"></script>
<script type="text/javascript" language="javascript" src="../resources/syntax/shCore.js"></script>
<script type="text/javascript" src="../media/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../media/js/transition.js"></script>
<script type="text/javascript" src="../media/js/moment-with-locales.js"></script>
<script type="text/javascript" src="../media/js/collapse.js"></script>
<script type="text/javascript" language="javascript" class="init"></script>
<script type="text/javascript" src="../media/js/select2.full.js"></script>
	
</script>
</head>

<body>
	<br/>
	<div class="container">
		<div class="jumbotron" style="background: #337AB7; font-family: HelveticaNeue,Helvetica,Arial,sans-serif">
			<header>
				<h1>CoolBeanzzz</h1>
			</header>
		</div>
	</div>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#" id="userBar">User Name...Role</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li style="font-size: 1.8em;"><a href="upload.jsp" class="navbar-brand"><span></span> Upload Dataset</a></li>
					<li style="font-size: 1.8em;"><a href="#" id="logintype" class="navbar-brand"><span></span> Hello</a></li>
					<li style="font-size: 1.8em;">
						<a href="../logout">
							<span class="glyphicon glyphicon-log-out"></span> Logout
						</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="col-lg-12">
		<div class="panel panel-primary">
			<div id="phead2" class="panel-heading">
				<h4>Add/Edit Users</h4>
			</div>
			<div class="panel-body" style="font-size: 15px;">
				<div align = "center">
					<p>
					<br/>
					<form action="" id="add/editUser">
					    <input id="addUserToggle" type="radio" onclick="swapType(1)" name="add/editUser" value="addUser" checked> Add User
					    <input id="editUserToggle" type="radio" onclick="swapType(2)" name="add/editUser" value="editUser"> Edit/Remove Existing User
					</form>
					<br/>
					<div id="uNameDiv">
						<label for="uName">Username</label>
						<input type="text" class="form-control" id="uName">
					</div>
					<div id="uNameDropdownDiv">
						<label for="uNameDropdown">Username</label><br>
						<select id="uNameDropdown" class="js-example-responsive" style="width: 450px;"></select>
					</div>
					<br/>
					<br/>
					<label for="pWord">Password</label>
					<input type="password" class="form-control" id="pWord">
					<br/>
					<br/>
					<label for="pWordConf">Password Confirm</label>
					<input type="password" class="form-control" id="pWordConf">
					<br/>
					<div>
					<label for="userType">Choose User Type</label>
					<form action="" id="userType">
					    <input type="radio" name="userType" value="SysAd"> System Administrator <br>
					    <input type="radio" name="userType" value="NetManEng"> Network Manager<br>
					    <input type="radio" name="userType" value="SupEng"> Support Engineer <br>
					    <input type="radio" name="userType" value="CSR" checked> Customer Service Representative
					</form>
					</div>
					
					<p id="count">
					</p>			
						
						  <div id="addUserDiv" class="btn-group" role="group" aria-label="First group">
						    <button id="addUser" type="button" class="btn btn-primary" onclick="addUser()">Add User</button>	
						    <button id="editUser" type="button" class="btn btn-primary" onclick="editUser()">Edit User</button>	
						    <button id="removeUser" type="button" class="btn btn-primary" onclick="removeUser()">Remove User</button>
						  </div>
						
					</div>
			</div>
		</div>
	</div>
	
	
	
	<script>
	
	$(document).ready(function() {
		$(function() {
			setUserDetails();
			populateUsernameDropdown();
			swapType(1);
		});
	});
	
	function setUserDetails() {
		$.ajax({
			type : 'GET',
			url : '../rest/users/currentUser',
			success : function(data) {
				var userBar = document.getElementById("userBar");
				userBar.innerHTML = "Priviledge type: " + data[1];
				var loginType = document.getElementById("logintype");
				loginType.innerHTML = "<span></span>Logged in as: "
						+ data[0];
			},
		});
	}
		
//define Users object
function Users(userName,passWord,userType) {
	this.id=0;
	this.username = userName;
	this.passkey = passWord;
	this.userType = userType;
}

function swapType(i){
	switch(i){
	case 1:
		document.getElementById("addUserToggle").checked=true;
		document.getElementById("editUserToggle").checked=false;
		document.getElementById("uNameDropdownDiv").style.display="none";
		document.getElementById("uNameDiv").style.display="block";
		document.getElementById("addUser").style.display="block";
		document.getElementById("editUser").style.display="none";
		document.getElementById("removeUser").style.display="none";
		break;
	case 2:
		document.getElementById("addUserToggle").checked=false;
		document.getElementById("editUserToggle").checked=true;
		document.getElementById("uNameDiv").style.display="none";
		document.getElementById("uNameDropdownDiv").style.display="block";
		document.getElementById("addUser").style.display="none";
		document.getElementById("editUser").style.display="block";
		document.getElementById("removeUser").style.display="block";
		break;
	}
}

//Get input from page
function addUser() {
	
	var radios = document.getElementsByName("userType");
	var uType="none";
	for (var i = 0; i < radios.length; i++) {
    	if (radios[i].checked) {
	        var uType = radios[i].value;
	        // only one radio can be logically checked, don't check the rest
	        break;
    	}
	}//radio loop
	
	var uName = $("#uName").val();

	var pWord0 = $("#pWord").val();

	var pWord1 = $("#pWordConf").val();
	
	
		if(uName.length==0){
			alert("Choose a longer user name");
		}
		else{
			if(pWord0==pWord1){
				if(pWord0.length<6){
					alert("Password must be at least 6 characters");
				}
				else{
					var pWord = pWord0;
					var user = new Users(uName, pWord, uType);
					call(user);
				}
			}else{
				alert("Passwords do not match");
			}
	
		}

}

//Get input from page
function editUser() {
	
	var radios = document.getElementsByName("userType");
	var uType="none";
	for (var i = 0; i < radios.length; i++) {
    	if (radios[i].checked) {
	        var uType = radios[i].value;
	        // only one radio can be logically checked, don't check the rest
	        break;
    	}
	}//radio loop
	
	var uName = $("#uNameDropdown").select2('data')[0].text;

	var pWord0 = $("#pWord").val();

	var pWord1 = $("#pWordConf").val();
	
	
	if(uName.length==0){
		alert("Choose a longer user name");
	}else{
		if(pWord0==pWord1){
			if(pWord0.length<6){
				alert("Password must be at least 6 characters");
			}
			else{
				var pWord = pWord0;
				var user = new Users(uName, pWord, uType);
				$.ajax({
					type : 'PUT',
					url : '../rest/users',
					dataType: "json",
					contentType: 'application/json',
					data: JSON.stringify(user),
					success: function(){
						alert(" User Edited ");
					},
				}); 
			}
		}else{
			alert("Passwords do not match");
		}
	
	}

}

function removeUser(){
	if($("#uNameDropdown").select2('data')[0].text=="admin"){
		alert("Admin User Cannot be removed!");
		return;
	}
	$.ajax({
		type : 'DELETE',
		url : '../rest/users/'+$("#uNameDropdown").select2('data')[0].text,
		success: function(){
			alert(" User Deleted ");
			populateUsernameDropdown()
		},
	}); 
	
}

function call(user){
	
	$.ajax({
		type : 'GET',
		url : '../rest/users',
		success : function(data) {
			for (i = 0; i < data.usersCollection.length; i++) {
				var add=true;
				var opt = data.usersCollection[i].username;
				if(opt == user.username){
					alert("User Already Exists");
					add=false;
					break;
				}
			}
			if(add){
				$.ajax({
					type : 'POST',
					url : '../rest/users',
					dataType: "json",
					contentType: 'application/json',
					data: JSON.stringify(user),
					success: function(){
						alert(" User added ");
					},
				}); 
			}
			populateUsernameDropdown();
		},
	});
	
	
}	

function populateUsernameDropdown() {
	$("#uNameDropdown").empty();
	$.ajax({
		type : 'GET',
		url : '../rest/users',
		success : function(data) {
			for (i = 0; i < data.usersCollection.length; i++) {
				var opt = data.usersCollection[i].username;
				var text = "<option value=\""+i+"\">" + opt
						+ "</option>";
				$("#uNameDropdown").append(text);
			}
			var $example = $("#uNameDropdown").select2();
			$example.val("0").trigger("change");
		},
	});
}
	
	
		 
	</script>
</body>
</html>