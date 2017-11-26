/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drinkkiarkisto.domain;

/**
 *
 * @author John-Robert
 */
public abstract class AbstractNamedObject {

    private Integer id;
    private String name;

    public AbstractNamedObject(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AbstractNamedObject{" + "id=" + id + ", name=" + name + '}';
    }
    
}
