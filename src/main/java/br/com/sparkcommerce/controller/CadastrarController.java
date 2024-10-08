package br.com.sparkcommerce.controller;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.sparkcommerce.dao.UsuarioDAO;
import br.com.sparkcommerce.model.Usuario;

@Controller
@Path("cadastrar")
public class CadastrarController {

    @Inject EntityManager em;
    @Inject Result result;
    @Inject UsuarioDAO usuarioDAO;
    @Inject Validator validator;
    @Inject HttpSession session;

    @Get("")
    public void cadastrar() {
        // Exibe a tela de cadastro
    }

    @IncludeParameters
    @Post("salvaUsuario")
    public void salvaUsuario(@Valid Usuario usuario, String confirmaSenha) {
        // O tipo de usuário já estará no objeto 'usuario'
        boolean tipoValido = usuario.getTipoUsuario() != null && (usuario.getTipoUsuario().equals("ADMIN") || usuario.getTipoUsuario().equals("USER"));
        validator.ensure(tipoValido, new SimpleMessage("erro", "Selecione um tipo de usuário válido"));

       
        // Se houver erro, retorna para a página de cadastro
        validator.onErrorRedirectTo(this).cadastrar();

       
        // Salvar o usuário no banco de dados
        usuarioDAO.insert(usuario);

        // Colocar o usuário na sessão
        session.setAttribute("usuarioLogado", usuario);

        // Redirecionar para a página de produtos
        result.redirectTo(ProdutosController.class).produtos(null);
    }
}