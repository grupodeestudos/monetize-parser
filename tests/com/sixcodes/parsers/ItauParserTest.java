package com.sixcodes.parsers;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItauParserTest {

	@Test
	public void testParseItauSMS() {
		ItauParser itau = new ItauParser();
		String input = "Compra aprovada no seu ITAU UNICLASS VISA final 5276 - " +
					   "SUPERMERCADOS GUA valor RS 16,63 em 23/02/2013, as 14h30.";
		ParsedSMS r = itau.parse(input);
		
		assertEquals("ITAU", r.getBanco());
		assertEquals("UNICLASS VISA", r.getTipoCartao());
		assertEquals("5276", r.getFinalNumeroCartao());
		assertEquals("16,63", r.getValor());
		assertEquals("23/02/2013", r.getData());
		assertEquals("14h30", r.getHora());
		assertEquals("SUPERMERCADOS GUA", r.getEstabelecimento());
	}

}
