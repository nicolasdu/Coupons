
	var url="http://localhost:8080/Coupons/rest/adminServices/getallcustomers";
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
								
			        if(i==0) 
			        	var text = (res2[i]+"}");
			        else if(i==res2.length-1)
			        	var text = ("{"+res2[i]);	
			        else
			        	var text = ("{"+res2[i]+"}");
			        
				    var obj = JSON.parse(text);
			        cell1.innerHTML =obj.id;
			        cell1.id="1"+[i];
			        cell2.innerHTML = obj.custName;
			        cell2.id="2"+[i]; // + row
			        cell3.innerHTML = obj.password;
			        cell3.id="3"+[i];
			        cell4.innerHTML = obj.coupons;
			        cell4.id="4"+[i];
			    }
		}
		