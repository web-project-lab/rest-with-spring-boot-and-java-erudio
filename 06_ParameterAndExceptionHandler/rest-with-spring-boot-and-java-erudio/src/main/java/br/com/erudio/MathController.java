package br.com.erudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exceptions.UnsupoportedMathOperationException;
import jakarta.websocket.server.PathParam;

@RestController
public class MathController {
	
	private final AtomicLong counter = new AtomicLong();
	
	/**
	 * Quando é path variavel entãol é obrigatório passar os parâmetros corretamente
	 * @param numberOne
	 * @param numberTwo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) 
			throw new UnsupoportedMathOperationException("Please set a numeric value!");
		
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}
	
	private Double convertToDouble(String stringNumber) {
		if(stringNumber == null) return 0D;
		
		String number = stringNumber.replaceAll(",", ".");
		if(isNumeric(number)) return Double.parseDouble(number);
			
		return 0D;
	}

	private boolean isNumeric(String stringNumber) {
		if(stringNumber == null) return false;
		String number = stringNumber.replaceAll(",", ".");
		
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

	/**
	 * É possível passar o path variabel para campos obrigatórios e ao mesmo tempo passar RequestParam que 
	 * são query strings, para passar os demais campos que são opcionais.
	 * @param numberOne
	 * @param numberTwo
	 * @param op
	 * @return
	 */
	@RequestMapping(value="/sum-with-param-optional/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sumComPathVariableAndOptionalRequestParam(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo,
			@RequestParam(value = "op", defaultValue = "") String op
			) {
		if(op != null && !op.isEmpty()) System.out.println(op);
		else System.out.println("Não passou OP");
		return 1D;
	}

	/**
	 * Quando vc vai passar só valores opcionais, como se fosse um query string, então vc usa RequestParam e a
	 * rota não precisa da atributo value e não precisa dizer que é get, pois por default é get
	 * @param op
	 * @return
	 */
	@RequestMapping("/valor-opcional")
	public Double umValorOpcional(
			@RequestParam(value = "op", defaultValue = "") String op
			) {
		if(op != null && !op.isEmpty()) System.out.println(op);
		else System.out.println("Não passou OP");
		return 1D;
	}
}
