var requestHeaders = context.getVariable("request.headers.names");
var blackListedReqHeaders = context.getVariable("blackListedRequestHeaders");

var requestQueryParams = context.getVariable("request.queryparams.names");
var blackListedQryParams = context.getVariable("blackListedQueryParams");

if (blackListedReqHeaders !== null) {

    requestHeaders = requestHeaders + '';
    requestHeaders = requestHeaders.slice(1,  - 1).split(', ');
    blackListedReqHeaders = blackListedReqHeaders.slice(1,  - 1).split(', ');
    
    requestHeaders.forEach (function(headerName) {
		
		if ((checkEntryExistsIgnoreCase(blackListedReqHeaders, headerName)) || headerName.toUpperCase().indexOf("X-AEG") !== -1) {
		    
		    print("request.header." + headerName);
		    context.removeVariable("request.header." + headerName);
		}
	});
}

if (blackListedQryParams !== null) {

    requestQueryParams = requestQueryParams + '';
    requestQueryParams = requestQueryParams.slice(1,  - 1).split(', ');
    blackListedQryParams = blackListedQryParams.split(',');
    
    for (var j=0; j<requestQueryParams.length; j++) {
        for (var i=0; i<blackListedQryParams.length; i++) {
            
            if (removeDashesAndUnderscores(requestQueryParams[j].trim()).toUpperCase().localeCompare(blackListedQryParams[i].trim().toUpperCase()) === 0) {
                print("request.queryparam." + requestQueryParams[j]);
                context.removeVariable("request.queryparam." + requestQueryParams[j]);
            }
        }
    }
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

function removeDashesAndUnderscores(rawString) {
    return rawString.replace("-", "").replace("_", "");
}
