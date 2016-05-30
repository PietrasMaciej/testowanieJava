$(function() {
// link pod ktorym mozna zobaczyc dzialanie: http://localhost:8080/restservicedemo/
var listContainer = $('.container');
var listContainerTwo = $('.containerTwo');

function getPersons() {

    $.ajax({
    	 method: "GET",
		 url: "http://localhost:8080/restservicedemo/api/person/all",
         success: function (data) {
        	 console.log(data);
        	listContainer.append('<table class="table table-hover" border = "1"><tr><th>ID</th><th>Name</th><th>Yob</th></tr></table>');
        	var html = "";
        	 if(typeof(data.person.length) !== 'number') {
        		 html += '<tr>' + 
		 	    			'<td>' + data.person.id + '</td>' +
			 	    		'<td>' + data.person.firstName + '</td>' +
			 	    		'<td>' + data.person.yob + '</td>' +
			    		'</tr>';
             }
        	 for(var i = 0; i < data.person.length; i++) {
        	    html += '<tr>' + 
	        	    		'<td>' + data.person[i].id + '</td>' +
	        	    		'<td>' + data.person[i].firstName + '</td>' +
	        	    		'<td>' + data.person[i].yob + '</td>' +
        	    		'</tr>';
        	}
        	$(html).insertAfter(listContainer.find('tr'));
        }
    });
     
}

function getCars() {

    $.ajax({
    	 method: "GET",
		 url: "http://localhost:8080/restservicedemo/api/bike/all",
         success: function (data) {
        	 console.log(data);
        	 listContainerTwo.append('<table class="table table-hover" border = "1"><tr><th>ID</th><th>Make</th><th>Model</th><th>YOP</th><th>Owner</th></tr></table>');
        	var html = "";
        	 if(typeof(data.bike.length) !== 'number') {
        		 html += '<tr>' + 
		 	    			'<td>' + data.bike.id + '</td>' +
			 	    		'<td>' + data.bike.make + '</td>' +
			 	    		'<td>' + data.bike.model + '</td>' +
			 	    		'<td>' + data.bike.yop + '</td>' +
			 	    		'<td>' + data.bike.owner.id + '</td>' +
			    		'</tr>';
             }
        	 for(var i = 0; i < data.bike.length; i++) {
        	    html += '<tr>' + 
	        	    		'<td>' + data.bike[i].id + '</td>' +
	        	    		'<td>' + data.bike[i].make + '</td>' +
	        	    		'<td>' + data.bike[i].model + '</td>' +
	        	    		'<td>' + data.bike[i].yop + '</td>' +
	        	    		'<td>' + data.bike[i].owner.id + '</td>' +
        	    		'</tr>';
        	}
        	$(html).insertAfter(listContainerTwo.find('tr'));
        }
    });
     
}

getPersons();
getCars();





});