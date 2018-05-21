var rules = context.getVariable("roRules");
if (rules !== null) {
	
	var finalRouteOffer;
    var rulesArray = rules.split('|||');
    
	if (rulesArray !== null) {
        
		var rulesLen = rulesArray.length;
		for (var i=0; i<rulesLen; i++) {
			
			var ruleArr = rulesArray[i].split(';');
			if (ruleArr !== null) { 
			
				var header = ruleArr[0].split('=');
				if (header !== null) {			
					
					var kvmHeaderKey = header[0];
					var kvmHeaderVal = header[1];
					
					var reqHdrVal = context.getVariable("request.header."+kvmHeaderKey);
					reqHdrVal = (reqHdrVal === null) ? null : reqHdrVal.trim().toUpperCase();
					
					if (reqHdrVal !== null) {
						if (containsComma(kvmHeaderVal)) {
						    kvmHeaderVal = kvmHeaderVal.split(',');
							if (checkEntryExists(kvmHeaderVal, reqHdrVal)) {
							    finalRouteOffer = pickRouteOffer(ruleArr);
								context.setVariable("request.header.assignedRouteOffer", finalRouteOffer);								
								break;
							}
						} else {
							
							if (isAsterisk(kvmHeaderVal) || 
								kvmHeaderVal.trim().toUpperCase().localeCompare(reqHdrVal) === 0) {
								
								// Header exists in the request. Now, pick route offer based on the rules
								finalRouteOffer = pickRouteOffer(ruleArr);
								context.setVariable("request.header.assignedRouteOffer", finalRouteOffer);								
								break;
							}
						}
					}
				}
			}
		}
	}
}

function checkEntryExists(inpArr, reqVal) {
    var inpArrLen = inpArr.length;
    for (var j=0; j<inpArrLen; j++) {
        var headerValEntry = inpArr[j];
		if (isAsterisk(headerValEntry) || 
			headerValEntry.trim().toUpperCase().localeCompare(reqVal) === 0 ||
			verifyWildcardEntry(headerValEntry, reqVal, j)) {
			return true;
		}
    }
    return false;
}

function verifyWildcardEntry(expr, testEntry, j) {    
    var regEx = new RegExp("\\b" + expr.replace(/\*/gi, ".*") + "\\b", 'gi');
    return regEx.test(testEntry);
}

function containsComma(inpStr) {
	return (inpStr.trim().toUpperCase().indexOf(',') !== -1);
}

function isAsterisk(inpStr) {
	return (inpStr.trim().toUpperCase().localeCompare('*') === 0);
}

function pickRouteOffer(ruleArr) {		
	var st = Number(1);
	var end = Number(0);
	var routerEntry = [];
	var ruleLen = ruleArr.length;
	var finalRouteOffer;				

	for(var j=1; j<ruleLen; j++) {
		
		var ruleEntry = ruleArr[j].split('=');				
		routerEntry.push({
			route : ruleEntry[1],	// "route" holds BLUE / GREEN (or something else)
			percentStart : st,
			percentEnd : end + parseInt(ruleEntry[0])
		});
		
		st += parseInt(ruleEntry[0]);
		end += parseInt(ruleEntry[0]);
	}
	
	// now, pick route offer based on RNG
	var rNum = getRandNum(1, 100);	
	var rtrEntryLen = routerEntry.length;
	for (var itr=0; itr<rtrEntryLen; itr++) {	
		if ((rNum>=routerEntry[itr].percentStart) &&
			(rNum<=routerEntry[itr].percentEnd)) {
				
				finalRouteOffer = routerEntry[itr].route;
				break;
		}			
	}
	return finalRouteOffer;				
}

function getRandNum(st, end) {
    return Math.floor(Math.random()*(end-st+1)+st);
}
