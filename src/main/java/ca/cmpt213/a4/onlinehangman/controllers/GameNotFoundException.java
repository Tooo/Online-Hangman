package ca.cmpt213.a4.onlinehangman.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * GameNotFoundException is a custom RuntimeException
 * It is thrown when the Game ID is invalid
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Game ID not found.")
public class GameNotFoundException extends RuntimeException{

}
