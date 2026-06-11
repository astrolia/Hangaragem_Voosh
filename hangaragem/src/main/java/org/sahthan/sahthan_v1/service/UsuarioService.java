package org.sahthan.sahthan_v1.service;

import org.sahthan.sahthan_v1.dao.UsuarioDAO;
import org.sahthan.sahthan_v1.model.Usuario;
import org.sahthan.sahthan_v1.util.HashUtil;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void cadastrarUsuario(
            String nome,
            String email,
            String senha,
            String confirmarSenha) throws Exception {

        if (nome.isBlank() ||
                email.isBlank() ||
                senha.isBlank()) {

            throw new Exception("Preencha todos os campos.");
        }

        if (!senha.equals(confirmarSenha)) {
            throw new Exception("As senhas não coincidem.");
        }

        if (usuarioDAO.existeEmail(email)) {
            throw new Exception("Email já cadastrado.");
        }

        String senhaHash = HashUtil.sha256(senha);

        Usuario usuario =
                new Usuario(nome, email, senhaHash);

        usuarioDAO.inserirUsuario(usuario);
    }

    public Usuario autenticar(
            String email,
            String senha) {

        String senhaHash =
                HashUtil.sha256(senha);

        return usuarioDAO.autenticar(
                email,
                senhaHash);
    }
}