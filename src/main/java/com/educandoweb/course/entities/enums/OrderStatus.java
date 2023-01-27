package com.educandoweb.course.entities.enums;

public enum OrderStatus {

	//embora haja um valor padrão conforme a ordem da lista, é importante...
	//atribuirmos um valor, pois se ocorrer um manutenção, mudaremos todos os...
	//demais valores, ocasionando quebra nas regras de negócio...
	//em decorrência, temos algumas implementações adicionais
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code;
	
	//para atribuir o valor do código
	private OrderStatus(int code) {
		this.code = code;
	}
	
	//para retornar o valor do código
	public int getCode() {
		return code;
	}
	
	public static OrderStatus valueOf(int code) {
		//varrer todos os códigos
		for (OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Order Status code");
	}
}
