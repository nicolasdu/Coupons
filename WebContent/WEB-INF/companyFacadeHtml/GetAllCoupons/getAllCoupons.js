	var url="http://localhost:8080/Coupons/rest/companyService/getallcoupons";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	       // Typical action to be performed when the document is ready:
	       console.log(xhttp.responseText);
	    }
	}
	xhttp.open("GET", url, true);
	xhttp.send();
	
	function numOfObjects(){
		
		var numofobjects=0;
		var result = xhttp.responseText;
		
		for(var i=0; i<result.length; i++)
			{
			if(result[i]=="{" && result[i+5]=="\"")
				{
					numofobjects++
				}
			}
			populateHTMLTable(result);	
	}

		function populateHTMLTable(result){
				
					var res= result.substr(4);
					
					var res2 = res.split("}{");
			
					insertQueriesIntoTable(res2);
					
	}
					
		function insertQueriesIntoTable(res2) {
		
		    for(var i=0;i<res2.length;i++)
		    {

			        var table = document.getElementById("myTable");
			        var row = table.insertRow(-1);
			        var cell1 = row.insertCell(0);
			        var cell2 = row.insertCell(1);
			        var cell3 = row.insertCell(2);
			        var cell4 = row.insertCell(3);
			        var cell5 = row.insertCell(4);
			        var cell6 = row.insertCell(5);
			        var cell7 = row.insertCell(6);
			        var cell8 = row.insertCell(7);
			        var cell9 = row.insertCell(8);
					
			        if(i==0) 
			        	var text = (res2[i]+"}");
			        else if(i==res2.length-1)
			        	var text = ("{"+res2[i]);	
			        else
			        	var text = ("{"+res2[i]+"}");
			   
				    var obj = JSON.parse(text);
			        cell1.innerHTML =obj.id;
			        cell1.id="1"+[i];
			        cell2.innerHTML = obj.title;
			        cell2.id="2"+[i]; // + row
			        cell3.innerHTML = obj.startDate;
			        cell3.id="3"+[i];
			        cell4.innerHTML = obj.endDate;
			        cell4.id="4"+[i];
			        cell5.innerHTML = obj.amount;
			        cell5.id="5"+[i];
			        cell6.innerHTML = obj.message;
			        cell6.id="6"+[i]; // + row
			        cell7.innerHTML = obj.price;
			        cell7.id="7"+[i];
			        cell8.innerHTML = obj.image;
			        cell8.id="8"+[i];
			        cell9.innerHTML = obj.type;
			        cell9.id="9"+[i];
			    }
		}
		