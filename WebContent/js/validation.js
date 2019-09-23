function checkUserPassword(){
	
	var password=document.getElementById("password").value;
	var conformPassword=document.getElementById("confirm_password").value;
	
	if(conformPassword!=password){
		windows.location();
	}
	else{
		console.log("password not matched");
	}
	
}