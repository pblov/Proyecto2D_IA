/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appjuego;

/**
 *
 * @author Pablo Vásquez
 */
public class Estado {
    public int x;
    public int y;
    public char operacion;
    public Estado predecesor;

    public Estado(int x, int y, char operacion, Estado predecesor) {
        this.x = x;
        this.y = y;
        this.operacion = operacion;
        this.predecesor = predecesor;
    }
    
    @Override
    public boolean equals(Object o){
        Estado e = (Estado) o;
        return this.x == e.x && this.y == e.y;
    }
    
    @Override
    public int hashCode(){
        int hash = 3;
        hash = 89* hash + this.x;
        hash = 89 * hash + this.y;
        return hash;
    }
    
    @Override
    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }
}
