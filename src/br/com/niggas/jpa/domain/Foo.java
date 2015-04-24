package br.com.niggas.jpa.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Foo
 *
 */
@Entity
public class Foo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FooId id;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@MapsId("barId")
	private Bar bar;
	
	private String name;

	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}

	public FooId getId() {
		return id;
	}

	public void setId(FooId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}   
}
