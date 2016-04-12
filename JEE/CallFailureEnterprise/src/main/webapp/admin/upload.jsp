<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <title>Dataset Upload</title>

    <link rel="stylesheet" href="../media/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../resources/syntax/shCore.css">
	<link rel="stylesheet" type="text/css" href="../resources/demo.css">
	<link rel="stylesheet" href="../media/css/bootstrap-table.css">
	<link rel="stylesheet" type="text/css" href="../media/css/select2.min.css">
	<link rel="stylesheet" type="text/css" href="../media/css/mainPage.css">
	<style type="text/css" class="init"></style>
	<script type="text/javascript" language="javascript" src="../media/js/jquery.js"></script>
	<script type="text/javascript" language="javascript" src="../resources/syntax/shCore.js"></script>
	<script type="text/javascript" src="../media/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../media/js/transition.js"></script>
	<script type="text/javascript" src="../media/js/moment-with-locales.js"></script>
	<script type="text/javascript" src="../media/js/collapse.js"></script>
	<script type="text/javascript" language="javascript" class="init"></script>
	<script type="text/javascript" src="../media/js/select2.full.js"></script>
	<script type="text/javascript" src="../media/js/jquery.form.js"></script>

    <!-- Custom styles for this template -->
    <link href="upload.css" rel="stylesheet">

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
				<a class="navbar-brand" href="#" id="userBar"></a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li style="font-size: 1.8em;"><a href="addUser.jsp" class="navbar-brand"><span></span> Add/Edit Users</a></li>
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
				<h4>Dataset Upload</h4>
			</div>
			<div class="panel-body" style="font-size: 15px;">
				<form id="uploadForm" action="dummy.php" class="upload" enctype="multipart/form-data">
			       <p>  
			       <input id="uploadFile" type="file" name="uploadFile" button class="btn btn-primary" />
			       </p>  
			       <input id="upload-button" type="button" value="Upload File (xls only)" button class="btn btn-lg btn-primary" />
					
				</form>
				<div align="center">
					<h3 id="uploadStatus" style="display: none;"></h3>
				</div>
			    <div id="upBar" align="center" style="display: none">
				    <h4>Upload Progress</h4>
				    <div class="progress" style="width:50%">
					    <div id="uploadprogressbar" class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
					    	0%
					    </div>
				    </div>
				    
			    </div>
			    <div id="comBar" align="center" style="display: none">
			    	<h4>Update Tables Progress</h4>
				    <div class="progress" style="width:50%">
					    <div id="commitprogressbar" class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
					    	0%
					    </div>
				    </div>
			    </div>
			</div>
		</div>
	</div>
	<script>
		var filename="";
		$(document).ready(function() {
			$(function() {
				setUserDetails();
				var bar = $('#uploadprogressbar');
				var commitbar = $('#commitprogressbar');
				var status = $('#status');
				var options={					
					url: "../rest/file/upload",
					type: 'post',
					dataType: 'json',
					clearForm: true,
					resetForm: true,
					
					beforeSend: function(formData, jqForm, options) {
						document.getElementById("uploadStatus").style.display="none";
				        var percentVal = '0%';
				        bar.attr('aria-valuenow', percentVal).css('width',percentVal);
				        bar.html(percentVal);
				        
				        document.getElementById("upBar").style.display="inline";
				    },
				    uploadProgress: function(event, position, total, percentComplete) {
				        var percentVal = percentComplete + '%';
				        bar.attr('aria-valuenow', percentVal).css('width',percentVal);
				        bar.html(percentVal);
				    },
				    success: function(responseText, statusText, xhr, $form) {
				    	if(statusText!= "success"){
				    		alert("Invalid File Chosen! Please choose a valid excel file")
				    		document.getElementById("upBar").style.display="none";
				    		document.getElementById("comBar").style.display="none";
				    		document.getElementById("uploadStatus").style.display="inline";
							$('#uploadStatus').html("Upload Failed!");
				    	}
				    	else{
					        var percentVal = '100%';
					        bar.attr('aria-valuenow', percentVal).css('width',percentVal);
					        commitbar.attr('aria-valuenow', '0%').css('width','0%');
					        bar.html(percentVal);
					        commitbar.html("0%");
					        document.getElementById("comBar").style.display="inline";
					        filename = responseText.filename;
					        setTimeout(updateCommitBar, 5000);
				    	}
				    }
				    
				};
				
				
				$(this).ajaxForm(options);
			});
		});
		
		$('#upload-button').click(function(e){
			if($('#uploadFile').val() == ""){
				alert("Please Choose a File!");
			}
			else{
				$('#uploadForm').submit();
			}
		});
		
		function setUserDetails() {
			$.ajax({
				type : 'GET',
				url : '../rest/users/currentUser',
				success : function(data) {
					var userBar = document.getElementById("userBar");
					userBar.innerHTML = "Privilege type: " + data[1];
					var loginType = document.getElementById("logintype");
					loginType.innerHTML = "<span></span>Logged in as: "
							+ data[0];
				}
			});
		}
		
		function updateCommitBar(){
			$.ajax({
				type : 'GET',
				url : '../rest/folderWatcher?filename='+filename,
				success : function(data) {
					
					var percentVal = data[0]+'%';
					$('#commitprogressbar').attr('aria-valuenow', percentVal).css('width',percentVal);
					$('#commitprogressbar').html(percentVal);
					if(percentVal != '100%'){
						setTimeout(updateCommitBar, 3000);
					}
					else{
						document.getElementById("upBar").style.display="none";
						document.getElementById("comBar").style.display="none";
						document.getElementById("uploadStatus").style.display="inline";
						$('#uploadStatus').html("Upload Complete!");
					}
				},
			});
		}
	</script>
</body>
</html>