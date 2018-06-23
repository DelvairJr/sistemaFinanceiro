package br.com.sistemafinanceiro.controller;

import br.com.sistemafinanceiro.dao.UsuarioDAO;
import br.com.sistemafinanceiro.model.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Junior
 */
public class UsuarioController {

    private UsuarioDAO dao;
    private Usuario usuario;

    public UsuarioController() {
        dao = new UsuarioDAO();
        usuario = new Usuario();
    }

    public boolean logIn() throws NoSuchAlgorithmException {
        encryptPassword();
        if (dao.logIn(usuario) != null) {
            return true;
        } else {
            return false;
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void save() throws NoSuchAlgorithmException {
        encryptPassword();
        dao.create(usuario);
    }

    private void encryptPassword() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(usuario.getSenha().getBytes());

        byte byteData[] = md.digest();

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        usuario.setSenha(hexString.toString());
    }
}
