<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">

<title>Query</title>
<link rel="stylesheet" href="media/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/syntax/shCore.css">
<link rel="stylesheet" type="text/css" href="resources/demo.css">
<link rel="stylesheet" href="media/css/bootstrap-table.css">
<link href="media/css/bootstrap-datetimepicker.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="media/css/select2.min.css">
<link rel="stylesheet" type="text/css" href="media/css/mainPage.css">
<!--  link href="example.css" rel="stylesheet" type="text/css"-->
<style type="text/css">

	#placeholder {
		width: 550px;
		height: 400px;
	}
	
	.demo-placeholder {
	font-size: 14px;
	line-height: 1.2em;
	}
	
	a {	color: #069; }
	a:hover { color: #28b; }
	
	.legend table {
		border-spacing: 5px;
	}

</style>
<script type="text/javascript" language="javascript" src="media/js/jquery.js"></script>
<script type="text/javascript" language="javascript" src="resources/syntax/shCore.js"></script>
<script type="text/javascript" src="media/js/bootstrap.min.js"></script>
<script type="text/javascript" src="media/js/bootstrap-table.js"></script>
<script type="text/javascript" src="media/js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="media/js/transition.js"></script>
<script type="text/javascript" src="media/js/moment-with-locales.js"></script>
<script type="text/javascript" src="media/js/collapse.js"></script>
<script type="text/javascript" src="media/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" language="javascript" class="init"></script>
<script type="text/javascript" src="media/js/select2.full.js"></script>
<script language="javascript" type="text/javascript" src="media/js/jquery.flot.js"></script>
<script language="javascript" type="text/javascript" src="media/js/jquery.flot.pie.js"></script>
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
					<li style="font-size: 1.8em;"><a href="#" id="logintype" class="navbar-brand"><span></span> Hello</a></li>
					<li style="font-size: 1.8em;">
						<a href="logout">
							<span class="glyphicon glyphicon-log-out"></span> Logout
						</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="dropdown">
		<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
			<b>Select Query</b> <span class="caret"></span>
		</button>
		<ul class="dropdown-menu">
			<li><a id="availQuery1" onClick=selectQuery(1)>Display Event ID and CauseCodes for given IMSI</a></li>
			<li><a id="availQuery14" onClick=selectQuery(14)>Display Unique CauseCodes for given IMSI</a></li>
			<li><a id="availQuery9" onClick=selectQuery(9)>Display failure count for given IMSI during a time period.</a></li>
			<shiro:hasAnyRoles name="SupEng, NetManEng">
			<li><a id="availQuery2" onClick=selectQuery(2)>Display all IMSIs with call failures during a period</a></li>
			<li><a id="availQuery3" onClick=selectQuery(3)>Display count of call failures for a given model of phone, during time period</a></li>
			<li><a id="availQuery16" onClick=selectQuery(16)>Display IMSIs for a given failure Cause Class</a></li>
			<shiro:hasRole name="NetManEng">
			<li><a id="availQuery4" onClick=selectQuery(4)>Display count, for each IMSI, the number of call failures and their total duration during a time period</a></li>
			<li><a id="availQuery5" onClick=selectQuery(5)>Display, for a given model of phone, all the unique failure Event Id and Cause Code combinations and the number of occurrences</a></li>
			<li><a id="availQuery12" onClick=selectQuery(12)>Display Top 10 Market/Operator/Cell ID combinations with call failures during a time period</a></li>			 
			<li><a id="availQuery15" onClick=selectQuery(15)>Display top 10 IMSIs with call failures during a time period</a></li>
			
			</shiro:hasRole>
			</shiro:hasAnyRoles>
		</ul>
	</div>
	<br>
	<br>
	<div class="col-lg-12">
		<div class="panel panel-primary">
			<div id="phead" class="panel-heading">
				<h4>Currently Selected Query: <u id="selectedquery"></u></h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in">
				<div class="panel-body" style="font-size: 15px;">
					<div class="container" id="datetimepickers">
						<div class='col-md-6'>
							<div class="form-group">
								<label for="fromdatetimepicker">From:</label>
								<div class='input-group date' id='fromdatetimepicker'>
									<input id="datefrom" type='text' class="form-control" /> 
									<span class="input-group-addon"> 
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class='col-md-6'>
							<div class="form-group">
								<label for="todatetimepicker">To:</label>
								<div class='input-group date' id='todatetimepicker'>
									<input id="dateto" type='text' class="form-control" /> 
									<span class="input-group-addon"> 
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="container" id="tacPicker">
						<div class='col-lg-4 col-md-4'>
							<div class="form-group">
								<label for="manufacturerDropdown">Choose Manufacturer:</label> 
								<select id="manufacturerDropdown" class="js-example-responsive" style="width: 300px">
								</select>
							</div>
						</div>
						<div class='col-lg-4 col-md-4'>
							<div class="form-group">
								<label for="modelDropdown">Choose Model:</label> 
								<select id="modelDropdown" class="js-example-responsive" style="width: 300px">
								</select>
							</div>
						</div>
					</div>
					<div class="container" id="imsiPicker">
						<label for="imsiDropdown">Choose IMSI:</label> 
						<select id="imsiDropdown" class="js-example-responsive" style="width: 400px">
						</select>
					</div>
					<div class="container" id="failurePicker">
						<label for="failureDropdown">Choose Failure Class:</label> 
						<select id="failureDropdown" class="js-example-responsive" style="width: 400px">
						</select>
					</div>
					<button id="query1" type="button" class="btn btn-primary">Run Query</button>
				</div>
			</div>
		</div>
	</div>
	<br />
	<br />
	<div class="col-lg-12">
		<div class="panel panel-primary">
			<div id="phead2" class="panel-heading">
				<h4>Query Results</h4>
			</div>
			<div id="collapseTwo" class="collapse">
				<div class="panel-body" style="font-size: 15px;">
					<div id="resultsDiv"
						style="overflow: auto; height: auto; max-height: 300px; display: none;">
						<table id="dataTable" class="table tablesorter tablesorter-default table-striped table-bordered">
						</table>
					</div>
				</div>
				<div class="panel-footer" style="font-size: 15px;">
					<p id="count"></p>
					<button id="graph_button" type="button" class="btn btn-primary">Look at this Graph!</button>
				</div>
			</div>
		</div>
	</div>
	<br />
	<br />

	<!--  div class="demo-container"-->	
	<div class="container">
		<div id="placeholder" ></div>
	</div>
	<!--  /div -->
	<p id = "percent"></p>
	<script>
		var selectedQuery=1;
		var queryType="GET";
		selectQuery(1);
		
		$(document).ready(function() {
			$(function() {
				$(".js-example-responsive").select2();
				$("#dataTable").tablesorter();
				populateManufacturerDropdown();
				
				populateImsiDropdown();
				populateFailureDropdown();
				setUserDetails();
			});
		});

		$(function() {
			$('#fromdatetimepicker').datetimepicker({
				locale : 'en-gb'
			});
			$('#todatetimepicker').datetimepicker({
				useCurrent : false,
				locale : 'en-gb'
			});
			
			$("#fromdatetimepicker").on("dp.change", function(e) {
				$('#todatetimepicker').data("DateTimePicker").minDate(e.date);
			});
			$("#todatetimepicker").on("dp.change", function(e) {
				$('#fromdatetimepicker').data("DateTimePicker").maxDate(e.date);
			});
			$('#fromdatetimepicker').data("DateTimePicker").date(new Date());
			$('#todatetimepicker').data("DateTimePicker").date(new Date());
		});

		function setUserDetails() {
			$.ajax({
				type : 'GET',
				url : 'rest/users/currentUser',
				success : function(data) {
					var userBar = document.getElementById("userBar");
					userBar.innerHTML = "Priviledge type: " + data[1];
					var loginType = document.getElementById("logintype");
					loginType.innerHTML = "<span></span>Logged in as: "
							+ data[0];
				},
			});
		}

		$("#manufacturerDropdown").on("change",function(e) {
			$("#modelDropdown").empty();
			if($("#manufacturerDropdown").select2('data').length>0){
				$.ajax({
						type : 'GET',
						url : 'rest/uetables/models/'
								+ $("#manufacturerDropdown").select2(
										'data')[0].text,
						success : function(data) {
							for (i = 0; i < data.length; i++) {
								var opt = data[i];
								var text = "<option value=\""+i+"\">"
										+ opt + "</option>";
								$("#modelDropdown").append(text);
							}
							var $example = $("#modelDropdown")
									.select2();
							$example.val("0").trigger("change");
						},
					});
			}
		});

		function populateManufacturerDropdown() {
			$("#manufacturerDropdown").empty();
			$.ajax({
				type : 'GET',
				url : 'rest/uetables/manufacturers',
				success : function(data) {
					for (i = 0; i < data.length; i++) {
						var opt = data[i];
						var text = "<option value=\""+i+"\">" + opt
								+ "</option>";
						$("#manufacturerDropdown").append(text);
					}
					var $example = $("#manufacturerDropdown").select2();
					$example.val("0").trigger("change");
				},
			});
		}
		
		function populateImsiDropdown(){
			$("#imsiDropdown").empty();
			$.ajax({
				type : 'GET',
				url : 'rest/validdata/imsis',
				success : function(data){
					for(i=0;i<data.length;i++){
						var opt = data[i];  
						var text = "<option value=\""+i+"\">"+opt+"</option>";
						$("#imsiDropdown").append(text);
					}
					var $example = $("#imsiDropdown").select2();
					$example.val("0").trigger("change");
				},
			});
		}
		
		function populateFailureDropdown(){
			$("#failureDropdown").empty();
			$.ajax({
				type : 'GET',
				url : 'rest/validdata/failure',
				success : function(data){
					for(i=0;i<data.length;i++){
						var opt = data[i];  
						var text = "<option value=\""+i+"\">"+opt+"</option>";
						$("#failureDropdown").append(text);
					}
					var $example = $("#failureDropdown").select2();
					$example.val("0").trigger("change");
				},
			});
		}
		
		

		$("#query1").click(function() {
			$('#collapseOne').collapse("hide");
			$('#collapseTwo').collapse('show');
			setTimeout(getData, 300);
		});

		
		function getData() {
			var fromdate = $("#fromdatetimepicker").data("DateTimePicker").date();
			var todate = $("#todatetimepicker").data("DateTimePicker").date();
			var dates = [];
			var queryUrl='rest/validdata/CB-'+(selectedQuery+3);
			var inputData=[];
			
			var ajaxDetails={
				type : queryType,
				url : queryUrl,
				dataType : null,
				contentType : null,
				data : null,
			}
			
			switch(selectedQuery){
			case 1:
				queryUrl+="/"+$("#imsiDropdown").select2('data')[0].text;
				break;
			case 2:
				inputData.push(fromdate.format("YYYY-MM-DD HH:mm"));
				inputData.push(todate.format("YYYY-MM-DD HH:mm"));
				break;
			case 3:
				if($("#manufacturerDropdown").select2('data').length>0){
					inputData.push($("#manufacturerDropdown").select2('data')[0].text);
				}
				else{
					inputData.push(" ");
				}
				if($("#modelDropdown").select2('data').length>0){
					inputData.push($("#modelDropdown").select2('data')[0].text);
				}
				else{
					inputData.push(" ");
				}
				inputData.push(fromdate.format("YYYY-MM-DD HH:mm"));
				inputData.push(todate.format("YYYY-MM-DD HH:mm"));
				break;
			case 4:
				inputData.push(fromdate.format("YYYY-MM-DD HH:mm"));
				inputData.push(todate.format("YYYY-MM-DD HH:mm"));
				break;
			case 5:
				if($("#manufacturerDropdown").select2('data').length>0){
					inputData.push($("#manufacturerDropdown").select2('data')[0].text);
				}
				else{
					inputData.push(" ");
				}
				if($("#modelDropdown").select2('data').length>0){
					inputData.push($("#modelDropdown").select2('data')[0].text);
				}
				else{
					inputData.push(" ");
				}
				break;
			case 9:
				if($("#imsiDropdown").select2('data').length>0){
					inputData.push($("#imsiDropdown").select2('data')[0].text);
				}
				inputData.push(fromdate.format("YYYY-MM-DD HH:mm"));
				inputData.push(todate.format("YYYY-MM-DD HH:mm"));
				break;				
			case 12:
				inputData.push(fromdate.format("YYYY-MM-DD HH:mm"));
				inputData.push(todate.format("YYYY-MM-DD HH:mm"));
				break;
			case 14:
				queryUrl+="/"+$("#imsiDropdown").select2('data')[0].text;
				break;
			case 15:
				inputData.push(fromdate.format("YYYY-MM-DD HH:mm"));
				inputData.push(todate.format("YYYY-MM-DD HH:mm"));
				break;
			case 16:  // NEW DROPDOWN !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				queryUrl+="/"+$("#failureDropdown").select2('data')[0].text;
				break;	
			}
			if(queryType=="POST"){
				ajaxDetails.data=JSON.stringify(inputData);
				ajaxDetails.dataType="json";
				ajaxDetails.contentType='application/json';
			}
		
			$.ajax({
				type : queryType,
				url : queryUrl,
				dataType : ajaxDetails.dataType,
				contentType : ajaxDetails.contentType,
				data : ajaxDetails.data,
				success : function(data) {
					$("#resultsDiv").empty();
					var table = $("<table id='dataTable' class='table tablesorter tablesorter-default table-striped table-bordered'>");
					
					$("#resultsDiv").append(table);
						document.getElementById("count").innerHTML = "Count ="
							+ (data.dataCollection.length - 1);
					var columnTitles = [];
					for (j = 0; j < data.dataCollection[0].length; j++) {
						columnTitles.push({
							title : "" + data.dataCollection[0][j],
							field : "" + data.dataCollection[0][j],
							align : 'center',
							valign : 'middle',
							sortable : true
						});
					}
	
					$('#dataTable').bootstrapTable({
						columns : columnTitles
					});
	
					var arr = [];
					for (i = 1; i < data.dataCollection.length; i++) {
						var rowData = '{';
						for (j = 0; j < data.dataCollection[i].length - 1; j++) {
							rowData += '"' + data.dataCollection[0][j]
									+ '" : "'
									+ data.dataCollection[i][j] + '",';
						}
						rowData += '"'
								+ data.dataCollection[0][data.dataCollection[i].length - 1]
								+ '" : "'
								+ data.dataCollection[i][data.dataCollection[i].length - 1]
								+ '"';
						rowData += '}';
						arr.push(JSON.parse(rowData));
					}
					$('#dataTable').bootstrapTable('load', arr);
					$("#dataTable").trigger("update");
					document.getElementById("resultsDiv").style.display = "block";
					document.getElementById("collapseTwo")
							.scrollIntoView();
				}
			});

		}
		
		$("#empty").click(function clear() {
			var $example = $("#modelDropdown").select2();
			$example.val("3").trigger("change");
		});

		$('#phead').click(function(e) {
			$('#collapseOne').collapse('toggle');
		});

		$('#phead2').click(function(e) {
			$('#collapseTwo').collapse('toggle');
		});
		
		function selectQuery(i){
			var dateTimePickers=document.getElementById("datetimepickers");
			var tacPickers=document.getElementById("tacPicker");
			var imsiPickers=document.getElementById("imsiPicker");
			var failurePickers=document.getElementById("failurePicker");
			selectedQuery=i;
			document.getElementById("selectedquery").innerHTML=document.getElementById("availQuery"+selectedQuery).innerHTML;
			
			tacPickers.style.display="none";
			dateTimePickers.style.display="none";
			imsiPickers.style.display="none";
			failurePickers.style.display="none";
			
			switch(selectedQuery){
			case 1:
				queryType="GET";
				imsiPickers.style.display="block";
				break;
			case 2:
				dateTimePickers.style.display="block";
				queryType="POST";
				break;
			case 3:
				queryType="POST";
				dateTimePickers.style.display="block";
				tacPickers.style.display="block"
				break;
			case 4:
				dateTimePickers.style.display="block";
				queryType="POST";
				break;
			case 5:
				queryType="POST";
				tacPickers.style.display="block"
				break;				
			case 9:
				queryType="POST";
				imsiPickers.style.display="block";
				dateTimePickers.style.display="block";
				break;
			case 12:
				//queryType="GET";
				dateTimePickers.style.display="block";
				queryType="POST";
				break;				
			case 14:
				queryType="GET";
				imsiPickers.style.display="block";
				break;
			case 15:
				dateTimePickers.style.display="block";
				queryType="POST";
				break;
			case 16:
				failurePickers.style.display="block";
				queryType="GET";
				break;
			}
		}
		
		$("#graph_button").click(function(e) {

			var data = [
			 //data got from something like: document.getElementById("count").innerHTML
			 //label got from: 
				{ label: "Series1",  data: 3599 },
				{ label: "Series2",  data: 3494 },
				{ label: "Series3",  data: 3411 },
				{ label: "Series4",  data: 3239 },
				{ label: "Series5",  data: 3164 },
				{ label: "Series6",  data: 3103 },
				{ label: "Series7",  data: 2951 },
				{ label: "Series8",  data: 2652 },
				{ label: "Series9",  data: 1217 },
				{ label: "Series10",  data: 1154 }
			];	


			var placeholder = $("#placeholder");


			$.plot(placeholder, data, {
				series: {
					pie: { 
						show: true
					}
				},
				grid: {
					hoverable: true,
					clickable: true
				}
			});

			setCode([
				"$.plot('#placeholder', data, {",
				"    series: {",
				"        pie: {",
				"            show: true",
				"        }",
				"    },",
				"    grid: {",
				"        hoverable: true,",
				"        clickable: true",
				"    }",
				"});"
			]);

			placeholder.bind("plothover", function(event, pos, obj) {

				if (!obj) {
					return;
				}

				var percent = parseFloat(obj.series.percent).toFixed(2);
				$("#hover").html("<span style='font-weight:bold; color:" + obj.series.color + "'>" + obj.series.label + " (" + percent + "%)</span>");
			});

			placeholder.bind("plotclick", function(event, pos, obj) {

				if (!obj) {
					return;
				}

				percent = parseFloat(obj.series.percent).toFixed(2);
				document.getElementById("percent").innerHTML = ""  + obj.series.label + ": " + percent + "%";
			});
		});

		function labelFormatter(label, series) {
			return "<div style='font-size:8pt; text-align:center; padding:2px; color:white;'>" + label + "<br/>" + Math.round(series.percent) + "%</div>";
		}

		function setCode(lines) {
			$("#code").text(lines.join("\n"));
		}
	</script>
</body>
</html>