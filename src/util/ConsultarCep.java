package util;

import br.com.correios.bsb.sigep.master.bean.cliente.AtendeCliente;
import br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteService;
import br.com.correios.bsb.sigep.master.bean.cliente.EnderecoERP;
import br.com.correios.bsb.sigep.master.bean.cliente.SQLException_Exception;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;
import model.Endereco;

public class ConsultarCep {
	
	public static Endereco consulta(String cep) throws SQLException_Exception, SigepClienteException
	{
		Endereco endereco = null;
		AtendeClienteService service = new AtendeClienteService();
        AtendeCliente port = service.getAtendeClientePort();
    	EnderecoERP enderecoERP = port.consultaCEP(cep);
    	endereco = new Endereco();
    	endereco.setCep(cep);
    	endereco.setLogradouro(enderecoERP.getEnd());
    	endereco.setCidade(enderecoERP.getCidade());
    	endereco.setUf(enderecoERP.getUf());
    	endereco.setBairro(enderecoERP.getBairro());
    	
		return endereco;
	}
	
//	public static void main(String[] args) {
//		String cep = "57045622";
//		try {
//			Endereco enderecoERP = new ConsultarCep().consulta(cep);
//			
//			System.out.println(enderecoERP.getBairro());
//	        System.out.println(enderecoERP.getCidade());
//	        System.out.println(enderecoERP.getLogradouro());
//	        System.out.println(enderecoERP.getUf());
//		} catch (SQLException_Exception | SigepClienteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}