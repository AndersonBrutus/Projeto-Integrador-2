package br.com.sparkcommerce.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;

@Controller
public class PagamentoController{
	
	@Inject Result result;
	
	@Get("success")
	public void success() {	    
	    // Redireciona para a página de sucesso
	    result.forwardTo("/WEB-INF/jsp/pagamento/success.jsp");
	}

    @Get("failure")
    public void failure() {
    	// Redireciona para a página de falha
    	result.forwardTo("/WEB-INF/jsp/pagamento/failure.jsp");
	}

    @Get("pending")
    public void pending() {
    	// Redireciona para a página de pendente
    	result.forwardTo("/WEB-INF/jsp/pagamento/pending.jsp");
    }
}