package com.sixcodes.parsers;

import java.lang.reflect.Method;

public class ParsedSMS {

	private String banco;
	private String tipoCartao;
	private String finalNumeroCartao;
	private String estabelecimento;
	private String valor;
	private String data;
	private String hora;
	
	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getTipoCartao() {
		return tipoCartao;
	}

	public void setTipoCartao(String tipoCartao) {
		this.tipoCartao = tipoCartao;
	}

	public String getFinalNumeroCartao() {
		return finalNumeroCartao;
	}

	public void setfinalNumeroCartao(String finalCartao) {
		this.finalNumeroCartao = finalCartao;
	}

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(String estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	
	public void saveValue(String campo, String valor) {

		try{
			Method setter = ParsedSMS.class.getDeclaredMethod("set" + campo, String.class);
			setter.invoke(this, valor);
			
		}catch (NoSuchMethodException nsm) {
			
		}catch (Exception e){
			
		}
		
	} 
	
}
