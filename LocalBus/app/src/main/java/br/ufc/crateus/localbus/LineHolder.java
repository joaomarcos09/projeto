package br.ufc.crateus.localbus;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LineHolder {
    public List<UserModel> users = new ArrayList<>();

    public LineHolder(List<UserModel> users) {
        this.users = users;
    }

    public LineHolder() {

    }

    public void addUser(String nome, String email, String curso, String matricula, String senha) {

        UserModel user = new UserModel(nome, email, matricula, curso, senha);
        users.add(user);
    }
    public void exibe(){
        for(int i=0; i < this.users.size(); i++){
            Log.i("message", this.users.get(i).getNome().toString());
        }
    }
    public UserModel getUsuario(String email){
        for(int i=0; i < this.users.size(); i++){
            if(this.users.get(i).getEmail().toString().equals(email)){
                return this.users.get(i);
            }
        }
        return null;
    }
}
