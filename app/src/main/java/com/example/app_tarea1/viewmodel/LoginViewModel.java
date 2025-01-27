package com.example.app_tarea1.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();

    public void login(String username, String password) {
        if ("admin".equals(username) && "password".equals(password)) {
            loginStatus.setValue(true); // Usuario autenticado correctamente
        } else {
            loginStatus.setValue(false); // Fallo en la autenticación
        }
    }

    public LiveData<Boolean> getLoginStatus() {
        return loginStatus;
    }
}
