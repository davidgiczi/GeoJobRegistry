document.getElementById("back").addEventListener("click", goBackToPlanRegister);
document.getElementById("input").addEventListener("click", goGeoInput);
document.getElementById("list").addEventListener("click", goGeoList);
document.getElementById("save").addEventListener("click", goGeoSave);
document.getElementById("map").addEventListener("click", goGoogleMap);
document.getElementById("tr").addEventListener("click", goTransformation);

function goBackToPlanRegister() {
	
	
	document.getElementById("data").submit();
	
}

function goGeoInput() {
	
	location.href = "geoinput.jsp";
	
}

function goGeoList() {
	
	document.getElementById("init").submit();
	
}

function goGeoSave() {
	
	location.href = "initGeoRegSave";
	
}

function goGoogleMap() {
	
	var startPlace = prompt("Indulási, vagy a keresendő hely megadása:");

	if(startPlace == null || startPlace === ""){
		return;
	}
	
	var stopPlace = prompt("Úticél megadása útvonaltervezés esetén:");
	

	if (startPlace != null && stopPlace == null) {

		window.open("https://www.google.hu/maps/dir/" + startPlace);
	
	}
	else if(startPlace != null && stopPlace != null) {

		window.open("https://www.google.hu/maps/dir/" + startPlace + "/" + stopPlace);
	}
}


function goTransformation() {
	
	window.open("https://pf-prg.hu/trafo/trafo-4.php");
}
