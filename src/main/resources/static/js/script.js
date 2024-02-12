document.addEventListener('DOMContentLoaded', function() {
    const container = document.getElementById('container');
    const swapRegisterBtn = document.getElementById('swap-register');
    const swapLoginBtn = document.getElementById('swap-login');
    const register = document.getElementById('register');

    function validarCampoRequerido(campo, mensajeError) {
        if (!campo.value.trim()) {
            mostrarError(campo);
            return false;
        } else {
            ocultarError(campo);
            return true;
        }
    }

    function validarEmail(email) {
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email.value.trim())) {
            mostrarError(email);
            return false;
        } else {
            ocultarError(email);
            return true;
        }
    }

    function validarLongitudPassword(password) {
        if (password.value.trim().length < 8) {
            mostrarError(password);
            return false;
        } else {
            ocultarError(password);
            return true;
        }
    }

    function mostrarError(campo, mensaje) {
        const contenedor = campo.closest('.form-control');
        if (contenedor) {
            const errorMensaje = contenedor.querySelector('.error-msg');
            if (errorMensaje) {
                errorMensaje.textContent = mensaje;
                errorMensaje.style.visibility = 'visible';
                contenedor.classList.remove('valid');
                contenedor.classList.add('invalid');
            }
        }
    }
    
    function ocultarError(campo) {
        const contenedor = campo.closest('.form-control');
        if (contenedor) {
            const errorMensaje = contenedor.querySelector('.error-msg');
            if (errorMensaje) {
                errorMensaje.textContent = '';
                errorMensaje.style.visibility = 'hidden';
                contenedor.classList.remove('invalid');
                contenedor.classList.add('valid');
            }
        }
    }
    

    function validarFormulario() {
        const nombre = document.getElementById('nombre');
        const apellidos = document.getElementById('apellidos');
        const telefono = document.getElementById('telefono');
        const email = document.getElementById('email');
        const password = document.getElementById('password');

        let esValido = true;

        esValido = validarCampoRequerido(nombre) && esValido;
        esValido = validarCampoRequerido(apellidos) && esValido;
        esValido = validarCampoRequerido(telefono) && esValido;
        esValido = validarCampoRequerido(email) && esValido;
        esValido = validarCampoRequerido(password) && esValido;

        esValido = validarEmail(email) && esValido;
        esValido = validarLongitudPassword(password) && esValido;

        return esValido;
    }

    swapRegisterBtn.addEventListener('click', function() {
        container.classList.add("active");
    });

    swapLoginBtn.addEventListener('click', function() {
        container.classList.remove("active");
    });

    register.addEventListener('click', function(event) {
        if (!validarFormulario()) {
            event.preventDefault();
        }
    });
});
