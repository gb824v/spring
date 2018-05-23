var responseHeaders = context.getVariable("response.headers.names");
var blackListedResHeaders = context.getVariable("blackListedResponseHeaders");

if (blackListedResHeaders !== null) {

    responseHeaders = responseHeaders + '';
    responseHeaders = responseHeaders.slice(1, -1).split(', ');
    blackListedResHeaders = blackListedResHeaders.slice(1, -1).split(', ');
    
    responseHeaders.forEach (function(headerName) {
		
    		if ((checkEntryExistsIgnoreCase(blackListedResHeaders, headerName)) || 
    		    (headerName.toUpperCase().indexOf("X-AEG") !== -1) || 
    		    headerName.toUpperCase().indexOf("X-FORWARDED") !== -1) {
    		    
    		    print("response.header." + headerName);
    		    context.removeVariable("response.header." + headerName);
    		}
    	});
    }


function checkEntryExistsIgnoreCase(arrayEntry, entryToSearch) {
    var entryExists = false;
    arrayEntry.forEach(function (anEntry) {
        if (anEntry.toUpperCase().localeCompare(entryToSearch.toUpperCase()) === 0) {
            entryExists = true;
        }
    })
    return entryExists;
}
