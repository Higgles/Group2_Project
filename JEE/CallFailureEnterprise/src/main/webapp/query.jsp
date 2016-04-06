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
		width: 800px;
		height: 400px;
	}
	
	.demo-placeholder {
	font-size: 14px;
	line-height: 1.2em;
	}
	
	a {	color: #069; }
	a:hover { color: #28b; }
	
	.legend table {
		position: relative;
		width: 300px;
		border-spacing: 5px;
		font-size: 20px;
	}
	
	@media screen and (min-width: 768px) {
        .modal-dialog {
          width: 800px;
        }
        .modal-sm {
          width: 400px;
        }
    }
    @media screen and (min-width: 992px) {
        .modal-lg {
          width: 950px;
        }
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
			<li><a id="availQuery1" onClick=selectQuery(1)>Display Event IDs and Cause Codes for a given IMSI</a></li>
			<li><a id="availQuery14" onClick=selectQuery(14)>Display unique Event IDs and Cause Codes for a given IMSI</a></li>
			<li><a id="availQuery9" onClick=selectQuery(9)>Display number of failures for a given IMSI during a time period.</a><br></li>
			<shiro:hasAnyRoles name="SupEng, NetManEng">
			<li><a id="availQuery2" onClick=selectQuery(2)>Display IMSIs with failures during a time period</a></li>
			<li><a id="availQuery3" onClick=selectQuery(3)>Display number of failures for a given model of phone, during a time period</a></li>
			<li><a id="availQuery16" onClick=selectQuery(16)>Display IMSIs for a given Failure Class</a><br></li>
			<shiro:hasRole name="NetManEng">
			<li><a id="availQuery4" onClick=selectQuery(4)>Display the number of failures and their total duration during a time period for each IMSI</a></li>
			<li><a id="availQuery5" onClick=selectQuery(5)>Display the unique Event ID and Cause Code combinations and the number of occurrences for a given model of phone</a></li>
			<li><a id="availQuery12" onClick=selectQuery(12)>Display top 10 Market/Operator/Cell ID combinations with failures during a time period</a></li>			 
			<li><a id="availQuery15" onClick=selectQuery(15)>Display top 10 IMSIs with failures during a time period</a></li>
			
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
								<select id="manufacturerDropdown" class="js-data-example-ajax" style="width: 300px">
								</select>
							</div>
						</div>
						<div class='col-lg-4 col-md-4'>
							<div class="form-group">
								<label for="modelDropdown">Choose Model:</label> 
								<select id="modelDropdown" class="js-data-example-ajax" style="width: 300px">
								</select>
							</div>
						</div>
					</div>
					<div class="container" id="imsiPicker">
						<label for="imsiDropdown">Choose IMSI:</label> 
						<select id="imsiDropdown" class="js-data-example-ajax" style="width: 400px">
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
				<h4 id="selectedquery2"></h4>
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
<button id="graph_button" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" onclick="clearPercent()">Look at this Graph</button>
				</div>
			</div>
		</div>
	</div>
	<br />
	<br />


		
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
	
	<div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Look at this Graph</h4>
      </div>
      <div class="modal-body">
        <p>Look at this graph</p>
        <div id="placeholder" ></div>
      </div>
      <div class="modal-footer">
      	<div class="col-lg-10">
      		<p align="left"><b id = "percent"></b></p>
      	</div>
      	<div class="col-lg-2">
      		<button type="button" class="btn btn-default" data-dismiss="modal" >Close</button>
      	</div>
      </div>
    </div>

  </div>
</div>
	
	<script>
		const QUERYPAGELIMIT = 20;
		var selectedQuery=1;
		var queryType="GET";
		selectQuery(1);
		
		$(document).ready(function() {
			$(function() {
				$(".js-example-responsive").select2();
				$("#dataTable").tablesorter();
				populateFailureDropdown();
				setUserDetails();
			});
		});
		
		$("#imsiDropdown").select2({
			placeholder: 'IMSI',
			ajax: {
				type : 'GET',
				url: 'rest/validdata/imsis',
				dataType: 'json',
				delay: 250,
				data: function(params){
					return{
						term: params.term || '',
			            page: params.page || 1,
			            pageLimit: QUERYPAGELIMIT
					};
				},
				processResults: function (data, params){
					
					params.page = params.page || 1;
					
					return {
						results: $.map(data, function(item){
							return {
								text: item,
								id: item
							}
						}),
						pagination: {
							more:  data.length == QUERYPAGELIMIT
						}
					};
				}
			}
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
		
		function clearPercent() {
			document.getElementById("percent").innerHTML = "";
		}

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
		
		$("#manufacturerDropdown").select2({
			placeholder: 'Manufacturer',
			ajax: {
				type : 'GET',
				url: 'rest/uetables/manufacturers',
				dataType: 'json',
				delay: 250,
				data: function(params){
					return{
						term: params.term || '',
			            page: params.page || 1,
			            pageLimit: QUERYPAGELIMIT
					};
				},
				processResults: function (data, params){
					
					params.page = params.page || 1;
					
					return {
						results: $.map(data, function(item){
							return {
								text: item,
								id: item
							}
						}),
						pagination: {
							more:  data.length == QUERYPAGELIMIT
						}
					};
				}
			}
		});
		
		$("#modelDropdown").select2({
			placeholder: 'Model',
			ajax: {
				type : 'GET',
				url : function(){
					if($("#manufacturerDropdown").select2('data').length>0)
						return 'rest/uetables/models/'+ $("#manufacturerDropdown").select2('data')[0].text;
					else{
						return 'rest/uetables/models/';
					}
				},
				dataType: 'json',
				delay: 250,
				data: function(params){
					return{
						term: params.term || '',
			            page: params.page || 1,
			            pageLimit: QUERYPAGELIMIT
					};
				},
				processResults: function (data, params){
					
					params.page = params.page || 1;
					
					return {
						results: $.map(data, function(item){
							return {
								text: item,
								id: item
							}
						}),
						pagination: {
							more:  data.length == QUERYPAGELIMIT
						}
					};
				}
			}
		});
		
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
		
		$("#manufacturerDropdown").on("change",function(e) {
			$("#modelDropdown").val('').trigger('change');	
		});

		$("#query1").click(function() {
			validate();
		});

		function validate(){
			switch(selectedQuery){
			case 1:
				if($("#imsiDropdown").select2('data').length<1){
					alert("Select Imsi value");
				}
				else{
					showData();
				}
				break;
			case 2:
					showData();
				break;
			case 3:
				if($("#manufacturerDropdown").select2('data').length<1){
					alert("Choose Manufacturer");
				}
				else if($("#modelDropdown").select2('data').length<1){
					alert("Choose Model");
				}
				else{
					showData();
				}
				break;
			case 4:
				showData();
				break;
			case 5:
				if($("#manufacturerDropdown").select2('data').length<1){
					alert("Choose Manufacturer");
				}
				else if($("#modelDropdown").select2('data').length<1){
					alert("Choose Model");
				}
				else{
					showData();
				}
				break;
			case 9:
				if($("#imsiDropdown").select2('data').length<1){
					alert("Select Imsi value");
				}
				else{
					showData();
				}
				break;
			case 12:
				showData();
				break;
			case 14:
				if($("#imsiDropdown").select2('data').length<1){
					alert("Select Imsi value");
				}
				else{
					showData();
				}
				break;
			case 15:
				showData();
				break;
			case 16:
				if($("#failureDropdown").select2('data').length<1){
					alert("Select Failure Class");
				}
				else{
					showData();
				}
				break;
			}
		}
		
		function showData(){
			$('#collapseOne').collapse("hide");
			$('#collapseTwo').collapse('show');
			setTimeout(getData, 300);
		}
		
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
				var manufacturer = $("#manufacturerDropdown").select2('data')[0].text
				var model = $("#modelDropdown").select2('data')[0].text;
				inputData.push(manufacturer);
				inputData.push(model);
				inputData.push(fromdate.format("YYYY-MM-DD HH:mm"));
				inputData.push(todate.format("YYYY-MM-DD HH:mm"));
				break;
			case 4:
				inputData.push(fromdate.format("YYYY-MM-DD HH:mm"));
				inputData.push(todate.format("YYYY-MM-DD HH:mm"));
				break;
			case 5:
				inputData.push($("#manufacturerDropdown").select2('data')[0].text);
				inputData.push($("#modelDropdown").select2('data')[0].text);
				break;
			case 9:
				inputData.push($("#imsiDropdown").select2('data')[0].text);
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
			case 16:  
				queryUrl+="/"+$("#failureDropdown").select2('data')[0].text;
				break;	
			}
			
			queryTitle(selectedQuery);
			
			
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
					if(data.dataCollection.length - 1 === 0){
						document.getElementById("graph_button").style.display="none";
					}
					else{
						if(selectedQuery == 12){
							document.getElementById("graph_button").style.display="block";
						}
					}
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
		
		function queryTitle(selectedQuery){
			var title = document.getElementById("selectedquery2");
			var fromdate = $("#fromdatetimepicker").data("DateTimePicker").date();
			var todate = $("#todatetimepicker").data("DateTimePicker").date();
			switch(selectedQuery){
			case 1:
				title.innerHTML = "Event ID and Cause Codes for IMSI: " + $("#imsiDropdown").select2('data')[0].text;
				break;
			case 2:
				title.innerHTML = "IMSIs with failures between " + fromdate.format("DD-MM-YYYY HH:mm") + 
				" and " + todate.format("DD-MM-YYYY HH:mm");
				break;
			case 3:
				var manufacturer = $("#manufacturerDropdown").select2('data')[0].text
				var model = $("#modelDropdown").select2('data')[0].text;
				title.innerHTML = "Number of failures for " + manufacturer + " " + model 
				+ " between " + fromdate.format("DD-MM-YYYY HH:mm") + " and " + todate.format("DD-MM-YYYY HH:mm"); 
				break;
			case 4:
				title.innerHTML = "Number of failures and duration, for each IMSI, between " 
				+ fromdate.format("DD-MM-YYYY HH:mm") + " and " + todate.format("DD-MM-YYYY HH:mm"); 
				break;
			case 5:
				title.innerHTML = "Unique Event ID and Cause Code combinations for " +
				$("#manufacturerDropdown").select2('data')[0].text + " " + $("#modelDropdown").select2('data')[0].text;
				break;
			case 9:
				title.innerHTML = "Number of failures for IMSI: " + $("#imsiDropdown").select2('data')[0].text + " between " 
				+ fromdate.format("DD-MM-YYYY HH:mm") + " and " + todate.format("DD-MM-YYYY HH:mm"); 
				break;				
			case 12:
				title.innerHTML = "Top 10 Market/Operator/Cell ID combinations with failures between " + fromdate.format("DD-MM-YYYY HH:mm") + 
				" and " + todate.format("DD-MM-YYYY HH:mm");
				break;
			case 14:
				title.innerHTML = "Unique Event IDs and Cause Codes for IMSI: " + $("#imsiDropdown").select2('data')[0].text;
				break;
			case 15:
				title.innerHTML = "Top 10 IMSIs with failures between "
				+ fromdate.format("DD-MM-YYYY HH:mm") + " and " + todate.format("DD-MM-YYYY HH:mm"); 
				break;
			case 16:  
				title.innerHTML = "IMSIs for Failure Class: " + $("#failureDropdown").select2('data')[0].text;
				break;	
			default:
				title.innerHTML = " No Query Selected ";
			}
			
		}

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
			var graphButton = document.getElementById("graph_button");
			selectedQuery=i;
			document.getElementById("selectedquery").innerHTML=document.getElementById("availQuery"+selectedQuery).innerHTML;
			
			tacPickers.style.display="none";
			dateTimePickers.style.display="none";
			imsiPickers.style.display="none";
			failurePickers.style.display="none";
			graphButton.style.display = "none";
			
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
				tacPickers.style.display="block";
				break;
			case 4:
				dateTimePickers.style.display="block";
				queryType="POST";
				break;
			case 5:
				queryType="POST";
				tacPickers.style.display="block";
				break;				
			case 9:
				queryType="POST";
				imsiPickers.style.display="block";
				dateTimePickers.style.display="block";
				graphButton.style.display="none";
				break;
			case 12:
				dateTimePickers.style.display="block";
				queryType="POST";
				graphButton.style.display="block";
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

		var jsonArray = $("#dataTable").bootstrapTable('getData');

		var data = [];

		for(var i = 0; i < jsonArray.length; i++){

		data.push({label: jsonArray[i].Market+" "+jsonArray[i].Operator+" "+jsonArray[i]["Cell Id"], data: jsonArray[i].Count });
	
		}	 

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
			document.getElementById("percent").innerHTML = ""  + obj.series.label + ": " + percent + "% "+ obj.series.data.toString().split(",").pop();
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