



function hacerFoco() {

	document.getElementById('red-social').focus();
	alert('hasta aca llegue');
}







const form = document.getElementById('form');
const inputs = document.querySelectorAll('#form input');

const expressions = {
	title: /^[a-zA-ZÀ-ÿ\s]{1,20}$/, // Letras y espacios, pueden llevar acentos.
	author: /^[a-zA-ZÀ-ÿ\s]{1,20}$/, // Letras y espacios, pueden llevar acentos.
	editorial: /^[a-zA-ZÀ-ÿ\s]{1,20}$/, // Letras y espacios, pueden llevar acentos.
	isbn: /^[0-9]{12}/ // doce números.
}




const input = {
	title: "",
	author: "",
	editorial: "",
	isbn: ""

}


document.getElementById('button').disabled = true;


const checkForms = (e) => {
	switch (e.target.name) {
		case "title":
			if (expressions.title.test(e.target.value)) {
				document.getElementById('group-title').classList.add('formCorrect');
				document.getElementById('group-title').classList.remove('formIncorrect');
				document.querySelector(`#group-title i`).classList.remove('fa-circle-xmark');
				//document.querySelector(`#group-name p`).classList.remove('errorUser'); //Texto para que aparezca el fallo (aun no implementado)
				document.querySelector(`#group-title i`).classList.add('fa-check-circle');
				input.title = true;



			} else {
				document.getElementById('group-title').classList.remove('formCorrect');
				document.getElementById('group-title').classList.add('formIncorrect');
				document.querySelector(`#group-title i`).classList.add('fa-circle-xmark');
				//document.querySelector(`#group-name p`).classList.add('errorUser'); //Texto para que aparezca el fallo (aun no implementado)
				input.title = false;

			}
			checkSaveButton();

			break;
		case "author":
			if (expressions.author.test(e.target.value)) {
				document.getElementById('group-author').classList.add('formCorrect');
				document.getElementById('group-author').classList.remove('formIncorrect');
				document.querySelector(`#group-author i`).classList.remove('fa-circle-xmark');
				document.querySelector(`#group-author i`).classList.add('fa-check-circle');
				input.author = true;


			} else {
				document.getElementById('group-author').classList.remove('formCorrect');
				document.getElementById('group-author').classList.add('formIncorrect');
				document.querySelector(`#group-author i`).classList.add('fa-circle-xmark');
				input.author = false;
			}
			checkSaveButton();

			break;
		case "editorial":
			if (expressions.editorial.test(e.target.value)) {
				document.getElementById('group-editorial').classList.add('formCorrect');
				document.getElementById('group-editorial').classList.remove('formIncorrect');
				document.querySelector(`#group-editorial i`).classList.remove('fa-circle-xmark');
				document.querySelector(`#group-editorial i`).classList.add('fa-check-circle');
				input.editorial = true;

			} else {
				document.getElementById('group-editorial').classList.remove('formCorrect');
				document.getElementById('group-editorial').classList.add('formIncorrect');
				document.querySelector(`#group-editorial i`).classList.add('fa-circle-xmark');
				input.editorial = false;
			}
			checkSaveButton();

			break;
		case "isbn":
			if (expressions.isbn.test(e.target.value)) {
				document.getElementById('group-isbn').classList.add('formCorrect');
				document.getElementById('group-isbn').classList.remove('formIncorrect');
				document.querySelector(`#group-isbn i`).classList.remove('fa-circle-xmark');
				document.querySelector(`#group-isbn i`).classList.add('fa-check-circle');
				input.isbn = true;

			} else {
				document.getElementById('group-isbn').classList.add('formIncorrect');
				document.getElementById('group-isbn').classList.remove('formCorrect');
				document.querySelector(`#group-isbn i`).classList.add('fa-circle-xmark');
				document.querySelector(`#group-isbn i`).classList.remove('fa-check-circle');
				input.isbn = false;
			}
			checkSaveButton();
			break;

	}


}





const checkSaveButtons = () => {

	if (input.title === true && input.author === true && input.editorial === true && input.isbn === true) {

		document.getElementById('button').disabled = false;

	} else {

		document.getElementById('button').disabled = true;

	}


}






inputs.forEach((input) => {
	input.addEventListener('keyup', checkForm);

});
const inputss = document.querySelectorAll('#form input');

const validaciones = {
	name: /^[a-zA-ZÀ-ÿ\s]{1,20}$/, // Letras y espacios, pueden llevar acentos.
	lastname: /^[a-zA-ZÀ-ÿ\s]{1,20}$/, // Letras y espacios, pueden llevar acentos.
	address: /^[a-zA-ZÀ-ÿ\s]+[0-9]+$/, // Letras y espacios, pueden llevar acentos.
	zipCode: /^[0-9]{4}/, // cuantro números.
	email: /^[a-zA-Z0-9_.]+@[a-zA-Z]+\.{1}[a-zA-Z.]+$/,
	password: /[a-zA-Z0-9*]{8,12}$/, // letras mayusculas, minuscular y numeros de 8 a 12 digitos.
	rpassword: /[a-zA-Z0-9*]{8,12}$/ // letras mayusculas, minuscular y numeros de 8 a 12 digitos.


}




const inputt = {
	name: "",
	lastname: "",
	address: "",
	zipCode: "",
	email: "",
	password: "",
	rpassword: ""
}


document.getElementById('button').disabled = true;


const checkForm = (e) => {
	switch (e.target.name) {
		case "name":
			if (expressions.name.test(e.target.value)) {
				document.getElementById('group-name').classList.add('formCorrect');
				document.getElementById('group-name').classList.remove('formIncorrect');
				document.querySelector(`#group-name i`).classList.remove('fa-circle-xmark');
				//document.querySelector(`#group-name p`).classList.remove('errorUser'); //Texto para que aparezca el fallo (aun no implementado)
				document.querySelector(`#group-name i`).classList.add('fa-check-circle');
				input.name = true;



			} else {
				document.getElementById('group-name').classList.remove('formCorrect');
				document.getElementById('group-name').classList.add('formIncorrect');
				document.querySelector(`#group-name i`).classList.add('fa-circle-xmark');
				//document.querySelector(`#group-name p`).classList.add('errorUser'); //Texto para que aparezca el fallo (aun no implementado)
				input.name = false;

			}
			checkSaveButton();

			break;
		case "lastname":
			if (expressions.lastname.test(e.target.value)) {
				document.getElementById('group-lastname').classList.add('formCorrect');
				document.getElementById('group-lastname').classList.remove('formIncorrect');
				document.querySelector(`#group-lastname i`).classList.remove('fa-circle-xmark');
				document.querySelector(`#group-lastname i`).classList.add('fa-check-circle');
				input.lastname = true;


			} else {
				document.getElementById('group-lastname').classList.remove('formCorrect');
				document.getElementById('group-lastname').classList.add('formIncorrect');
				document.querySelector(`#group-lastname i`).classList.add('fa-circle-xmark');
				input.lastname = false;
			}
			checkSaveButton();

			break;
		case "address":
			if (expressions.address.test(e.target.value)) {
				document.getElementById('group-address').classList.add('formCorrect');
				document.getElementById('group-address').classList.remove('formIncorrect');
				document.querySelector(`#group-address i`).classList.remove('fa-circle-xmark');
				document.querySelector(`#group-address i`).classList.add('fa-check-circle');
				input.address = true;

			} else {
				document.getElementById('group-address').classList.remove('formCorrect');
				document.getElementById('group-address').classList.add('formIncorrect');
				document.querySelector(`#group-address i`).classList.add('fa-circle-xmark');
				input.address = false;
			}
			checkSaveButton();

			break;
		case "zipCode":
			if (expressions.zipCode.test(e.target.value)) {
				document.getElementById('group-zipCode').classList.add('formCorrect');
				document.getElementById('group-zipCode').classList.remove('formIncorrect');
				document.querySelector(`#group-zipCode i`).classList.remove('fa-circle-xmark');
				document.querySelector(`#group-zipCode i`).classList.add('fa-check-circle');
				input.zipCode = true;

			} else {


				document.getElementById('group-zipCode').classList.add('formIncorrect');
				document.getElementById('group-zipCode').classList.remove('formCorrect');
				document.querySelector(`#group-zipCode i`).classList.add('fa-circle-xmark');
				document.querySelector(`#group-zipCode i`).classList.remove('fa-check-circle');
				input.zipCode = false;
			}
			checkSaveButton();


			break;
		case "email":
			if (expressions.email.test(e.target.value)) {
				document.getElementById('group-email').classList.add('formCorrect');
				document.getElementById('group-email').classList.remove('formIncorrect');
				document.querySelector(`#group-email i`).classList.remove('fa-circle-xmark');
				document.querySelector(`#group-email i`).classList.add('fa-check-circle');
				input.email = true;

			} else {
				document.getElementById('group-email').classList.remove('formCorrect');
				document.getElementById('group-email').classList.add('formIncorrect');
				document.querySelector(`#group-email i`).classList.add('fa-circle-xmark');
				input.email = false;
			}
			checkSaveButton();

			break;
		case "password":
			if (expressions.password.test(e.target.value)) {
				document.getElementById('group-password').classList.add('formCorrect');
				document.getElementById('group-password').classList.remove('formIncorrect');
				document.querySelector(`#group-password i`).classList.remove('fa-circle-xmark');
				document.querySelector(`#group-password i`).classList.add('fa-check-circle');
				input.password = true;

			} else {
				document.getElementById('group-password').classList.remove('formCorrect');
				document.getElementById('group-password').classList.add('formIncorrect');
				document.querySelector(`#group-password i`).classList.add('fa-circle-xmark');
				document.querySelector(`#group-password i`).classList.remove('fa-check-circle');
				input.password = false;
			}
			checkRpassword();
			checkSaveButton();

			break;
		case "rpassword":
			if (expressions.rpassword.test(e.target.value)) {
				document.getElementById('group-rpassword').classList.add('formCorrect');
				document.getElementById('group-rpassword').classList.remove('formIncorrect');
				document.querySelector(`#group-rpassword i`).classList.remove('fa-circle-xmark');
				document.querySelector(`#group-rpassword i`).classList.add('fa-check-circle');


			} else {
				document.getElementById('group-rpassword').classList.remove('formCorrect');
				document.getElementById('group-rpassword').classList.add('formIncorrect');
				document.querySelector(`#group-rpassword i`).classList.add('fa-circle-xmark');
				document.querySelector(`#group-rpassword i`).classList.remove('fa-check-circle');
				input.rpassword = false;
			}

			checkRpassword();
			checkSaveButton();

			break;



	}


}


// const checkSaveButton = () => {

// const name = document.getElementById('name');
// const lastname = document.getElementById('lastname');
// const address = document.getElementById('address');
// const zipCode = document.getElementById('zipCode');
// const email = document.getElementById('email');
// const password = document.getElementById('password');
// const rpassword = document.getElementById('rpassword');


// if (name === true && lastname === true && address === true && zipCode === true && email === true
// 	&& password === true && rpassword === true) {

// 	document.getElementById('button').disabled = false;

// } else {

// 	document.getElementById('button').disabled = true;

// }


// }





const checkRpassword = () => {
	const password = document.getElementById('password');
	const rpassword = document.getElementById('rpassword');

	if (password.value !== rpassword.value || rpassword.value === "") {
		document.getElementById(`group-rpassword`).classList.add('formIncorrect');
		document.getElementById(`group-rpassword`).classList.remove('formCorrect');
		document.querySelector(`#group-rpassword i`).classList.add('fa-circle-xmark');
		document.querySelector(`#group-rpassword i`).classList.remove('fa-check-circle');

	} else {
		document.getElementById(`group-rpassword`).classList.remove('formIncorrect');
		document.getElementById(`group-rpassword`).classList.add('formCorrect');
		document.querySelector(`#group-rpassword i`).classList.remove('fa-circle-xmark');
		document.querySelector(`#group-rpassword i`).classList.add('fa-check-circle');
		input.rpassword = true;

	}
}





const checkSaveButton = () => {

	if (input.name === true && input.lastname === true && input.address === true && input.zipCode === true && input.email === true
		&& input.password === true && input.rpassword === true) {

		document.getElementById('button').disabled = false;

	} else {

		document.getElementById('button').disabled = true;

	}


}






inputs.forEach((input) => {
	input.addEventListener('keyup', checkForm);

});