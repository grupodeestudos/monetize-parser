package com.sixcodes.parsers;

import org.parboiled.BaseParser;
import org.parboiled.Node;
import org.parboiled.Parboiled;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.parserunners.BasicParseRunner;
import org.parboiled.support.ParsingResult;

@BuildParseTree
public class ItauParser extends BaseParser<Object>{

/*	
 * "Compra aprovada no seu ITAU UNICLASS VISA final 5276 - " +
 * "SUPERMERCADOS GUA valor RS 16,63 em 23/02/2013, as 14h30.";
*/	
	
	private Rule ESPACO = ZeroOrMore(AnyOf("- ,."));
	private Rule palavraMinuscula = Sequence(ESPACO, OneOrMore(CharRange('a', 'z')), ESPACO);
	private Rule sifrao = IgnoreCase("RS"); 
	private Rule numero = OneOrMore(CharRange('0', '9'));
	
	public Rule Main(){
		return Sequence(dispensableWords(), ESPACO,
						Banco(), ESPACO,
						TipoCartao(), ESPACO,
						antesNumeroCartao(), ESPACO,
						finalNumeroCartao(), ESPACO,
						Estabelecimento(), ESPACO, palavraMinuscula,
						sifrao, ESPACO,
						Valor(), ESPACO,
						palavraMinuscula, ESPACO,
						Data(), ESPACO, palavraMinuscula,
						Hora(), ESPACO,
						/* Lixo final */
						ZeroOrMore(ANY));
	}
	
	Rule Valor(){
		return Sequence(numero, ",", numero);
	}
	
	Rule Data(){
		return Sequence(numero, "/", numero, "/", numero);
	}
	
	Rule Hora(){
		return Sequence(numero, "h", numero);
	}
	
	Rule dispensableWords(){
		return Sequence(IgnoreCase("Compra"), ANY, 
						IgnoreCase("aprovada"), ANY, 
						IgnoreCase("no"), ANY, 
						IgnoreCase("seu"));
	}
	
	Rule Banco(){
		return IgnoreCase("ITAU");
	}
	
	Rule TipoCartao(){
		return Sequence("UNICLASS", ANY, "VISA");
	}
	
	Rule antesNumeroCartao(){
		return IgnoreCase("final");
	}
	
	Rule finalNumeroCartao(){
		return OneOrMore(CharRange('0', '9'));
	}
	
	Rule Estabelecimento(){
		Rule palavra = Sequence(OneOrMore(CharRange('A', 'Z')), Optional(" "));
		return OneOrMore(palavra);
	}
	
	
	/**
	 * Determina se esse parser é capaz de parsear o input fornecido
	 * @param input
	 * @return
	 */
	public ParsedSMS parse(String input){
		ParsedSMS resultado = new ParsedSMS();
		ItauParser parser = Parboiled.createParser(ItauParser.class);
		ParsingResult<?> result = new BasicParseRunner<String>(parser.Main()).run(input);
		
		Node<?> rootNode = result.parseTreeRoot;
		for (Node<?> n: rootNode.getChildren()){
			String label = n.getMatcher().getLabel();
			String value = input.substring(n.getStartIndex(), n.getEndIndex());
			resultado.saveValue(label, value);
		}
		return resultado;
	}
	
}
