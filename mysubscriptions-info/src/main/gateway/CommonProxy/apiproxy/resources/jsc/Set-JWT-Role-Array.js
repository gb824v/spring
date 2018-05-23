var claimRole = context.getVariable("jwt.claim_role");
var claimRoleArray = claimRole.split(',');
context.setVariable("jwt.claim_role", claimRoleArray);
