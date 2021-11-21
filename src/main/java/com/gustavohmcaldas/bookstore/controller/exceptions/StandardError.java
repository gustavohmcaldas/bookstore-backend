package com.gustavohmcaldas.bookstore.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardError {

	private Long timestamp;
	private Integer status;
	String error;
}
