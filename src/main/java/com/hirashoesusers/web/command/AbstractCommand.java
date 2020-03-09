package com.hirashoesusers.web.command;

import com.hirashoesusers.core.IFachada;
import com.hirashoesusers.impl.controle.Fachada;

public abstract class AbstractCommand implements ICommand {
	
	protected IFachada fachada = new Fachada();
}
